package vn.smartdev.book.manager.entity.builder;

import vn.smartdev.book.manager.entity.ObjectMapper;

public final class ObjectMapperBuilder {
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

    private ObjectMapperBuilder() {
    }

    public static ObjectMapperBuilder anObjectMapper() {
        return new ObjectMapperBuilder();
    }

    public ObjectMapperBuilder withBookId(String bookId) {
        this.bookId = bookId;
        return this;
    }

    public ObjectMapperBuilder withBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public ObjectMapperBuilder withBookDetailId(String bookDetailId) {
        this.bookDetailId = bookDetailId;
        return this;
    }

    public ObjectMapperBuilder withBookReferenceId(String bookReferenceId) {
        this.bookReferenceId = bookReferenceId;
        return this;
    }

    public ObjectMapperBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public ObjectMapperBuilder withPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public ObjectMapperBuilder withPages(String pages) {
        this.pages = pages;
        return this;
    }

    public ObjectMapperBuilder withLanguages(String languages) {
        this.languages = languages;
        return this;
    }

    public ObjectMapperBuilder withFileSize(String fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public ObjectMapperBuilder withFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
        return this;
    }

    public ObjectMapperBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public ObjectMapperBuilder withDriveDirectory(String driveDirectory) {
        this.driveDirectory = driveDirectory;
        return this;
    }

    public ObjectMapperBuilder withBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
        return this;
    }

    public ObjectMapperBuilder withImages(String images) {
        this.images = images;
        return this;
    }

    public ObjectMapperBuilder withLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
        return this;
    }

    public ObjectMapperBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public ObjectMapperBuilder withDriveFileId(String driveFileId) {
        this.driveFileId = driveFileId;
        return this;
    }

    public ObjectMapperBuilder withDriveFileName(String driveFileName) {
        this.driveFileName = driveFileName;
        return this;
    }

    public ObjectMapper build() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setBookId(bookId);
        objectMapper.setBookName(bookName);
        objectMapper.setBookDetailId(bookDetailId);
        objectMapper.setBookReferenceId(bookReferenceId);
        objectMapper.setAuthor(author);
        objectMapper.setPublicationDate(publicationDate);
        objectMapper.setPages(pages);
        objectMapper.setLanguages(languages);
        objectMapper.setFileSize(fileSize);
        objectMapper.setFileFormat(fileFormat);
        objectMapper.setCategory(category);
        objectMapper.setDriveDirectory(driveDirectory);
        objectMapper.setBookDescription(bookDescription);
        objectMapper.setImages(images);
        objectMapper.setLinkDownload(linkDownload);
        objectMapper.setState(state);
        objectMapper.setDriveFileId(driveFileId);
        objectMapper.setDriveFileName(driveFileName);
        return objectMapper;
    }
}
