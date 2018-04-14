package vn.smartdev.book.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.smartdev.book.manager.entity.BookDetail;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, String> {

}
