package vn.smartdev.book.manager.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import vn.smartdev.book.manager.service.WebScraperService;

import java.io.IOException;

@Service
public class WebScraperServiceImpl implements WebScraperService {

    static final Log log = LogFactory.getFactory().getInstance(WebScraperServiceImpl.class);


    @Override
    public String getTitleFromUrl(String url) throws IOException {
        log.info("Getting title from url : " + url);
        Document document = Jsoup.connect(url).get();
        return document.title();
    }
}
