package vn.smartdev.book.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.smartdev.book.manager.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{

}
