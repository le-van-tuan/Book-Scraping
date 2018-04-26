package vn.smartdev.book.manager.model;

import java.io.Serializable;

public class LinkContent implements Serializable {

    private String id;

    private String linkTitle;

    private String linkUrl;

    private boolean isSubmit;

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public String toString() {
        return "LinkContent{" +
                "id='" + id + '\'' +
                ", linkTitle='" + linkTitle + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }
}
