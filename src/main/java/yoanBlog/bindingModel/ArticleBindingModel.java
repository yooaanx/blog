package yoanBlog.bindingModel;

import org.springframework.web.multipart.MultipartFile;
import yoanBlog.entity.Category;

import javax.validation.constraints.NotNull;

public class ArticleBindingModel {

    @NotNull
    private String title;

    @NotNull
    private String content;
    private MultipartFile picture;
    private Integer categoryId;
    private Category category;
    private String tagString;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public String getTagString() {
        return tagString;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }
}
