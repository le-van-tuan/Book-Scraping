package vn.smartdev.book.manager.exception;

public class TechnicalException extends RuntimeException {
    public TechnicalException(Exception ex) {
        super(ex);
    }
}
