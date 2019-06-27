package yoanBlog.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yoanBlog.entity.Category;
import yoanBlog.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Integer id) throws NotFoundException {
        Category category = this.categoryRepository.findOne(id);
        if(category == null){
            throw new NotFoundException("Invalid Category!");
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAllByOrderByIdAsc();
    }
}
