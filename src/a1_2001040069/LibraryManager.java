package a1_2001040069;

import common.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LibraryManager {
    private List<Book> books;
    private List<LibraryTransaction> transactions;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> checkedOutBooks = new ArrayList<>();
        for (LibraryTransaction libraryTransaction : this.transactions) {
            if (libraryTransaction.getPatron().equals(patron)) {
                checkedOutBooks.add(libraryTransaction);
            }
        }
        return checkedOutBooks;
    }

    public void checkoutBook(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        if (patron.canCheckOutBook() && book.canBeBorrowed()) {
            LibraryTransaction libraryTransaction = new LibraryTransaction(patron, book, checkoutDate, dueDate);
            patron.checkedOutBook();
            book.borrowed();
            transactions.add(libraryTransaction);
        } else {
            System.out.println("Transaction: " + patron.getPatronID() + " borrows " + book.getISBN() + " Failed to check out");
        }
    }

    public void returnBook(LibraryTransaction transaction, Date returnDate) {
        if (transaction.getReturnDate() == null) {
            transaction.setReturnDate(returnDate);
            transaction.getPatron().returnedBook();;
            transaction.getBook().returned();
            transaction.setFineAmount();
            System.out.println("Transaction: " + transaction.getPatron().getPatronID() + " borrows " +  transaction.getBook().getISBN() + " Book is successfully returned.");
        } else {
            System.out.println("Transaction: " + transaction.getPatron().getPatronID() + " borrows " +  transaction.getBook().getISBN() + " Book's already returned");
        }
    }

    public List<LibraryTransaction> getOverdueBooks() {
        List<LibraryTransaction> overdueBooks = new ArrayList<>();
        Date currentDate = new DateUtils().getCurrentDate();
        for (LibraryTransaction transaction : this.transactions) {
            if ((transaction.getReturnDate() == null) && (transaction.getDueDate().getTime() < currentDate.getTime())) {
                overdueBooks.add(transaction);
            }
        }
        return overdueBooks;
    }

    public void sort() {
        Collections.sort(this.transactions);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<LibraryTransaction> getTransactions() {
        return transactions;
    }
}
