package vn.smartdev.book.manager.service.impl;

import com.google.api.services.drive.model.File;
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
import vn.smartdev.book.manager.entity.builder.BookDetailBuilder;
import vn.smartdev.book.manager.model.DriveFileType;
import vn.smartdev.book.manager.model.LinkContent;
import vn.smartdev.book.manager.provider.PropertyProvider;
import vn.smartdev.book.manager.service.BookService;
import vn.smartdev.book.manager.service.GoogleDriverService;
import vn.smartdev.book.manager.service.NotificationService;
import vn.smartdev.book.manager.service.WebScraperService;
import vn.smartdev.book.manager.utils.AllITBooksAttributes;

import java.io.IOException;
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
            log.info("Start scrapping books from url : " + linkContent.getLinkUrl());
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
        Elements allITBooks = getAllITBookElements(linkContent);

        if(!allITBooks.isEmpty()){
            for (Element bookElement : allITBooks) {
                String bookDetailLink = getLinkDetail(bookElement);
                String bookName = bookElement.text();

                if (!bookService.isBookWasDownload(bookName)) {
                    Book book = initBookFromName(bookName);
                    BookDetail bookDetail;
                    try {
                        bookDetail = getBookDetailFromLink(book, bookDetailLink);

                        File file = uploadBookToGoogleDrive(bookDetail);

                        bookDetail.setState(DownloadState.COMPLETED);
                        bookDetail.setDriveFileId(file.getId());
                        bookDetail.setDriveFileName(file.getName());

                        bookService.saveBookDetail(bookDetail);
                    } catch (IOException e) {
                        log.info("Occur some error while try do download book name : " + bookName);
                        e.printStackTrace();
                        log.info("Reason : " + e.getMessage());
                        bookDetail = new BookDetail();

                        bookDetail.setState(DownloadState.FAILED);
                        bookDetail.setBook(book);

                        bookService.saveBookDetail(bookDetail);
                    }
                }
            }
        }
    }

    private File uploadBookToGoogleDrive(BookDetail bookDetail) throws IOException {
        String categoryFolderId;

        if(!googleDriverService.isFolderNameExistInRootFolder(bookDetail.getCategory())){
            categoryFolderId = googleDriverService.createFolderIntoRootFolder(bookDetail.getCategory()).getId();
        }else{
            categoryFolderId = googleDriverService.getDriveFileId(bookDetail.getCategory(), DriveFileType.FOLDER);
        }
        return googleDriverService.uploadFileToGoogleDrive(categoryFolderId, bookDetail.getLinkDownload(), bookDetail.getBook().getName());
    }

    private Elements getAllITBookElements(LinkContent linkContent) throws IOException {
        log.info("Starting to get all book element for the required.");
        int currentPage = 1;
        int limitQuantityBook = PropertyProvider.limitBookQuantityPerCategory;

        Document mainCategoryPage = Jsoup.connect(linkContent.getLinkUrl()).get();

        Elements allITBooks = mainCategoryPage.getElementsByClass(AllITBooksAttributes.BOOK_TITLE_ELEMENT);

        if(!allITBooks.isEmpty()){
            while (allITBooks.size() < limitQuantityBook){
                String nextPage = getNextUrlPage(currentPage, mainCategoryPage);

                if(Objects.isNull(nextPage)){
                    break;
                } else {
                    mainCategoryPage = Jsoup.connect(nextPage).get();
                    Elements newPage = mainCategoryPage.getElementsByClass(AllITBooksAttributes.BOOK_TITLE_ELEMENT);

                    for (Element np : newPage) {
                        if (allITBooks.size() == limitQuantityBook) {
                            break;
                        }
                        allITBooks.add(np);
                    }
                    currentPage += 1;
                }
            }
        }
        return allITBooks;
    }

    private String getNextUrlPage(int currentPage, Document mainCategoryPage) {
        log.info("Starting to get next page : " + currentPage + 1);
        Elements nextPageElements = mainCategoryPage.getElementsByClass(AllITBooksAttributes.NEXT_PAGE_ELEMENT);

        if(nextPageElements.isEmpty()) return null;

        Elements pages = nextPageElements.select("a");
        for(Element page : pages){
            if(page.text().equals(String.valueOf(currentPage + 1))){
                return page.attr("href");
            }
        }
        return null;
    }

    private BookDetail getBookDetailFromLink(Book book, String bookDetailLink) throws IOException {
        BookDetail bookDetail;

        Document detailBookPage = Jsoup.connect(bookDetailLink).get();

        String linkDownload = getLinkDownloadFromDetailPage(detailBookPage);
        String linkCoverImage = getLinkCoverImageFromDetailPage(detailBookPage);
        String bookDescription = getBookDescription(detailBookPage);
        String author = null;
        String year = null;
        String pages = null;
        String languages = null;
        String fileSize = null;
        String fileFormat = null;
        String category = null;

        Element bookDetailParent = detailBookPage.getElementsByClass(AllITBooksAttributes.BOOK_DETAIL_ELEMENT).first();
        Element bookDetailChild = bookDetailParent.select("dl").first();

        Elements titleInformation = bookDetailChild.select("dt");
        Elements valueInformation = bookDetailChild.select("dd");

        for(int i = 0 ; i < titleInformation.size() ; i ++){
            Element title = titleInformation.get(i);

            if(title.text().equalsIgnoreCase(AllITBooksAttributes.AUTHOR_TITLE)){
                author = valueInformation.get(i).text();
            }else if(title.text().equalsIgnoreCase(AllITBooksAttributes.YEAR_TITLE)){
                year = valueInformation.get(i).text();
            }else if(title.text().equalsIgnoreCase(AllITBooksAttributes.PAGES_TITLE)){
                pages = valueInformation.get(i).text();
            }else if(title.text().equalsIgnoreCase(AllITBooksAttributes.LANGUAGES_TITLE)){
                languages = valueInformation.get(i).text();
            }else if(title.text().equalsIgnoreCase(AllITBooksAttributes.FILE_SIZE_TITLE)){
                fileSize = valueInformation.get(i).text();
            }else if(title.text().equalsIgnoreCase(AllITBooksAttributes.FILE_FORMAT_TITLE)){
                fileFormat = valueInformation.get(i).text();
            }else if(title.text().equalsIgnoreCase(AllITBooksAttributes.CATEGORY_TITLE)){
                category = valueInformation.get(i).text();
            }
        }
        bookDetail = wrappingBookDetail(book, linkDownload, linkCoverImage, author, year, pages, languages, fileSize, fileFormat, category, bookDescription);
        return bookDetail;
    }

    private BookDetail wrappingBookDetail(Book book, String linkDownload, String linkCoverImage, String author, String year, String pages, String languages, String fileSize, String fileFormat, String category, String bookDescription) {
        return BookDetailBuilder.aBookDetail()
                .withLinkDownload(linkDownload)
                .withImage(linkCoverImage)
                .withAuthor(author)
                .withPublicationDate(year)
                .withPages(Integer.valueOf(pages))
                .withLanguages(languages)
                .withFileSize(fileSize)
                .withFileFormat(fileFormat)
                .withCategory(category)
                .withBookDescription(bookDescription)
                .withState(DownloadState.NONE)
                .withBook(book)
                .build();
    }

    private String getBookDescription(Document detailBookPage) {
        StringBuilder bookDescription = new StringBuilder();

        Element coverImageElement = detailBookPage.getElementsByClass(AllITBooksAttributes.BOOK_DESCRIPTION_ENTRY).first();

        Elements contents = coverImageElement.select("p");

        contents.stream().forEach(ct->{
            bookDescription.append(ct.text());
            bookDescription.append("\n");
        });
        return bookDescription.toString();
    }

    private String getLinkCoverImageFromDetailPage(Document detailBookPage) {
        Element coverImageElement = detailBookPage.getElementsByClass(AllITBooksAttributes.COVER_IMAGE_TITLE).first();

        return coverImageElement.select("img").attr("src");
    }

    private String getLinkDownloadFromDetailPage(Document detailBookPage) {
        Element downloadLink = detailBookPage.getElementsByClass(AllITBooksAttributes.BOOK_DOWNLOAD_LINK_ELEMENT).first();
        String link =  downloadLink.select("a").attr("href");

        return link.replaceAll(" ","%20");
    }

    private String getLinkDetail(Element firstBook) {
        return firstBook.select("a").attr("href");
    }

    private Book initBookFromName(String text) {
        Book book = new Book();

        book.setName(text);

        return bookService.saveBook(book);
    }
}
