package vn.smartdev.book.manager.service;

import vn.smartdev.book.manager.model.LinkContent;

import java.io.IOException;

public interface WebScraperService {
    void scrapBookFromUrl(LinkContent linkContent) throws IOException;
}
