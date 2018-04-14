package vn.smartdev.book.manager.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.smartdev.book.manager.service.GoogleDriverService;
import vn.smartdev.book.manager.service.WebScraperService;

import java.io.IOException;

@Controller
@RequestMapping(value = "/book-downloading")
public class DownloadBookController {

    static final Log log = LogFactory.getFactory().getInstance(DownloadBookController.class);

    @Autowired
    private GoogleDriverService googleDriverService;

    @Autowired
    private WebScraperService webScraperService;

    @RequestMapping(value = "/home")
    public String showHomePage(ModelMap modelMap) throws IOException {

        return "home";
    }
}
