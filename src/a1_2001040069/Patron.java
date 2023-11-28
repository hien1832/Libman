package a1_2001040069;

import common.PatronType;

public class Patron {
    private static int patronCreated = 0;
    private final String patronID;
    private final String name;
    private final String dob;
    private final String email;
    private final String phoneNumber;
    private final PatronType patronType;
    private final int checkOutLimit;
    private int checkedOutTimes;

    public Patron(String name, String dob, String email, String phoneNumber, PatronType patronType) {
        this.patronID = generatePatronID();
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
        if (this.patronType == PatronType.REGULAR) {
            this.checkOutLimit = 3;
        } else {
            this.checkOutLimit = 5;
        }
        this.checkedOutTimes = 0;
    }

    public String generatePatronID() {
        String id;
        if (patronCreated < 10) {
            id = "P00" + (patronCreated += 1);
        } else if (patronCreated < 100) {
            id = "P0" + (patronCreated += 1);
        } else {
            id = "P" + (patronCreated += 1);
        }
        return id;
    }

    public void checkedOutBook() {
        if (this.canCheckOutBook()) {
            this.checkedOutTimes += 1;
        } else {
            System.out.println("Can't borrow more books");
        }
    }

    public void returnedBook() {
        if (this.checkedOutTimes > 0) {
            this.checkedOutTimes -= 1;
        } else {
            System.out.println("No book borrowed");
        }
    }

    public boolean canCheckOutBook() {
        return this.checkedOutTimes < this.checkOutLimit;
    }

    public String getPatronID() {
        return patronID;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PatronType getPatronType() {
        return patronType;
    }
}
