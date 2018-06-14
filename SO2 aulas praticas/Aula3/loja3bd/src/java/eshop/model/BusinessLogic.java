package eshop.model;

import eshop.beans.Category;
import eshop.beans.Book;
import eshop.beans.CartItem;
import eshop.beans.Customer;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * Interface com as operações a implementar
 * pelo gestor de acesso aos dados.
 */
public interface BusinessLogic {
    
    //---------- Category operations ----------
    public abstract String getCategoryName(String categoryID);
    public abstract int getCategoryIdFromCategoryName(String categoryName);
    public abstract LinkedList<Category> getCategoryList();

    
    //---------- Book operations ----------
    public abstract LinkedList<Book> getSearchResults(String keyword);
    public abstract LinkedList<Book> getBooksInCategoryId(String categoryId);
    public abstract LinkedList<Book> getBooksInCategoryName(String categoryName);

    public abstract Book getBookDetails(String bookID);
    
    //---------- Order operations ----------
    public abstract long insertOrder(Customer customer, HashMap<String, CartItem> shoppingCart);
    
    
    public void finish();
    
}
