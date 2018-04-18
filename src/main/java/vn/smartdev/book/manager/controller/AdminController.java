package vn.smartdev.book.manager.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.smartdev.book.manager.model.LinkContent;
import vn.smartdev.book.manager.provider.PropertyProvider;

import java.util.List;

@Controller
public class AdminController {

    static final Log log = LogFactory.getFactory().getInstance(AdminController.class);

    @RequestMapping(value = "/admin-console")
    public String showAdminConsole(ModelMap modelMap){
        List<LinkContent> linkContents = PropertyProvider.linkContents;
        modelMap.put("linkContents", linkContents);
        return "admin-console";
    }
}
