package vn.smartdev.book.manager.model;

import java.io.Serializable;

public class LinkContent implements Serializable {
    private String linkTitle;

    private String linkUrl;

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
                "linkTitle='" + linkTitle + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                '}';
    }
}
