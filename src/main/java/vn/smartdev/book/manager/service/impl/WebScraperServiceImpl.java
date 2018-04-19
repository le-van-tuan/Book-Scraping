package vn.smartdev.book.manager.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import vn.smartdev.book.manager.entity.Book;
import vn.smartdev.book.manager.entity.BookDetail;
import vn.smartdev.book.manager.model.LinkContent;
import vn.smartdev.book.manager.provider.PropertyProvider;
import vn.smartdev.book.manager.service.BookService;
import vn.smartdev.book.manager.service.GoogleDriverService;
import vn.smartdev.book.manager.service.NotificationService;
import vn.smartdev.book.manager.service.WebScraperService;
import vn.smartdev.book.manager.utils.AllITBooksAttributes;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

@Service
public class WebScraperServiceImpl implements WebScraperService {

    static final Log log = LogFactory.getFactory().getInstance(WebScraperServiceImpl.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private GoogleDriverService googleDriverService;

    @Autowired
    private BookService bookService;

    @Override
    public void scrapBookFromUrl(LinkContent linkContent){
        if(!Objects.isNull(linkContent)){
            log.info("Start scrapping boot from url : " + linkContent.getLinkUrl());
            taskExecutor.execute(() -> {
                try {
                    log.info("Thread in : " + linkContent.getLinkUrl() + " is running..");
                    openMainCategoryPage(linkContent);
                } catch (IOException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }

    private void openMainCategoryPage(LinkContent linkContent) throws IOException {
        int limitQuantityBook = PropertyProvider.limitBookQuantityPerCategory;

        Document mainCategoryPage = Jsoup.connect(linkContent.getLinkUrl()).get();

        for (int i = 0 ; i < limitQuantityBook ; i ++){
            Elements allBookAtFirstPage = mainCategoryPage.getElementsByClass(AllITBooksAttributes.BOOK_TITLE_ELEMENT);

            Element firstBook = allBookAtFirstPage.first();

            String bookDetailLink = getLinkDetail(firstBook);
            String bookName = firstBook.text();

            log.info("book name : " + bookName);
            log.info("detail book link : " + bookDetailLink);



            Book book = initBookFromName(firstBook.text());
            BookDetail  bookDetail = getBookDetailFromLink(bookDetailLink, bookName);

            break;
        }
    }

    private BookDetail getBookDetailFromLink(String bookDetailLink, String bookName) throws IOException {
        Document detailBookPage = Jsoup.connect(bookDetailLink).get();

        String linkDownload = getLinkDownloadFromDetailPage(detailBookPage);

        Element bookDetailParent = detailBookPage.getElementsByClass(AllITBooksAttributes.BOOK_DETAIL_ELEMENT).first();

        Element bookDetailChild = bookDetailParent.select("dl").first();

        Iterator<Element> titleInformation = bookDetailChild.select("dt").iterator();
        Iterator<Element> valueInformation = bookDetailChild.select("dd").iterator();

        while (titleInformation.hasNext()){
            Element title = titleInformation.next();
            Element value = valueInformation.next();

            log.info("information - " + title.text() + " " + value.text());
        }

        googleDriverService.uploadFileToGoogleDrive(linkDownload, bookName);
        return null;
    }

    private String getLinkDownloadFromDetailPage(Document detailBookPage) {
        Element downloadLink = detailBookPage.getElementsByClass(AllITBooksAttributes.BOOK_DOWNLOAD_LINK_ELEMENT).first();
        String link =  downloadLink.select("a").attr("href").toString();

        return link.replaceAll(" ","%20");
    }

    private String getLinkDetail(Element firstBook) {
        return firstBook.select("a").attr("href").toString();
    }

    private Book initBookFromName(String text) {
        Book book = new Book();

        book.setName(text);

        return book;
    }
}
