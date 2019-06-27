package yoanBlog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import yoanBlog.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {


    Tag findByName(String name);
}
