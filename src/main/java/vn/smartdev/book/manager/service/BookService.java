package vn.smartdev.book.manager.service;

import vn.smartdev.book.manager.entity.Book;
import vn.smartdev.book.manager.entity.BookDetail;
import vn.smartdev.book.manager.entity.DownloadState;

public interface BookService {
    Book saveBook(Book book);
    BookDetail saveBookDetail(BookDetail bookDetail);

    boolean isBookWithDownloadState(String name, DownloadState downloadState);
}