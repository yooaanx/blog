package yoanBlog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    private Integer id;
    private String content;
    private Date creationDate;
    private User author;
    private Article article;

    public Comment(String content,
                   User author,
                   Article article) {
        this.content = content;
        this.author = author;
        this.article = article;
        this.creationDate = new Date();
    }

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(columnDefinition = "text", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne
    @JoinColumn(nullable = false, name = "authorId")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
