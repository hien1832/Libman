package a1_2001040069;

import common.Genre;

import java.util.Scanner;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private Genre genre;
    private int publicationYear;
    private int numCopiesAvailable;

    public Book(String title, String author, Genre genre, int publicationYear, int numCopiesAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.numCopiesAvailable = numCopiesAvailable;
        this.ISBN = generateISBN();
    }

    public String generateISBN() {
        String id = "";
        Scanner scanner = new Scanner(this.author);
        while (scanner.hasNext()) {
            String part = scanner.next();
            String initial = part.substring(0, 1);
            id += initial;
        }
        id += "-";
        Integer genreCode = genre.ordinal() + 1;
        if (genreCode < 10) {
            id += "0" + genreCode + "-" + Integer.toString(this.publicationYear);
        } else {
            id += genreCode + "-" + Integer.toString(this.publicationYear);
        }
        return id;
    }

    public boolean canBeBorrowed() {
        return this.numCopiesAvailable > 0;
    }
    public void borrowed() {
        if (this.canBeBorrowed()) {
            this.numCopiesAvailable -= 1;
        } else {
            System.out.println("No book to borrow");
        }
    }

    public void returned() {
        this.numCopiesAvailable += 1;
    }
    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getNumCopiesAvailable() {
        return numCopiesAvailable;
    }
}
