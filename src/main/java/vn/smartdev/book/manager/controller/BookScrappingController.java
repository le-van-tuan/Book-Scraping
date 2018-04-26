package vn.smartdev.book.manager.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.smartdev.book.manager.model.LinkContent;
import vn.smartdev.book.manager.provider.PropertyProvider;
import vn.smartdev.book.manager.service.WebScraperService;

import java.io.IOException;
import java.util.List;

@RestController
public class BookScrappingController {
    static final Log log = LogFactory.getFactory().getInstance(BookScrappingController.class);

    @Autowired
    private WebScraperService webScraperService;

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void downloadBook(@PathVariable(name = "id") String id) throws IOException {
        LinkContent linkContent = PropertyProvider.getLinkContentById(id);
        log.info("downloading book from url :" + linkContent.getLinkUrl());

        webScraperService.scrapBookFromUrl(linkContent);
    }

    @RequestMapping(value = "/init-data", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity initLinkContent(){
        List<LinkContent> linkContents = PropertyProvider.linkContents;
        return ResponseEntity.ok(linkContents);
    }
}
