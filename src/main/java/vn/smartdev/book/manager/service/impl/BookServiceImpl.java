package vn.smartdev.book.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.smartdev.book.manager.entity.Book;
import vn.smartdev.book.manager.entity.BookDetail;
import vn.smartdev.book.manager.entity.DownloadState;
import vn.smartdev.book.manager.repository.BookDetailRepository;
import vn.smartdev.book.manager.repository.BookRepository;
import vn.smartdev.book.manager.service.BookService;

import java.util.Objects;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDetailRepository bookDetailRepository;


    @Override
    public Book saveBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        return bookRepository.save(book);
    }

    @Override
    public BookDetail saveBookDetail(BookDetail bookDetail) {
        bookDetail.setId(UUID.randomUUID().toString());
        return bookDetailRepository.save(bookDetail);
    }

    @Override
    public boolean isBookWasDownload(String name) {
        BookDetail bookDetail = bookDetailRepository.findByBook_NameAndState(name, DownloadState.COMPLETED);
        return !Objects.isNull(bookDetail);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public BookDetail updateBookDetail(BookDetail bookDetail) {
        return bookDetailRepository.save(bookDetail);
    }
}
