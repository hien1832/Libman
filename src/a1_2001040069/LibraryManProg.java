package a1_2001040069;
import common.DateUtils;
import common.Genre;
import common.PatronType;

import javax.sound.midi.Soundbank;
import java.util.Calendar;
import java.util.Date;

public class LibraryManProg {
    private static Date[] checkoutDate = new Date[]{
            new Date(2023 - 1900, Calendar.MARCH, 25),
            new Date(2023 - 1900, Calendar.MAY, 8),
            new Date(2023 - 1900, Calendar.JUNE, 1),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.AUGUST, 10)
    };

    private static Date[] dueDate = new Date[]{
            new Date(2023 - 1900, Calendar.APRIL, 25),
            new Date(2023 - 1900, Calendar.MAY, 10),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.JULY, 25),
            new Date(2023 - 1900, Calendar.SEPTEMBER, 20)
    };

    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();

        // Initialize at least 10 books in the library collection.
        Book book1 = new Book("Rich Dad Poor Dad", "Jackson William", Genre.SELF_HELP, 2012, 10);
        Book book2 = new Book("One Piece", "Echiro Oda", Genre.ADVENTURE, 1990, 15);
        Book book3 = new Book("Doraemon", "Fujiko F. Fujio", Genre.SCIENCE_FICTION, 1980, 5);
        Book book4 = new Book("Tat Den", "Ngo Tat To", Genre.POETRY, 1945, 50);
        Book book5 = new Book("Romeo and Juliet", "William Shakepere", Genre.ROMANCE, 1947, 100);
        Book book6 = new Book("Can Nha Ma Am", "Nguyen Ngoc Ngan", Genre.THRILLER, 2012, 19);
        Book book7 = new Book("Monkey Planet War", "Leonel Sala Messi Ronaldo", Genre.FICTION, 1999, 15);
        Book book8 = new Book("Khong Thay Ngay Ve", "La Phong Lam", Genre.HISTORY, 2012, 10);
        Book book9 = new Book("Buong Doi Tay Nhau Ra", "Son Tung Mtp", Genre.FANTASY, 2011, 5);
        Book book10 = new Book("Nguoi Nhu Anh", "Mai Tien Dung", Genre.BIOGRAPHY, 2012, 75);
        Book book11 = new Book("Nguoi Vo Tam", "Bao Anh", Genre.NON_FICTION, 1996, 7);
        Book book12 = new Book("Khong Cam Xuc", "Ho Quang Hieu", Genre.BIOGRAPHY, 2011, 4);
        Book book13 = new Book("Noi Ay Con Tim ve", "Jack", Genre.MYSTERY, 2010, 2);

        libraryManager.addBook(book1);
        libraryManager.addBook(book2);
        libraryManager.addBook(book3);
        libraryManager.addBook(book4);
        libraryManager.addBook(book5);
        libraryManager.addBook(book6);
        libraryManager.addBook(book7);
        libraryManager.addBook(book8);
        libraryManager.addBook(book9);
        libraryManager.addBook(book10);
        libraryManager.addBook(book11);
        libraryManager.addBook(book12);
        libraryManager.addBook(book13);

        // Initialize at least 3 patrons involving both regular and premium patrons.
        Patron patron1 = new Patron("Nguyen Xuan Hien", "18-03-2002", "hienvip02@gmail.com", "0336042014", PatronType.PREMIUM);
        Patron patron2 = new Patron("Monkey D. Luffy", "17-02-1996", "luffy@gmail.com", "0337589423", PatronType.REGULAR);
        Patron patron3 = new Patron("Kudo Shinichi", "11-11-1911", "kudo@gmail.com", "0336583932", PatronType.REGULAR);

        // Initialize and use to create 5 book transactions
        libraryManager.checkoutBook(patron2, book1, checkoutDate[0], dueDate[0]);
        libraryManager.checkoutBook(patron2, book2, checkoutDate[1], dueDate[1]);
        libraryManager.checkoutBook(patron2, book3, checkoutDate[2], dueDate[2]);
        libraryManager.checkoutBook(patron3, book4, checkoutDate[2], dueDate[2]);
        libraryManager.checkoutBook(patron1, book5, checkoutDate[3], dueDate[3]);

        // Print currently checked-out books
        System.out.println("Checked-out books of Patron 1");
        for (LibraryTransaction libraryTransaction : libraryManager.getCheckedOutBooks(patron1)) {
            System.out.println(libraryTransaction.getDescription());
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Checked-out books of Patron 2");
        for (LibraryTransaction libraryTransaction : libraryManager.getCheckedOutBooks(patron2)) {
            System.out.println(libraryTransaction.getDescription());
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Checked-out books of Patron 3");
        for (LibraryTransaction libraryTransaction : libraryManager.getCheckedOutBooks(patron3)) {
            System.out.println(libraryTransaction.getDescription());
        }
        System.out.println("-------------------------------------------------------------");

        // Print list of the overdue books that are not returned yet
        System.out.println("Overdue books");
        for (LibraryTransaction libraryTransaction : libraryManager.getOverdueBooks()) {
            System.out.println(libraryTransaction.getDescription());
        }
        System.out.println("-------------------------------------------------------------");

        // Patron returns the book
        for (LibraryTransaction libraryTransaction : libraryManager.getTransactions()) {
            if (libraryTransaction.getReturnDate() == null) {
                libraryManager.returnBook(libraryTransaction, new DateUtils().getCurrentDate());
            }
        }
        System.out.println("-------------------------------------------------------------");

        // Sort transactions by patron ID
        System.out.println("Sorted Transactions");
        libraryManager.sort();
        for (LibraryTransaction libraryTransaction : libraryManager.getTransactions()) {
            System.out.println(libraryTransaction.getDescription());
        }
    }
}
