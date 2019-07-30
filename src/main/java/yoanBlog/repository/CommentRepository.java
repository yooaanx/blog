package yoanBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoanBlog.entity.Article;
import yoanBlog.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

    List<Comment> findCommentByArticleOrderByIdDesc(Article article);

}
