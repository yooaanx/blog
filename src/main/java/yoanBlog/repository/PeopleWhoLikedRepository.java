package yoanBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoanBlog.entity.Article;
import yoanBlog.entity.PeopleWhoLiked;

import java.util.List;

public interface PeopleWhoLikedRepository extends JpaRepository<PeopleWhoLiked, Integer> {

    List<PeopleWhoLiked> findPeopleWhoLikedByArticle(Article article);
}
