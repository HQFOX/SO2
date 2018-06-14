package eshop.model;

import eshop.beans.Category;
import eshop.beans.Book;
import eshop.beans.CartItem;
import eshop.beans.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * Classe com as operações e acesso aos dados (Versão BD MySQL)
 *
 */
public class MySQLDM implements BusinessLogic {

    Connection connection;

    public MySQLDM(String dbURL, String dbName, String dbUser, String dbPassword) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = 
                    DriverManager.getConnection(dbURL
                            +dbName+"?"+"user="+dbUser+"&password="+dbPassword);
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Could not connect to DB: " + e.getMessage());
        } catch (InstantiationException e) {
            System.out.println("Could not connect to DB: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println("Could not connect to DB: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to DB: " + e.getMessage());
        }
    }

    
    //---------- Category operations ----------
    @Override
    public String getCategoryName(String categoryId) {
        Category category = null;
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select category_id, category_name from"
                        + " categories where category_id=" + categoryId;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        if (rs.next()) {
                            category = new Category(rs.getInt(1), rs.getString(2));
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get categories: " + e.getMessage());
            } finally {
                // 
            }
        }
        if (category != null) {
            return category.getName();
        }
        return null;
    }

    
    @Override
    public int getCategoryIdFromCategoryName(String categoryName) {
        int categoryId = -1;
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select category_id from"
                        + " categories where category_name='" + categoryName + "'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        if (rs.next()) {
                            categoryId = rs.getInt(1);
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get category id: " + e.getMessage());
            } finally {
                // 
            }
        }
        return categoryId;
    }

    
    @Override
    public LinkedList<Category> getCategoryList() {
        LinkedList<Category> categories = new LinkedList<Category>();
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select category_id, category_name from categories";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            categories.add(new Category(rs.getInt(1), rs.getString(2)));
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get categories: " + e.getMessage());
            } finally {
                //
            }
        }
        return categories;
    }

    
    //---------- Book operations ----------
    @Override
    public LinkedList<Book> getSearchResults(String keyword) {
        LinkedList<Book> books = new LinkedList<Book>();
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select book_id, title, author, price from books"
                        + " where title like '%" + keyword.trim() + "%'"
                        + " or author like '%" + keyword.trim() + "%'";
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Book book = new Book();
                            book.setId(rs.getString(1));
                            book.setTitle(rs.getString(2));
                            book.setAuthor(rs.getString(3));
                            book.setPrice(rs.getDouble(4));
                            books.add(book);
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not search for books:" + e.getMessage());
            } finally {
                //
            }
        }
        return books;
    }

    
    @Override
    public LinkedList<Book> getBooksInCategoryId(String categoryId) {
        LinkedList<Book> livrosNestaCategoria = new LinkedList<Book>();
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select book_id, title, author, price from books"
                        + " where category_id=" + categoryId;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Book book = new Book();
                            book.setId(rs.getString(1));
                            book.setTitle(rs.getString(2));
                            book.setAuthor(rs.getString(3));
                            book.setPrice(rs.getDouble(4));
                            livrosNestaCategoria.add(book);
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get books: " + e.getMessage());
            } finally {
                //
            }
        }
        System.err.println("\t# getBooksInCategory(): " + livrosNestaCategoria);
        return livrosNestaCategoria;
    }

    
    @Override
    public LinkedList<Book> getBooksInCategoryName(String categoryName) {
        LinkedList<Book> livrosNestaCategoria = new LinkedList<Book>();

        int categoryId = getCategoryIdFromCategoryName(categoryName);

        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select book_id, title, author, price from books"
                        + " where category_id=" + categoryId;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    try {
                        while (rs.next()) {
                            Book book = new Book();
                            book.setId(rs.getString(1));
                            book.setTitle(rs.getString(2));
                            book.setAuthor(rs.getString(3));
                            book.setPrice(rs.getDouble(4));
                            livrosNestaCategoria.add(book);
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get books: " + e.getMessage());
            } finally {
                //
            }
        }
        System.err.println("\t# getBooksInCategory(): " + livrosNestaCategoria);
        return livrosNestaCategoria;
    }

    
    @Override
    public Book getBookDetails(String bookID) {
        Book book = null;
        if (connection != null) {
            try {
                Statement s = connection.createStatement();
                String sql = "select book_id, title, author, price from books"
                        + " where book_id=" + bookID;
                try {
                    ResultSet rs = s.executeQuery(sql);
                    if (rs.next()) {
                        book = new Book();
                        book.setId(rs.getString(1));
                        book.setTitle(rs.getString(2));
                        book.setAuthor(rs.getString(3));
                        book.setPrice(rs.getDouble(4));
                    }
                } finally {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Could not get book: " + e.getMessage());
            } finally {
                //
            }
        }
        return book;
    }

    
    //---------- Order operations ----------
    @Override
    public long insertOrder(Customer customer, HashMap<String, CartItem> shoppingCart) {
        long returnValue = 0L;
        try {
            long orderId = System.currentTimeMillis();

            // colocar na Base de Dados
            if (connection != null) {
                try {
                    Statement stmt = connection.createStatement();
                    // a COMPRA
                    String sql = "insert into orders (order_id, delivery_name,"
                            + " delivery_address, cc_name, cc_number, cc_expiry) values ('"
                            + orderId + "','" + customer.getContactName() + "','"
                            + customer.getDeliveryAddress() + "','"
                            + customer.getCcName() + "','" + customer.getCcNumber()
                            + "','" + customer.getCcExpiryDate() + "')";
                    stmt.executeUpdate(sql);

                    // cada item na compra:
                    for (CartItem item : shoppingCart.values()) {
                        stmt = connection.createStatement();
                        sql = "insert into order_details (order_id, book_id, quantity,"
                                + " price, title, author) values ('" + orderId + "','"
                                + item.getBookID() + "','" + item.getQuantity() + "','"
                                + item.getPrice() + "','" + item.getTitle() + "','"
                                + item.getAuthor() + "')";
                        stmt.executeUpdate(sql);
                    }

                } catch (SQLException e) {
                    System.err.println("ERRO ao escrever na BD: " + e.getMessage());
                }
            }
            // registar a compra nos logs da WebApp
            System.out.println("MySQL: PROCESSA COMPRA: \n\tCLIENTE: "
                    + customer.toString() + "\n\tLISTA DE COMPRAS: "
                    + shoppingCart.toString()
                    + "\n\tCODIGO DA VENDA: " + orderId);

            returnValue = orderId;
        } catch (Exception e) {
            System.err.println("PROBLEMA: insertOrder(): " + e.getMessage());
        }
        return returnValue;
    }

    
    @Override
    public void finish() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    
}
