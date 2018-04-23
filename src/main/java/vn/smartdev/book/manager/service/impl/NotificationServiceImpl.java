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

    /**
     *
     * @param subject
     * @param error
     */
    @Override
    public void pushNotify(String subject, String error) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(receiver);
        message.setSubject(subject);

        if(error == null){
         message.setText("All IT book in link " + subject + " was downloaded success.");
        }else{
            message.setText("There was an error while try to download IT book from link : " + subject + " , detail reason : " + error);
        }

        javaMailSender.send(message);
    }
}
