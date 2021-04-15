package yqx1110.LibraryManagement.entity;

import java.util.Date;

public class Book {
    private int bookId;
    private String name;
    private String author;
    private String publish;
    private String ISBN;
    private String introduction;
    private String language;
    private double price;
    private Date pubdate;
    private int class_id;
    private int pressmark;
    private int state;

    public Book(int bookId, String name, String author, String publish,
                String ISBN, String introduction, String language, double price,
                Date pubdate, int class_id, int pressmark, int state) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.ISBN = ISBN;
        this.introduction = introduction;
        this.language = language;
        this.price = price;
        this.pubdate = pubdate;
        this.class_id = class_id;
        this.pressmark = pressmark;
        this.state = state;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getPressmark() {
        return pressmark;
    }

    public void setPressmark(int pressmark) {
        this.pressmark = pressmark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
