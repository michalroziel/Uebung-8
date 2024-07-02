package src;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibraryManagementSystem {

    private TreeMap<String, Book> bibliotheque;
    private HashMap<String, User> users;
    private TreeSet<Book> borrowedBooks;
    private Comparator<Book> comparator = Comparator.comparing(Book::getReturnDate).thenComparing(Book::getTitle);

    public LibraryManagementSystem() {
        bibliotheque = new TreeMap<>();
        users = new HashMap<>();
        borrowedBooks = new TreeSet<>(comparator);
    }
    public User getUser(String ID){
        return users.get(ID);
    }
    // Add a new Book to the library. If already exists, return false else add it and return true.
    public boolean addBook(Book book){
        if (bibliotheque.containsKey(book.getTitle())){
            return false;
        }
        bibliotheque.put(book.getTitle(), book);
        return true;
    }
    public boolean addUser(User user){
        if (users.containsKey(user.getReaderID())){
            return false;
        }
        users.put(user.getReaderID(), user);
        return true;
    }
    public boolean returnBook(String readerID, String bookTitle){
        if(!bibliotheque.containsKey(bookTitle)){
            return false;
        }
        if(!users.containsKey(readerID)){
            return false;
        }
        Book book = bibliotheque.get(bookTitle);
        User user = users.get(readerID);
        if(user.returnBook(book)){
            borrowedBooks.remove(book);
            book.setReturnDate(null);
            return true;
        }
        return false;
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
            borrowedBooks.add(book);
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
        borrowedBooks.forEach(System.out::println);
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
        bibliotheque.values().stream().sorted(Comparator.comparingInt(Book::getPages)).forEach(sortedBib::add);
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
    public List<Book> filterByAuthor(String author){
        List<Book> filteredBib = new ArrayList<>();
        bibliotheque.values().stream().filter(book -> book.getAuthor().equals(author)).forEach(filteredBib::add);
        return filteredBib;
    }
    public LinkedHashMap<String, Integer> authorsWithMostBooks(){
        LinkedHashMap<String,Integer> countBooksByAuthor = new LinkedHashMap<>();
        List<String> authors = bibliotheque.values().stream().map(Book::getAuthor).toList();
        for (String author : authors){
            List<Book> books = filterByAuthor(author);
            countBooksByAuthor.put(author,books.size());
        }

        return countBooksByAuthor.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public List<Book> filterAndSort(Comparator<Book> sorting, Predicate<Book> filtering){
        return bibliotheque.values().stream().filter(filtering).sorted(sorting).toList();
    }

    public void printAllBooks() {
        bibliotheque.values().forEach(System.out::println);
    }
}