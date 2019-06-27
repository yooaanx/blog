package yoanBlog.service;

import javassist.NotFoundException;
import yoanBlog.bindingModel.UserBindingModel;
import yoanBlog.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User getCurrentUser();
    User getById(Integer id) throws NotFoundException;

    User create(UserBindingModel bindingModel) throws IOException;
    User edit(User user, UserBindingModel userBindingModel) throws IOException;
    void delete(User user);

    List<User> getAll();
}
