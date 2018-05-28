package vn.smartdev.book.manager.service;

import vn.smartdev.book.manager.entity.Book;
import vn.smartdev.book.manager.entity.BookDetail;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);
    BookDetail saveBookDetail(BookDetail bookDetail);
    boolean isBookWasDownloadWithCompletedOrFailedState(String name);
    Book updateBook(Book book);
    BookDetail updateBookDetail(BookDetail bookDetail);
    List<BookDetail> getAllBooks();
}
