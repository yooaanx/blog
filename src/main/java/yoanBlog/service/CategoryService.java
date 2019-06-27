package yoanBlog.service;

import javassist.NotFoundException;
import yoanBlog.entity.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Integer id) throws NotFoundException;

    List<Category> getAll();
}
