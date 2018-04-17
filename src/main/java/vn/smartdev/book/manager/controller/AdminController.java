package vn.smartdev.book.manager.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping(value = "/book-downloading")
public class AdminController {

    static final Log log = LogFactory.getFactory().getInstance(AdminController.class);

    @RequestMapping(value = "/admin/console")
    public String showAdminConsole(){
        log.info("Request to admin console");
        return "admin-console";
    }
}
