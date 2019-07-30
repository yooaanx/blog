package yoanBlog.service;

import yoanBlog.entity.Article;
import yoanBlog.entity.PeopleWhoLiked;

import java.util.List;

public interface PeopleWhoLikedService {
    List<PeopleWhoLiked> getAllByArticle(Article article);
}
