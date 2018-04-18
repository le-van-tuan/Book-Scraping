package vn.smartdev.book.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vn.smartdev.book.manager.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.receiver.username}")
    private String receiver;

    @Override
    public void pushNotify(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
