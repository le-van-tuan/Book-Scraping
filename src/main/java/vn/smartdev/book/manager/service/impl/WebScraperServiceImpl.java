package vn.smartdev.book.manager.service.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import vn.smartdev.book.manager.model.EBook;
import vn.smartdev.book.manager.provider.WebDriverProvider;
import vn.smartdev.book.manager.service.WebScraperService;
import vn.smartdev.book.manager.utils.AllITBooksAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraperServiceImpl implements WebScraperService {

    private WebDriver webDriver = WebDriverProvider.getWebDriver();

    @Override
    public List<EBook> getListBooksFromUrl(String url) {
        webDriver.get(url);

        List<WebElement> javaBooksElements = webDriver.findElements(By.className(AllITBooksAttributes.BOOK_ENTRY_ELEMENT));

        List<EBook> javaBooks = getJavaBookNameFromElements(javaBooksElements);

        return javaBooks;
    }

    private List<EBook> getJavaBookNameFromElements(List<WebElement> javaBooksElements) {
        List<EBook> eBooks = new ArrayList<>();

        javaBooksElements.stream().forEach(jb->{
            EBook javaBook = new EBook();
            javaBook.setName(jb.getText());

            eBooks.add(javaBook);
        });
        return eBooks;
    }
}
