package yoanBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoanBlog.entity.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findTop5By();
    List<Article> findAllByOrderByIdDesc();
    List<Article> findAllByTitleContainingIgnoreCase(String criteria);
}
