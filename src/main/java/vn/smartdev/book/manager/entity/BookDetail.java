package vn.smartdev.book.manager.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bsp_book_detail")
public class BookDetail extends BaseEntity implements Serializable {

    @Id
    private String id;

    @Column(name = "author")
    private String author;

    @Column(name = "publication_date")
    private String publicationDate;

    @Column(name = "pages")
    private int pages;

    @Column(name = "languages")
    private String languages;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "file_format")
    private String fileFormat;

    private String category;

    @Column(name = "driver_directory")
    private String driverDirectory;

    @Column(name = "book_description")
    private String bookDescription;

    @Column
    private String image;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Book.class)
    @JoinColumn(name = "book_reference_id", nullable = false)
    private Book book;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDriverDirectory() {
        return driverDirectory;
    }

    public void setDriverDirectory(String driverDirectory) {
        this.driverDirectory = driverDirectory;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", pages=" + pages +
                ", languages='" + languages + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileFormat='" + fileFormat + '\'' +
                ", category='" + category + '\'' +
                ", driverDirectory='" + driverDirectory + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", image='" + image + '\'' +
                ", book=" + book +
                '}';
    }
}
