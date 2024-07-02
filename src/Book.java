package src;

import java.time.LocalDate;

public class Book {

    private String title;
    private String author;
    private int year;
    private int pages;
    private String genre;
    private double rating;  // 1.0 - 5.0
    private boolean borrowed;
    private LocalDate returnDate;   // date of return if book is borrowed

    public Book(String title, String author, int year, int pages, String genre, double rating) throws InvalidRatingException {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.genre = genre;

        if ( !( rating >= 1.0 && rating <= 5.0) ) {
            throw new InvalidRatingException("Rating must be between 1.0 and 5.0");
        } else {

            this.rating = rating;
        }


        this.borrowed = false;
        this.returnDate = null;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", borrowed=" + borrowed +
                ", returnDate=" + returnDate +
                '}';
    }


}
