package entity.media;

import java.sql.SQLException;
import java.util.Date;

public class Book extends Media {

    String author;
    String coverType;
    String publisher;
    Date publishDate;
    int numOfPages;
    String language;
    String bookCategory;

    public Book() throws SQLException{

    }

    // coupling: data -> chi phu thuoc mot so tham so
    public Book(int id, String title, String category, int price, int quantity, String type, String author,
            String coverType, String publisher, Date publishDate, int numOfPages, String language,
            String bookCategory) throws SQLException{
        super(id, title, category, price, quantity, type);
        this.author = author;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.numOfPages = numOfPages;
        this.language = language;
        this.bookCategory = bookCategory;
    }

    // getter and setter
    public int getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }
    public String getCoverType() {
        return this.coverType;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public Book setCoverType(String coverType) {
        this.coverType = coverType;
        return this;
    }

    public String getPublisher() {
        return this.publisher;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Date getPublishDate() {
        return this.publishDate;
    }

    //stamp coupling: truyen doi tuong Date
    public Book setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public int getNumOfPages() {
        return this.numOfPages;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public Book setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
        return this;
    }

    public String getLanguage() {
        return this.language;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public Book setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getBookCategory() {
        return this.bookCategory;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public Book setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
        return this;
    }

    @Override
    public String toString() {
        String basicInformation = "{" +
                " id='" + id + "'" +
                ", title='" + title + "'" +
                ", category='" + category + "'" +
                ", price='" + price + "'" +
                ", quantity='" + quantity + "'" +
                ", type='" + type + "'" +
                ", imageURL='" + imageURL + "'" +
                "}";
        return "{" +
            basicInformation +
            " author='" + author + "'" +
            ", coverType='" + coverType + "'" +
            ", publisher='" + publisher + "'" +
            ", publishDate='" + publishDate + "'" +
            ", numOfPages='" + numOfPages + "'" +
            ", language='" + language + "'" +
            ", bookCategory='" + bookCategory + "'" +
            "}";
    }
}
