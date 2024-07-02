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


}
