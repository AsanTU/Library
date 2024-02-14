package org.example;

public class Book {
    private String title;
    private String author;
    private Integer year;
    private String kind;
    private Boolean rent;

    public Book(String title, String author, Integer year, String kind) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.kind = kind;
        this.rent = true;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Boolean getRent() {
        return rent;
    }

    public void setRent(Boolean rent) {
        this.rent = rent;
    }

    public String toString(){
        return "Book {" +
                "title:'" + title + '\'' +
                ", author:" + author +
                ", year:" + year +
                ", kind:" + kind + '\'' +
                ", rent:" + rent + '\'' + '}';
    }
}
