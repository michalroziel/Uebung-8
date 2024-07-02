package src;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibraryManagementSystem {

    TreeMap<String, Book> bibliotheque;
    HashMap<String, User> users;
    TreeMap<String, Book> borrowedBooks;

    public LibraryManagementSystem() {
        bibliotheque = new TreeMap<>();
        users = new HashMap<>();
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
    // Prints all borrowed Books by a specific user.
    public void allBooksBorrowedByUser(String readerID){
        if(!users.containsKey(readerID)){
            System.out.println("User does not exist");
        }
        User user = users.get(readerID);
        user.getBorrowedBooks().forEach(System.out::println);
    }
    //Printing all books in the library sorted by the return date.
    public void printAllBorrowedBooks() {
        borrowedBooks.values().stream().sorted((book1,book2)-> book1.getReturnDate().compareTo(book2.getReturnDate())).forEach(System.out::println);
    }
    // Filters the books by release Year and returns them as a List.
    public List<Book> filterBooksByRelease(int releaseYear){
        List<Book> filteredBib = new ArrayList<>();
        bibliotheque.values().stream().filter(book -> book.getYear() == releaseYear).forEach(filteredBib::add);
        return filteredBib;
    }
    //Sorts the books by the number of pages and returns them as a List.
    public List<Book> sortBooksByNumberOfPages() {
        List<Book> sortedBib = new ArrayList<>();
        bibliotheque.values().stream().sorted((book1,book2)-> Integer.compare(book1.getPages(),book2.getPages())).forEach(sortedBib::add);
        return sortedBib;
    }
    public int calculateTotalNumberOfPages(){
       return bibliotheque.values().stream().mapToInt(Book::getPages).sum();
    }
    public List<Book> filterByGenre(String genre){
        List<Book> filteredBib = new ArrayList<>();
        bibliotheque.values().stream().filter(book -> book.getGenre().equals(genre)).forEach(filteredBib::add);
        return filteredBib;
    }
    public TreeMap<String,Double> calculateAverageRatingByGenre(){
        TreeMap<String,Double> averageRatingByGenre = new TreeMap<>();
        List<String> genres = bibliotheque.values().stream().map(Book::getGenre).toList();
        for (String genre : genres){
            List<Book> books = filterByGenre(genre);
            double averageRating = books.stream().collect(Collectors.averagingDouble(Book::getRating));
            averageRatingByGenre.put(genre,averageRating);
        }
        return averageRatingByGenre;
    }
    public List<Book> topThreeRatedBooks(){
        return sortByRates().subList(0,3);
    }
    public List<Book> sortByRates(){
        return bibliotheque.values().stream().sorted((book1, book2)-> Double.compare(book2.getRating(),book1.getRating())).toList();
    }
    public List<String> authorsWithMostBooks(){
        //TODO ???
        return null;
    }
    public List<Book> filterAndSort(Comparator<Book> sorting, Predicate<Book> filtering){
        return bibliotheque.values().stream().filter(filtering).sorted(sorting).toList();
    }

}