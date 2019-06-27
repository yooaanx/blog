package yoanBlog.bindingModel;

import java.util.ArrayList;
import java.util.List;

public class UserEditBindingModel extends UserBindingModel {

    private List<Integer> roles;

    public UserEditBindingModel(List<Integer> roles) {
        this.roles = roles;
    }

    public UserEditBindingModel(){
        this.roles = new ArrayList<>();
    }

    public List<Integer> getRoles(){
        return roles;
    }

    public void setRoles(List<Integer> roles){
        this.roles = roles;
    }



}
