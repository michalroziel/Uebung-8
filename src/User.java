package src;

import java.time.LocalDate;
import java.util.*;

public class User {

    private String name;
    private String readerID;
    private TreeSet<Book> borrowedBooks;
    private Comparator<Book> comparator = Comparator.comparing(Book::getReturnDate, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Book::getTitle);

    public User(String readerID, String name) {
        this.name = name;
        this.readerID = readerID;
        this.borrowedBooks = new TreeSet<>(comparator);
    }
    public boolean borrow(Book book) {
        if (book.isBorrowed()) {
            return false;
        }
        book.setReturnDate(LocalDate.now().plusDays(14));
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


    public TreeSet<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getName() {
        return name;
    }
    public String getReaderID() {
        return readerID;
    }
}
