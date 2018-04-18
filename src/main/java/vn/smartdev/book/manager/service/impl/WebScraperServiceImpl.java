package vn.smartdev.book.manager.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import vn.smartdev.book.manager.model.LinkContent;
import vn.smartdev.book.manager.service.GoogleDriverService;
import vn.smartdev.book.manager.service.NotificationService;
import vn.smartdev.book.manager.service.WebScraperService;

import java.io.IOException;
import java.util.Objects;

@Service
public class WebScraperServiceImpl implements WebScraperService {

    static final Log log = LogFactory.getFactory().getInstance(WebScraperServiceImpl.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private GoogleDriverService googleDriverService;

    @Override
    public void scrapBookFromUrl(LinkContent linkContent){
        if(!Objects.isNull(linkContent)){
            log.info("Getting title from url : " + linkContent.getLinkUrl());
            taskExecutor.execute(() -> {
                try {
                    log.info("Thread in : " + linkContent.getLinkUrl() + " is running..");
                    Document document = Jsoup.connect(linkContent.getLinkUrl()).get();
                    log.info("title from link : " + linkContent.getLinkUrl() + " is : " + document.title());
                } catch (IOException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }
}
