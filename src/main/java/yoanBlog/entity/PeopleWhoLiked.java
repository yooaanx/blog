package yoanBlog.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "likes")
public class PeopleWhoLiked {
    private Integer id;
    private User user;
    private Article article;

    public PeopleWhoLiked(Integer id, User user, Article article) {
        this.id = id;
        this.user = user;
        this.article = article;
    }

    public PeopleWhoLiked() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "article")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
