package vn.smartdev.book.manager.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.smartdev.book.manager.service.WebScraperService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/book-downloading")
public class BookScrappingController {
    static final Log log = LogFactory.getFactory().getInstance(BookScrappingController.class);

    @Autowired
    private WebScraperService webScraperService;

    @RequestMapping(value = "/testing")
    @ResponseBody
    public void testing() throws IOException {
        log.info("angular js was contact to me!");
        log.info("Title from google website : " + webScraperService.getTitleFromUrl("http://google.com"));
    }
}
