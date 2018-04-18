package vn.smartdev.book.manager.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vn.smartdev.book.manager.controller.BookScrappingController;

import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:it-ebook-link-content.properties")
public class LoadPropertyProvider {
    static final Log log = LogFactory.getFactory().getInstance(BookScrappingController.class);

    public static List<String> ebookLinks;

    @Autowired
    public LoadPropertyProvider(@Value("${it-ebooks-list-links-content}") final String linksContent){
        log.info("Loading Ebook links...");
        ebookLinks = Arrays.asList(linksContent.trim().split(","));
        log.info("Ebook links was loaded success.");
    }
}
