package vn.smartdev.book.manager.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vn.smartdev.book.manager.controller.BookScrappingController;
import vn.smartdev.book.manager.model.LinkContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
@PropertySource(value = "classpath:it-ebook-link-content.properties")
public class PropertyProvider {
    static final Log log = LogFactory.getFactory().getInstance(BookScrappingController.class);

    public static int limitBookQuantityPerCategory;

    public static List<LinkContent> linkContents;

    @Autowired
    public PropertyProvider(@Value("${it-ebooks-list-links-content}") final String linksContent, @Value("${it-limit-quantity-books}") int number){
        log.info("Loading Ebook links...");
        List<String> ebookLinks = Arrays.asList(linksContent.trim().split(","));
        linkContents = convertListLinksContentToMap(ebookLinks);

        limitBookQuantityPerCategory = number;
        log.info("Ebook links was loaded success.");
    }

    private List<LinkContent> convertListLinksContentToMap(List<String> ebookLinks) {
        List<LinkContent> linkContents = new ArrayList<>();
        ebookLinks.stream().forEach(ebl->{
            LinkContent linkContent = new LinkContent();

            linkContent.setId(UUID.randomUUID().toString());
            linkContent.setLinkUrl(ebl.trim().toString());
            linkContent.setLinkTitle(ebl.trim().toString());
            linkContent.setSubmit(false);

            linkContents.add(linkContent);
        });
        return linkContents;
    }

    public static LinkContent getLinkContentById(String id) {
        try {
            return linkContents.stream().filter(linkContent -> linkContent.getId().equals(id)).findFirst().get();
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
