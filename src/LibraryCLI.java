package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;

public class LibraryCLI {
    private LibraryManagementSystem libraryManagementSystem;
    private Scanner scanner;

    public LibraryCLI(LibraryManagementSystem libraryManagementSystem) {
        this.libraryManagementSystem = libraryManagementSystem;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
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
            System.out.println("16. Programm beenden");
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
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte versuchen Sie es erneut.");
            }
        }
    }

    private void loadBooksFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            . . .
            System.out.println("Bücher aus CSV-Datei geladen.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addBook() {
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

        . . .
        System.out.println("Buch hinzugefügt!");
    }

    private void displayAllBooks() {
    }

    private void filterBooksByYear() {
    }

    private void sortBooksByPages() {
    }

    private void calculateTotalPages() {
    }

    private void borrowBook() {
    }

    private void returnBook() {
    }

    private void displayBorrowedBooksByUser() {
    }

    private void displayAllBorrowedBooks() {
    }

    private void filterBooksByGenre() {
    }

    private void calculateAverageRatingPerGenre() {
    }

    private void displayTopRatedBooks() {
    }

    private void displayAuthorsWithMostBooks() {
    }

    private void sortBooksByRating() {
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
                filter = ...
                break;
            case 2:
                filter = ...
                break;
            case 3:
                filter = ...
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
                sorter = ...
                break;
            case 2:
                sorter = ...
                break;
            case 3:
                sorter = ...
                break;
            case 4:
                sorter = ...
                break;
            default:
                System.out.println("Ungültige Auswahl.");
                return;
        }

        List<Book> result = libraryManagementSystem.filterAndSortBooks(filter, sorter);
        result.forEach(System.out::println);
    }

    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        LibraryCLI libraryCLI = new LibraryCLI(libraryManagementSystem);
        libraryCLI.run();
    }
}
