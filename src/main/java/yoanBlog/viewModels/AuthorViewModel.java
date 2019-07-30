package yoanBlog.viewModels;

public class AuthorViewModel {
    private Integer id;
    private String fullName;
    private String picture;

    public AuthorViewModel(Integer id, String fullName, String picture) {
        this.id = id;
        this.fullName = fullName;
        this.picture = picture;
    }

    public AuthorViewModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
