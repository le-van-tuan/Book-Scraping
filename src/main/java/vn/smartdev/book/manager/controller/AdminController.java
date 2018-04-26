package vn.smartdev.book.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin-console")
    public String showAdminConsole() {
        return "admin-console";
    }
}
