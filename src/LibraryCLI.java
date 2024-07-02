package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;


public class LibraryCLI {
    private LibraryManagementSystem libraryManagementSystem;
    private Scanner scanner;

    public LibraryCLI(LibraryManagementSystem libraryManagementSystem) {
        this.libraryManagementSystem = libraryManagementSystem;
        this.scanner = new Scanner(System.in);
    }

    public void run() throws InvalidRatingException {
        loadBooksFromCSV("books.csv");

        boolean running = true;

        while (running) {
            System.out.println("\n--- Bücherverwaltungssystem ---");
            System.out.println("1. Buch hinzufügen");
            System.out.println("2. Alle Bücher anzeigen");
            System.out.println("3. Bücher nach Jahr filtern");
            System.out.println("4. Bücher nach Seitenanzahl sortieren");
            System.out.println("5. Gesamtanzahl der Seiten berechnen");
            System.out.println("6. Buch ausleihen");
            System.out.println("7. Buch zurückgeben");
            System.out.println("8. Ausgeliehene Bücher eines Nutzers anzeigen");
            System.out.println("9. Alle ausgeliehenen Bücher anzeigen, sortiert nach Rückgabedatum");
            System.out.println("10. Bücher nach Genre filtern");
            System.out.println("11. Durchschnittliche Bewertung pro Genre berechnen");
            System.out.println("12. Top-bewertete Bücher anzeigen");
            System.out.println("13. Autoren mit den meisten Büchern anzeigen");
            System.out.println("14. Bücher nach Bewertung sortieren");
            System.out.println("15. Gefilterte und sortierte Liste der Bücher anzeigen");
            System.out.println("16. Nutzer anlegen");
            System.out.println("17. Programm beenden");
            System.out.print("Bitte wählen Sie eine Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    filterBooksByYear();
                    break;
                case 4:
                    sortBooksByPages();
                    break;
                case 5:
                    calculateTotalPages();
                    break;
                case 6:
                    borrowBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    displayBorrowedBooksByUser();
                    break;
                case 9:
                    displayAllBorrowedBooks();
                    break;
                case 10:
                    filterBooksByGenre();
                    break;
                case 11:
                    calculateAverageRatingPerGenre();
                    break;
                case 12:
                    displayTopRatedBooks();
                    break;
                case 13:
                    displayAuthorsWithMostBooks();
                    break;
                case 14:
                    sortBooksByRating();
                    break;
                case 15:
                    filterAndSortBooks();
                    break;
                case 16:
                    addUser();
                    break;
                case 17:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte versuchen Sie es erneut.");
            }
        }
    }

    private void loadBooksFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    // Skip the header line
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                String title = values[0];
                String author = values[1];
                int year = Integer.parseInt(values[2]);
                int pages = Integer.parseInt(values[3]);
                String genre = values[4];
                double rating = Double.parseDouble(values[5]);

