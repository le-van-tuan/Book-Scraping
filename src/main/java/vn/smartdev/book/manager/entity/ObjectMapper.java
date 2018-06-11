package vn.smartdev.book.manager.entity;

import java.io.Serializable;

public class ObjectMapper implements Serializable{

    private String bookId;

    private String bookName;

    private String bookDetailId;

    private String bookReferenceId;

    private String author;

    private String publicationDate;

    private String pages;

    private String languages;

    private String fileSize;

    private String fileFormat;

    private String category;

    private String driveDirectory;

    private String bookDescription;

    private String images;

    private String linkDownload;

    private String state;

    private String driveFileId;

    private String driveFileName;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDetailId() {
        return bookDetailId;
    }

    public void setBookDetailId(String bookDetailId) {
        this.bookDetailId = bookDetailId;
    }

    public String getBookReferenceId() {
        return bookReferenceId;
    }

    public void setBookReferenceId(String bookReferenceId) {
        this.bookReferenceId = bookReferenceId;
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

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
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

    public String getDriveDirectory() {
        return driveDirectory;
    }

    public void setDriveDirectory(String driveDirectory) {
        this.driveDirectory = driveDirectory;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
}
