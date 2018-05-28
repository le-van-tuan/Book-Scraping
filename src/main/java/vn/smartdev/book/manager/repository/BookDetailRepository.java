package vn.smartdev.book.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.smartdev.book.manager.entity.BookDetail;
import vn.smartdev.book.manager.entity.DownloadState;

import java.util.List;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, String> {
    BookDetail findByBook_NameAndStateIsIn(String bookName, List<DownloadState> downloadState);
}
