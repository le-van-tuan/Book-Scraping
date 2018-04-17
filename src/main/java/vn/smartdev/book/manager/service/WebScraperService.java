package vn.smartdev.book.manager.service;

import vn.smartdev.book.manager.model.EBookResponse;
import vn.smartdev.book.manager.model.EBookResponseDetail;

import java.io.IOException;
import java.util.List;

public interface WebScraperService {
    String getTitleFromUrl(String url) throws IOException;
}
