package vn.smartdev.book.manager.controller;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.smartdev.book.manager.model.LinkContent;
import vn.smartdev.book.manager.provider.LoadPropertyProvider;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    static final Log log = LogFactory.getFactory().getInstance(AdminController.class);

    @RequestMapping(value = "/admin-console")
    public String showAdminConsole(ModelMap modelMap){
        List<LinkContent> linkContents = convertListLinksContentToMap(LoadPropertyProvider.ebookLinks);

        modelMap.put("linkContents", linkContents);

        log.info("Request to admin console");
        return "admin-console";
    }

    private List<LinkContent> convertListLinksContentToMap(List<String> ebookLinks) {
        List<LinkContent> linkContents = new ArrayList<>();
        ebookLinks.stream().forEach(ebl->{
            LinkContent linkContent = new LinkContent();

            linkContent.setLinkUrl(ebl.trim().toString());
            linkContent.setLinkTitle(getTitleFromLink(ebl.trim().toString()));

            linkContents.add(linkContent);
        });
        return linkContents;
    }

    private String getTitleFromLink(String s) {
        String newLink = s.substring(0, s.length()-1);
        int lastIndex = newLink.lastIndexOf("/");

        return newLink.substring(lastIndex + 1, newLink.length());
    }
}
