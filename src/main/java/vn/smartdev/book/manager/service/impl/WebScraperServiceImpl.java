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
import vn.smartdev.book.manager.entity.DownloadState;
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

    /**
     *
     * @param linkContent
     */
    @Override
    public void scrapBookFromUrl(LinkContent linkContent){
        if(!Objects.isNull(linkContent)){
            log.info("Start scrapping boot from url : " + linkContent.getLinkUrl());
            taskExecutor.execute(() -> {
                try {
                    log.info("Thread in : " + linkContent.getLinkUrl() + " is running..");
                    openMainCategoryPage(linkContent);
                    log.info("save all book success with link : " + linkContent.getLinkUrl());
                    notificationService.pushNotify(linkContent.getLinkUrl(), null);
                } catch (IOException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                    notificationService.pushNotify(linkContent.getLinkUrl(), e.getMessage());
                }
            });
        }
    }

    private void openMainCategoryPage(LinkContent linkContent) throws IOException {
        int currentPage = 1;
        int limitQuantityBook = PropertyProvider.limitBookQuantityPerCategory;
        int currentBookDownloaded = 0;

        Document mainCategoryPage = Jsoup.connect(linkContent.getLinkUrl()).get();
        Elements allBookAtFirstPage = mainCategoryPage.getElementsByClass(AllITBooksAttributes.BOOK_TITLE_ELEMENT);

       while (allBookAtFirstPage.size() < limitQuantityBook){
           mainCategoryPage = Jsoup.connect(getNextPage(currentPage, linkContent.getLinkUrl(), mainCategoryPage)).get();
       }

        if(!allBookAtFirstPage.isEmpty()){
            for (int i = 0 ; i < allBookAtFirstPage.size() ; i ++){
                if(currentBookDownloaded <= limitQuantityBook){
                    Element bookElement = allBookAtFirstPage.get(i);

                    String bookDetailLink = getLinkDetail(bookElement);
                    String bookName = bookElement.text();

                    log.info("book name : " + bookName);
                    log.info("detail book link : " + bookDetailLink);

                    if(!bookService.isBookWasDownload(bookName)){
                        Book book = initBookFromName(bookName);
                        BookDetail  bookDetail = getBookDetailFromLink(bookDetailLink, bookName);

                        saveBookToDB(book, bookDetail);
                        currentBookDownloaded += 1;
                    }
                }
                if(currentBookDownloaded == limitQuantityBook){
                    break;
                }
            }
        }
    }

    private String getNextPage(int currentPage, String linkUrl, Document mainCategoryPage) {
        Elements nextPageElements = mainCategoryPage.getElementsByClass(AllITBooksAttributes.NEXT_PAGE_ELEMENT);

        return null;
    }

    private void saveBookToDB(Book book, BookDetail bookDetail) {
        Book bookSaved = bookService.saveBook(book);

        bookDetail.setBook(bookSaved);
        bookService.saveBookDetail(bookDetail);
    }

    private BookDetail getBookDetailFromLink(String bookDetailLink, String bookName) throws IOException {
        BookDetail bookDetail = new BookDetail();

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
        try{

            // TODO : need to check that category already exist ?
            // TODO : pass root folder id to parameter
            // TODO : save book to db with sate is completed
            googleDriverService.uploadFileToGoogleDrive("a", linkDownload, bookName);
            bookDetail = wrappingBookDetail(bookDetail);
        }catch (IOException e){
            log.info("There is an error while try to upload book to gg drive : " + e.getMessage());
            e.printStackTrace();
            bookDetail.setState(DownloadState.FAILED);
        }
        return bookDetail;
    }

    private BookDetail wrappingBookDetail(BookDetail bookDetail) {
        bookDetail.setState(DownloadState.COMPLETED);


        return bookDetail;
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
