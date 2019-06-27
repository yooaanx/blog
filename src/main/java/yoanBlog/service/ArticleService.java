package yoanBlog.service;

import javassist.NotFoundException;
import yoanBlog.bindingModel.ArticleBindingModel;
import yoanBlog.entity.Article;
import yoanBlog.entity.Category;
import yoanBlog.entity.User;
import yoanBlog.viewModels.ArticleViewModel;

import java.io.IOException;
import java.util.List;

public interface ArticleService {
    Article getById(Integer id);

    Article create(User author, ArticleBindingModel bindingModel) throws NotFoundException;
    Article edit(Article article, ArticleBindingModel bindingModel) throws IOException, NotFoundException;
    void delete(Article article);

    List<ArticleViewModel> getLastTwoArticles();
    List<ArticleViewModel> getAllArticles();

    ArticleViewModel convertToViewModel(Article article);




}
