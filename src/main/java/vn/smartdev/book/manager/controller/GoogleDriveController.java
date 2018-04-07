package vn.smartdev.book.manager.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.smartdev.book.manager.model.FilesDriver;
import vn.smartdev.book.manager.service.GoogleDriverService;

import java.io.IOException;
import java.util.List;

@Controller
public class GoogleDriveController {

    static final Log log = LogFactory.getFactory().getInstance(GoogleDriveController.class);

    @Autowired
    private GoogleDriverService googleDriverService;

    @RequestMapping(value = "/home")
    public String showHomePage(ModelMap modelMap) throws IOException {
        List<FilesDriver> fileDriver = googleDriverService.getAllDriverFiles();
        modelMap.put("filesDriver", fileDriver);
        return "home";
    }

    @RequestMapping(value = "/folder/create", method = RequestMethod.POST)
    public String createFolder(@RequestParam("folderName") String folderName) throws IOException {
        googleDriverService.createFolder(folderName);
        log.info("Folder was created.");
        return "redirect:/home";
    }
}
