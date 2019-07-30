package yoanBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yoanBlog.bindingModel.CommentBindingModel;
import yoanBlog.entity.Article;
import yoanBlog.entity.Comment;
import yoanBlog.entity.User;
import yoanBlog.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllByArticle(Article article) {
        return this.commentRepository.findCommentByArticleOrderByIdDesc(article);
    }

    @Override
    public Comment create(CommentBindingModel bindingModel, User author, Article article) {
        Comment comment = new Comment(
                bindingModel.getContent(),
                author,
                article
        );
        return this.commentRepository.saveAndFlush(comment);
    }
}
