package vn.smartdev.book.manager.model;

import java.io.Serializable;

public class EBookResponseDetail extends EBookResponse implements Serializable {

    private String author;

    private String fileSize;

    private String fileFormat;

    private String pages;

    private String category;

    private String language;

    private String linkDownload;

    private EBookResponseDetail(EBookDetailBuilder eBookDetailBuilder) {
        super.setName(eBookDetailBuilder.name);
        this.author = eBookDetailBuilder.author;
        this.fileSize = eBookDetailBuilder.fileSize;
        this.fileFormat = eBookDetailBuilder.fileFormat;
        this.pages = eBookDetailBuilder.pages;
        this.category = eBookDetailBuilder.category;
        this.language = eBookDetailBuilder.language;
        this.linkDownload = eBookDetailBuilder.linkDownload;
    }

    public String getAuthor() {
        return author;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public String getPages() {
        return pages;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public static class EBookDetailBuilder {

        private String name;

        private String author;

        private String fileSize;

        private String fileFormat;

        private String pages;

        private String category;

        private String language;

        private String linkDownload;

        private EBookDetailBuilder withName(String name){
            this.name = name;
            return this;
        }

        public EBookDetailBuilder withAuthor(String authorName){
            this.author = authorName;
            return this;
        }

        public EBookDetailBuilder withFileSize(String fileSize){
            this.fileSize = fileSize;
            return this;
        }

        public EBookDetailBuilder withFileFormat(String fileFormat){
            this.fileFormat = fileFormat;
            return this;
        }

        public EBookDetailBuilder withPages(String pages){
            this.pages = pages;
            return this;
        }

        public EBookDetailBuilder withCategory(String category){
            this.category = category;
            return this;
        }

        public EBookDetailBuilder withLanguage(String language){
            this.language = language;
            return this;
        }

        public EBookDetailBuilder withLinkDownload(String linkDownload){
            this.linkDownload = linkDownload;
            return this;
        }

        public EBookResponseDetail build(){
            return new EBookResponseDetail(this);
        }
    }

    @Override
    public String toString() {
        return "EBookResponseDetail{" +
                "author='" + author + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileFormat='" + fileFormat + '\'' +
                ", pages='" + pages + '\'' +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", linkDownload='" + linkDownload + '\'' +
                '}';
    }
}
