package vn.smartdev.book.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.smartdev.book.manager.entity.BookDetail;
import vn.smartdev.book.manager.service.BookService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/admin-console")
    public String showAdminConsole() {
        return "admin-console";
    }

    @RequestMapping(value = "/books")
    public String getAllBooks(ModelMap modelMap){
        List<BookDetail> bookDetails = bookService.getAllBooks();

        modelMap.addAttribute("books", bookDetails);

        return "all-books";
    }
}
