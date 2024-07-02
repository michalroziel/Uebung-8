package src;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class LibraryManagementSystem {

    TreeMap<String, Book> bibliotheque;
    LinkedHashMap<String, User> users;
    TreeMap<String, Book> borrowedBooks;

    public LibraryManagementSystem() {
        bibliotheque = new TreeMap<>();
        users = new LinkedHashMap<>();
        borrowedBooks = new TreeMap<>();
    }

    // Add a new Book to the library. If already exists, return false else add it and return true.
    public boolean addBock(Book book){
        if (bibliotheque.containsKey(book.getTitle())){
            return false;
        }
        bibliotheque.put(book.getTitle(), book);
        return true;
    }

    // Borrow a Book from the library. If the book does not exist or the user does not exist, return false. Else, borrow the book and return true.
    public boolean borrowBook(String readerID, String bookTitle){
        if(!bibliotheque.containsKey(bookTitle)){
            return false;
        }
        if(!users.containsKey(readerID)){
            return false;
        }
        Book book = bibliotheque.get(bookTitle);
        User user = users.get(readerID);
        if(user.borrow(book)){
            borrowedBooks.put(bookTitle, book);
            return true;
        }
        return false;
    }

}
