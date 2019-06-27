package yoanBlog.bindingModel;

import org.springframework.web.multipart.MultipartFile;
import yoanBlog.entity.Role;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UserBindingModel {
    @NotNull
    private String email;

    @NotNull
    private String fullName;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    private String authorInfo;

    private MultipartFile profilePicture;

    private List<Integer> roles;

    public UserBindingModel(List<Integer> roles) {
        this.roles = roles;
    }

    public UserBindingModel(){
        this.roles = new ArrayList<>();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Integer> getRoles(){
        return roles;
    }

    public void setRoles(List<Integer> roles){
        this.roles = roles;
    }
}
