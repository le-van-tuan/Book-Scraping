package vn.smartdev.book.manager.entity.builder;

import vn.smartdev.book.manager.entity.Book;
import vn.smartdev.book.manager.entity.BookDetail;
import vn.smartdev.book.manager.entity.DownloadState;

import java.io.Serializable;
import java.util.Date;

public final class BookDetailBuilder implements Serializable{
    private Date createdAt = new Date();
    private String id;
    private String author;
    private Date lastUpdatedAt = new Date();
    private String publicationDate;
    private int versionNo = 1;
    private int pages;
    private String languages;
    private String fileSize;
    private String fileFormat;
    private String category;
    private String driverDirectory;
    private String bookDescription;
    private String image;
    private String linkDownload;
    private DownloadState state;
    private Book book;

    private BookDetailBuilder() {
    }

    public static BookDetailBuilder aBookDetail() {
        return new BookDetailBuilder();
    }

    public BookDetailBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public BookDetailBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public BookDetailBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookDetailBuilder withLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }

    public BookDetailBuilder withPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public BookDetailBuilder withVersionNo(int versionNo) {
        this.versionNo = versionNo;
        return this;
    }

    public BookDetailBuilder withPages(int pages) {
        this.pages = pages;
        return this;
    }

    public BookDetailBuilder withLanguages(String languages) {
        this.languages = languages;
        return this;
    }

    public BookDetailBuilder withFileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public BookDetailBuilder withFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
        return this;
    }

    public BookDetailBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public BookDetailBuilder withDriverDirectory(String driverDirectory) {
        this.driverDirectory = driverDirectory;
        return this;
    }

    public BookDetailBuilder withBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
        return this;
    }

    public BookDetailBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public BookDetailBuilder withLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
        return this;
    }

    public BookDetailBuilder withState(DownloadState state) {
        this.state = state;
        return this;
    }

    public BookDetailBuilder withBook(Book book) {
        this.book = book;
        return this;
    }

    public BookDetail build() {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setCreatedAt(createdAt);
        bookDetail.setId(id);
        bookDetail.setAuthor(author);
        bookDetail.setLastUpdatedAt(lastUpdatedAt);
        bookDetail.setPublicationDate(publicationDate);
        bookDetail.setVersionNo(versionNo);
        bookDetail.setPages(pages);
        bookDetail.setLanguages(languages);
        bookDetail.setFileSize(fileSize);
        bookDetail.setFileFormat(fileFormat);
        bookDetail.setCategory(category);
        bookDetail.setDriverDirectory(driverDirectory);
        bookDetail.setBookDescription(bookDescription);
        bookDetail.setImage(image);
        bookDetail.setLinkDownload(linkDownload);
        bookDetail.setState(state);
        bookDetail.setBook(book);
        return bookDetail;
    }
}
