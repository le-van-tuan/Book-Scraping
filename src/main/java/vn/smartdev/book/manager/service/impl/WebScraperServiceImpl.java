package vn.smartdev.book.manager.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import vn.smartdev.book.manager.model.EBookResponse;
import vn.smartdev.book.manager.model.EBookResponseDetail;
import vn.smartdev.book.manager.provider.WebDriverProvider;
import vn.smartdev.book.manager.service.WebScraperService;
import vn.smartdev.book.manager.utils.AllITBooksAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraperServiceImpl implements WebScraperService {

    static final Log log = LogFactory.getFactory().getInstance(WebScraperServiceImpl.class);

    private WebDriver webDriver = WebDriverProvider.getWebDriver();

    /**
     *
     * @param url
     * @return
     */
    @Override
    public List<EBookResponse> getListBooksFromUrl(String url) {
        log.info("Getting list book from specify URL : " + url);
        webDriver.get(url);

        List<WebElement> javaBooksElements = webDriver.findElements(By.className(AllITBooksAttributes.BOOK_ENTRY_ELEMENT));

        List<EBookResponse> javaBooks = getJavaBookNameFromElements(javaBooksElements);

        return javaBooks;
    }

    /**
     *
     * @param url
     * @return
     */
    @Override
    public EBookResponseDetail getEbookDetail(String url) {

        return null;
    }


    /**
     *
     * @param javaBooksElements
     * @return
     */
    private List<EBookResponse> getJavaBookNameFromElements(List<WebElement> javaBooksElements) {
        List<EBookResponse> eBookResponses = new ArrayList<>();

        javaBooksElements.stream().forEach(jb->{
            EBookResponse javaBook = new EBookResponse();
            javaBook.setName(jb.getText());

            eBookResponses.add(javaBook);
        });
        return eBookResponses;
    }
}