                Book parsedBook = new Book(title, author, year, pages, genre, rating);
                this.libraryManagementSystem.addBook(parsedBook);
            }

            System.out.println("Books loaded from CSV file.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidRatingException e) {
            throw new RuntimeException(e);
        }
    }


    private void addBook() throws InvalidRatingException {
        System.out.print("Titel: ");
        String title = scanner.nextLine();
        System.out.print("Autor: ");
        String author = scanner.nextLine();
        System.out.print("Veröffentlichungsjahr: ");
        int year = scanner.nextInt();
        System.out.print("Anzahl der Seiten: ");
        int pages = scanner.nextInt();
        System.out.print("Genre: ");
        String genre = scanner.next();
        System.out.print("Bewertung: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (libraryManagementSystem.addBook(new Book(title, author, year, pages, genre, rating))) {
            System.out.println("Buch hinzugefügt!");
        } else {
            System.out.println("Buch konnte nicht hinzugefügt werden.");
        }
    }
    private void addUser(){
        System.out.println("Bitte geben Sie die Benutzer-ID ein: ");
        String userID = scanner.nextLine();
        System.out.println("Bitte geben Sie den Namen des Benutzers ein: ");
        String name = scanner.nextLine();
        libraryManagementSystem.addUser(new User(userID,name));
    }

    private void displayAllBooks() {
        libraryManagementSystem.printAllBooks();
    }

    private void filterBooksByYear() {
        System.out.print("Geben Sie das Jahr ein: ");
        libraryManagementSystem.filterBooksByRelease(scanner.nextInt()).forEach(System.out::println);
    }

    private void sortBooksByPages() {
        libraryManagementSystem.sortBooksByNumberOfPages().forEach(System.out::println);
    }

    private void calculateTotalPages() {
        System.out.println("Gesamtanzahl der Seiten: " + libraryManagementSystem.calculateTotalNumberOfPages());
    }

    private void borrowBook() {
        System.out.print("Benutzer-ID: ");
        String readerID = scanner.nextLine();
        System.out.print("Buchtitel: ");
        String bookTitle = scanner.nextLine();

        if (libraryManagementSystem.borrowBook(readerID, bookTitle)) {
            System.out.println("Buch ausgeliehen!");
        } else {
            System.out.println("Buch konnte nicht ausgeliehen werden.");
        }
    }

    private void returnBook() {
        System.out.print("Benutzer-ID: ");
        String readerID = scanner.nextLine();
        System.out.print("Buchtitel: ");
        String bookTitle = scanner.nextLine();

        if (libraryManagementSystem.returnBook(readerID, bookTitle)) {
            System.out.println("Buch zurückgegeben!");
        } else {
            System.out.println("Buch konnte nicht zurückgegeben werden.");
        }
    }

    private void displayBorrowedBooksByUser() {
        System.out.print("Benutzer-ID: ");
        libraryManagementSystem.allBooksBorrowedByUser(scanner.nextLine());

    }

    private void displayAllBorrowedBooks() {
        libraryManagementSystem.printAllBorrowedBooks();
    }

    private void filterBooksByGenre() {
        System.out.print("Genre: ");
        libraryManagementSystem.filterByGenre(scanner.nextLine()).forEach(System.out::println);
    }

    private void calculateAverageRatingPerGenre() {
        libraryManagementSystem.calculateAverageRatingByGenre().forEach((genre, rating) -> System.out.println(genre + ": " + rating));
    }

    private void displayTopRatedBooks() {
        libraryManagementSystem.topThreeRatedBooks().forEach(System.out::println);
    }

    private void displayAuthorsWithMostBooks() {
        libraryManagementSystem.authorsWithMostBooks().forEach((author, count) -> System.out.println(author + ": " + count));
    }

    private void sortBooksByRating() {
        libraryManagementSystem.sortByRates().forEach(System.out::println);
    }

    private void filterAndSortBooks() {
        System.out.println("Filtern nach benutzerdefinierten Kriterien:");
        System.out.println("1. Nach Jahr");
        System.out.println("2. Nach Seitenanzahl");
        System.out.println("3. Nach Bewertung");
        System.out.print("Wählen Sie ein Filterkriterium: ");
        int filterChoice = scanner.nextInt();
        System.out.print("Wählen Sie 1 für > oder 2 für <: ");
        int comparison = scanner.nextInt();
        System.out.print("Geben Sie den Wert ein: ");
        double filterValue = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Predicate<Book> filter;
        switch (filterChoice) {
            case 1:
                if (comparison == 1)
                    filter = book -> book.getYear() > filterValue;
                else
                    filter = book -> book.getYear() < filterValue;
                break;
            case 2:
                if (comparison == 1)
                    filter = book -> book.getPages() > filterValue;
                else
                    filter = book -> book.getPages() < filterValue;
                break;
            case 3:
                if (comparison == 1)
                    filter = book -> book.getRating() > filterValue;
                else
                    filter = book -> book.getRating() < filterValue;
                break;
            default:
                System.out.println("Ungültige Auswahl.");
                return;
        }

        System.out.println("Sortieren nach benutzerdefinierten Kriterien:");
        System.out.println("1. Nach Titel");
        System.out.println("2. Nach Jahr");
        System.out.println("3. Nach Seitenanzahl");
        System.out.println("4. Nach Bewertung");
        System.out.print("Wählen Sie ein Sortierkriterium: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Comparator<Book> sorter;
        switch (sortChoice) {
            case 1:
                sorter = Comparator.comparing(Book::getTitle);
                break;
            case 2:
                sorter = Comparator.comparing(Book::getYear);
                break;
            case 3:
                sorter = Comparator.comparing(Book::getPages);
                break;
            case 4:
                sorter = Comparator.comparing(Book::getRating);

                break;
            default:
                System.out.println("Ungültige Auswahl.");
                return;
        }

        List<Book> result = libraryManagementSystem.filterAndSort(sorter, filter);
        result.forEach(System.out::println);
    }

    public static void main(String[] args) throws InvalidRatingException {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        LibraryCLI libraryCLI = new LibraryCLI(libraryManagementSystem);
        libraryCLI.run();
    }
}
