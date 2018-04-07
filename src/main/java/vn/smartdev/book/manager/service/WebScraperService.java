package vn.smartdev.book.manager.service;

import vn.smartdev.book.manager.model.EBook;

import java.util.List;

public interface WebScraperService {
    List<EBook> getListBooksFromUrl(String url);
}
