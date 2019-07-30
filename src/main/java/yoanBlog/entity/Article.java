package yoanBlog.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {

    private Integer id;
    private String title;
    private String content;
    private User author;
    private byte[] picture;
    private Date creationDate;
    private Category category;
    private Set<Tag> tags;
    private Set<Comment> comments;
    private Set<PeopleWhoLiked> peopleWhoLiked;



    public Article(String title, String content,
                   User author, Category category,HashSet<Tag> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.tags = tags;
        this.creationDate = new Date();
        this.comments = new HashSet<>();
        this.peopleWhoLiked = new HashSet<>();
    }

    public Article() {
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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "text", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(nullable = false, name = "authorId")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @ManyToOne
    @JoinColumn(nullable = false, name = "categoryId")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tags")
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Transient
    public String getSummary(){
        return  this.getContent().substring(0, this.getContent().length()/2) + "...";
    }

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    public Set<PeopleWhoLiked> getPeopleWhoLiked(){
        return peopleWhoLiked;}

    public void setPeopleWhoLiked(Set<PeopleWhoLiked> peopleWhoLiked){
        this.peopleWhoLiked = peopleWhoLiked;}



}
