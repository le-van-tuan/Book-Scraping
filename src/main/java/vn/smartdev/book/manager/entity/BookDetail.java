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

    @Column(name = "link_download")
    private String linkDownload;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private DownloadState state;

    @Column(name = "drive_file_id")
    private String driveFileId;

    @Column(name = "drive_file_name")
    private String driveFileName;

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

    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    public DownloadState getState() {
        return state;
    }

    public void setState(DownloadState state) {
        this.state = state;
    }

    public String getDriveFileId() {
        return driveFileId;
    }

    public void setDriveFileId(String driveFileId) {
        this.driveFileId = driveFileId;
    }

    public String getDriveFileName() {
        return driveFileName;
    }

    public void setDriveFileName(String driveFileName) {
        this.driveFileName = driveFileName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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
                ", linkDownload='" + linkDownload + '\'' +
                ", state=" + state +
                ", book=" + book +
                '}';
    }
}
