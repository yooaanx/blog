package yoanBlog.viewModels;

import yoanBlog.entity.Article;

import java.util.List;

public class PeopleWhoLikedViewModel {
    private Integer id;
    private List<Article> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
