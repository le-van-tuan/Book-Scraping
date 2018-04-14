package vn.smartdev.book.manager.service;

import vn.smartdev.book.manager.model.EBookResponse;
import vn.smartdev.book.manager.model.EBookResponseDetail;

import java.util.List;

public interface WebScraperService {
    List<EBookResponse> getListBooksFromUrl(String url);
    EBookResponseDetail getEbookDetail(String url);
}
