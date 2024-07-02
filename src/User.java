package src;

import java.time.LocalDate;
import java.util.*;

public class User {

    private String name;
    private String readerID;
    private TreeSet<Book> borrowedBooks;
    private Comparator<Book> comparator = (Book book1, Book book2) -> book1.getReturnDate().compareTo(book2.getReturnDate());

    public User(String name, String readerID) {
        this.name = name;
        this.readerID = readerID;
        this.borrowedBooks = new TreeSet<>(comparator);
    }
    public boolean borrow(Book book) {
        if (book.isBorrowed()) {
            return false;
        }
        book.setBorrowed(true);
        borrowedBooks.add(book);
        return true;
    }

    public boolean returnBook(Book book){
        if (!book.isBorrowed()) {
            return false;
        }
        book.setBorrowed(false);
        borrowedBooks.remove(book);
        return true;
    }





    public String getName() {
        return name;
    }
    public String getReaderID() {
        return readerID;
    }
}
