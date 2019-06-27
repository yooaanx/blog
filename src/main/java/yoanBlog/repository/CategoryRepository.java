package yoanBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoanBlog.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByOrderByIdAsc();
}
