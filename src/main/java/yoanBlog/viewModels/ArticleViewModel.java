package yoanBlog.viewModels;

import yoanBlog.entity.Comment;
import yoanBlog.entity.Tag;
import java.util.Set;

public class ArticleViewModel {

    private Integer id;
    private String title;
    private String content;
    private String summary;
    private AuthorViewModel author;
    private String picture;
    private Set<Tag> tags;
    private String date;
    private Set<Comment> comments;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AuthorViewModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorViewModel author) {
        this.author = author;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
