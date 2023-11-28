package a1_2001040069;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LibraryTransaction implements Comparable<LibraryTransaction> {
    private Patron patron;
    private Book book;
    private Date checkoutDate;
    private Date dueDate;
    private Date returnDate;
    private double fineAmount;

    public LibraryTransaction(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        this.patron = patron;
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.fineAmount = 0;
    }

    public Patron getPatron() {
        return patron;
    }

    public Book getBook() {
        return book;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public double calculateFine() {
        int daysLate = (int) TimeUnit.DAYS.convert((this.returnDate.getTime() - this.dueDate.getTime()), TimeUnit.MILLISECONDS);
        if (daysLate <= 0) {
            return 0;
        } else if (daysLate <= 7) {
            return daysLate * 1.00;
        } else if (daysLate <= 14) {
            return (daysLate - 7) * 2.00 + 7.00;
        } else  {
            return (daysLate - 14) * 3.00 + 21.00;
        }
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setFineAmount() {
        this.fineAmount = calculateFine();
    }

    public String getDescription() {
        String s;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, MMM d yyyy");
        if (this.returnDate != null) {
            s = "Transaction Details:\n" +
                    "\tPatron ID: " + this.patron.getPatronID() + "\n" +
                    "\tBook ISBN: " + this.book.getISBN() +"\n" +
                    "\tCheckout Date: " + this.getCheckoutDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter) +"\n" +
                    "\tDue Date: " + this.getDueDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter) + "\n" +
                    "\tReturn Date: " + this.getReturnDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter) + "\n" +
                    "\tFine Amount: $" + new DecimalFormat("0.00").format(this.fineAmount);
        } else {
            s = "Transaction Details:\n" +
                    "\tPatron ID: " + this.patron.getPatronID() + "\n" +
                    "\tBook ISBN: " + this.book.getISBN() +"\n" +
                    "\tCheckout Date: " + this.getCheckoutDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter) +"\n" +
                    "\tDue Date: " + this.getDueDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter) + "\n" +
                    "\tReturn Date: Book Not Returned\n" +
                    "\tFine Amount: $" + new DecimalFormat("0.00").format(this.fineAmount);
        }
        return s;
    }

    public int getPatronIntID() {
        return Integer.parseInt(this.getPatron().getPatronID().substring(1));
    }

    @Override
    public int compareTo(LibraryTransaction o) {
        return this.getPatronIntID() - o.getPatronIntID();
    }

}
