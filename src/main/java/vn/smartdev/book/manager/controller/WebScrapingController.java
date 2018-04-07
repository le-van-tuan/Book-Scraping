package vn.smartdev.book.manager.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.smartdev.book.manager.model.EBook;
import vn.smartdev.book.manager.service.WebScraperService;
import vn.smartdev.book.manager.utils.AllITBooksAttributes;

import java.util.List;

@Controller
public class WebScrapingController {

    static final Log log = LogFactory.getFactory().getInstance(WebScrapingController.class);

    @Autowired
    private WebScraperService webScraperService;

    @RequestMapping(value = "/all-it-ebooks/java", method = RequestMethod.GET)
    public String getAllJavaBooks(ModelMap modelMap){

        log.info("Getting all java books from link : " + AllITBooksAttributes.JAVA_BOOK_LINKS);

        List<EBook> javaBooks = webScraperService.getListBooksFromUrl(AllITBooksAttributes.JAVA_BOOK_LINKS);
        modelMap.put("javaBooks", javaBooks);

        return "java-book";
    }
}
