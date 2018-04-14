package vn.smartdev.book.manager.provider;

import com.google.api.client.util.Value;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.smartdev.book.manager.service.impl.GoogleDriverServiceImpl;

import java.util.Objects;

public class WebDriverProvider {

    static final Log log = LogFactory.getFactory().getInstance(GoogleDriverServiceImpl.class);

    @Value("${web-driver.chrome.name}")
    private static String WEB_DRIVER_NAME;

    @Value("${web-driver.chrome.path}")
    private static String WEB_DRIVER_PATH;

    private static WebDriverProvider instance;

    private static WebDriver webDriver;

    static void setup() {
        log.info("Setup web-driver....");
        System.setProperty(WEB_DRIVER_NAME, WEB_DRIVER_PATH);
        log.info("Web-driver was setup success.");
    }

    private WebDriverProvider(){
        try {
            webDriver = new ChromeDriver();
        }catch (Exception e){
            log.error("Can not start WebDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static WebDriver getWebDriver(){
        if(Objects.isNull(instance)){
            instance = new WebDriverProvider();
            return webDriver;
        }
        return webDriver;
    }

    public static void closeWebDriver(){
        webDriver.close();
    }
}
