package eshop.model;

import eshop.beans.Category;
import eshop.beans.Book;
import eshop.beans.CartItem;
import eshop.beans.Customer;

import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.ServletContext;

/**
 * Classe com as operações e acesso aos dados (Versão Memory-Only)
 *
 */
public class DataManager implements BusinessLogic {

    /**
     * DB connector instanciado com um dos tipos possíveis (memória, MySQL,
     * HyperSQL...)
     *
     */
    private static BusinessLogic dbManager;

    public DataManager() {
    }


    public DataManager(ServletContext context) {
        // ESCOLHER 1 das opcoes:

        // a
        dbManager= new MemoryOnlyDM();

        // b
        /*
        dbManager = new HyperSQLDM(
                (String) context.getAttribute("HyperSQL_dbURL"),
                (String) context.getAttribute("HyperSQL_dbName"),
                (String) context.getAttribute("HyperSQL_dbUser"),
                (String) context.getAttribute("HyperSQL_dbPassword")
        );
        */

        // c
        /*
        dbManager = new MySQLDM(
                (String) context.getAttribute("dbURL"),
                (String) context.getAttribute("dbName"),
                (String) context.getAttribute("dbUser"),
                (String) context.getAttribute("dbPassword")
        );
        */
                
    }


    
    //---------- Category operations ----------
    @Override
    public String getCategoryName(String categoryID) {
        return dbManager.getCategoryName(categoryID);
    }

    @Override
    public int getCategoryIdFromCategoryName(String categoryName) {
        return dbManager.getCategoryIdFromCategoryName(categoryName);
    }

    @Override
    public LinkedList<Category> getCategoryList() {
        return dbManager.getCategoryList();
    }

    //---------- Book operations ----------
    @Override
    public LinkedList<Book> getSearchResults(String keyword) {
        return dbManager.getSearchResults(keyword);
    }

    @Override
    public LinkedList<Book> getBooksInCategoryId(String categoryId) {
        return dbManager.getBooksInCategoryId(categoryId);
    }

    @Override
    public LinkedList<Book> getBooksInCategoryName(String categoryName) {
        return dbManager.getBooksInCategoryName(categoryName);
    }

    @Override
    public Book getBookDetails(String bookID) {
        return dbManager.getBookDetails(bookID);
    }

    //---------- Order operations ----------
    @Override
    public long insertOrder(Customer customer, HashMap<String, CartItem> shoppingCart) {
        return dbManager.insertOrder(customer, shoppingCart);
    }

    // **************
    @Override
    public void finish() {
        dbManager.finish();
    }

}
