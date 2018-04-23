package vn.smartdev.book.manager.service;

import vn.smartdev.book.manager.entity.Book;
import vn.smartdev.book.manager.entity.BookDetail;

public interface BookService {
    Book saveBook(Book book);
    BookDetail saveBookDetail(BookDetail bookDetail);
    boolean isBookWasDownload(String name);
    Book updateBook(Book book);
    BookDetail updateBookDetail(BookDetail bookDetail);
}
