package vn.smartdev.book.manager.service;

public interface NotificationService {
    void pushNotify(String to, String subject, String text);
}
