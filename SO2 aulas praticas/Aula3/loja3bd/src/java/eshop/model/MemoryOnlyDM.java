package eshop.model;

import eshop.beans.Category;
import eshop.beans.Book;
import eshop.beans.Customer;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * Classe com as operações e acesso aos dados (Versão Memory-Only)
 * 
 */
public class MemoryOnlyDM implements BusinessLogic {
    
    
    private LinkedList<Category> categorias;
    private LinkedList<Book> livros;
    private HashMap<String,LinkedList<Book>> livrosPorCategoria;
    
 
    public MemoryOnlyDM() {
        super();
        iniciarDadosFicticios();
    }
 

    //---------- Category operations ----------
    @Override
    public String getCategoryName(String categoryID) {
        Category category= null;
        for (Category c: categorias) {
            if (c.getId() == Integer.parseInt(categoryID))
                category= c;
        }
        return (category == null) ? null : category.getName();
    }

    
    @Override
    public LinkedList<Category> getCategoryList() {
        return categorias;
    }

    
    //---------- Book operations ----------
    @Override
    public LinkedList<Book> getSearchResults(String keyword) {
        LinkedList<Book> res= new LinkedList<Book>();
        if (keyword != null) {
            for (Book b: livros) {
                if (b.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                    res.add(b);
                }
            }
        }
        return res;
    }

    @Override
    public LinkedList<Book> getBooksInCategoryName(String categoryName) {
         LinkedList<Book> livrosNestaCategoria= livrosPorCategoria.get(categoryName);
         System.err.println("\t# getBooksInCategory(): "+ livrosNestaCategoria);
         return livrosNestaCategoria;
    }


    @Override
    public LinkedList<Book> getBooksInCategoryId(String categoryId) {
        String categoryName= null;
        for (Category c: categorias) {
            if ( (""+c.getId()).equals( categoryId ) ) {
                categoryName= c.getName();
                break;
            }
        }
         LinkedList<Book> livrosNestaCategoria= livrosPorCategoria.get(categoryName);
         System.err.println("\t# getBooksInCategory(): "+ livrosNestaCategoria);
         return livrosNestaCategoria;
    }    
    
    @Override
    public int getCategoryIdFromCategoryName(String categoryName) {
        int id= -1;
        for (Category c: categorias) {
            if ( c.getName().equals( categoryName ) ) {
                id= c.getId();
                break;
            }
        }
        return id;
    }
    
    @Override
    public Book getBookDetails(String bookID) {
        for (Book b: livros) {
            if (b.getId().equals(bookID))
                return b;
        }
        System.err.println("# AVISO: livro nao encontrado: "+bookID);
        return null;
    }

    
    
    //---------- Order operations ----------
    @Override
    public long insertOrder(Customer customer, HashMap shoppingCart) {
        long returnValue = 0L;
        try {
            long orderId = System.currentTimeMillis();

            // colocar na Base de Dados
            // ...
            
            // registar a compra nos logs da WebApp
            System.out.println("PROCESSA COMPRA: \n\tCLIENTE: "
                    +customer.toString()+"\n\tLISTA DE COMPRAS: "
                    +shoppingCart.toString()
                    +"\n\tCODIGO DA VENDA: "+orderId);
            
            returnValue= orderId;
        }
        catch (Exception e) {
            System.err.println("PROBLEMA: insertOrder(): "+e.getMessage());
        }
        return returnValue;
    }
    
    
    
    /**
     * colocar alguns dados no repositorio em memoria...
     * TESTES
     */
    private void iniciarDadosFicticios() {
        categorias= new LinkedList<Category>();
        livros= new LinkedList<Book>();
        livrosPorCategoria= new HashMap<String, LinkedList<Book>>();
        // **********
        Book b1= new Book("1","Pro CSS and HTML Design Patterns","Michael Bowers",44.99);
        Book b2= new Book("2","Pro PayPal E-Commerce","Damon Williams",59.99);
        Book b3= new Book("3","The Complete Robot","Isaac Asimov",8.95);
        Book b4= new Book("4","Foundation","Isaac ASimov",8.95);
        Book b5= new Book("5","Area 7","Matthew Reilly",5.99);
        Book b6= new Book("6","Term Limits","Vince Flynn",6.99);
        
        livros.add(b1);
        livros.add(b2);
        livros.add(b3);
        livros.add(b4);
        livros.add(b5);
        livros.add(b6);
        
        Category c1= new Category(1, "Web Development");
        Category c2= new Category(2, "Scientific Fiction");
        Category c3= new Category(3, "Action Novels");
        
        LinkedList<Book> livros1= new LinkedList<Book>();
        livros1.add(b1);
        livros1.add(b2);
        LinkedList<Book> livros2= new LinkedList<Book>();
        livros2.add(b3);
        livros2.add(b4);
        LinkedList<Book> livros3= new LinkedList<Book>();
        livros3.add(b5);
        livros3.add(b6);
        
        categorias.add(c1);
        categorias.add(c2);
        categorias.add(c3);
        
        livrosPorCategoria.put(c1.getName(), livros1);
        livrosPorCategoria.put(c2.getName(), livros2);
        livrosPorCategoria.put(c3.getName(), livros3);
        System.out.println("#####  dados ficticios prontos para uso  ##### "+ (new java.util.Date()));
    }
    
    
    @Override
    public void finish() {
      // nada a fazer
    }
    
}
