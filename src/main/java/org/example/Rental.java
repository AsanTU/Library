package org.example;

import java.util.Date;

public class Rental {
    private String userId;
    private String bookTitle;
    private Date rentalDate;
    private Date dueData;
    private Date returnDate;

    public Rental(String userId, String bookTitle) {
        this.userId = userId;
        this.bookTitle = bookTitle;
        this.rentalDate = new Date();
        this.dueData = new Date(System.currentTimeMillis() + (14 * 24 * 60 * 60 * 1000));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getDueData() {
        return dueData;
    }

    public void setDueData(Date dueData) {
        this.dueData = dueData;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isOverdue() {
        return rentalDate == null && System.currentTimeMillis() > dueData.getTime();
    }
}
