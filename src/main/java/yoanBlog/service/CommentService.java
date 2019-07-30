package yoanBlog.service;

import yoanBlog.bindingModel.CommentBindingModel;
import yoanBlog.entity.Article;
import yoanBlog.entity.Comment;
import yoanBlog.entity.User;

import java.util.List;

public interface CommentService {
    List<Comment> getAllByArticle(Article article);

    Comment create(CommentBindingModel bindingModel, User author, Article article);
}
