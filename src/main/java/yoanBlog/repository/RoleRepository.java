package yoanBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoanBlog.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
    List<Role> findAllByOrderByIdAsc();
}
