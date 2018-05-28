package vn.smartdev.book.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.smartdev.book.manager.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, String> {

}

