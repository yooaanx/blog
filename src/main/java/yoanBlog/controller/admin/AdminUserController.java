package yoanBlog.controller.admin;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yoanBlog.bindingModel.UserEditBindingModel;
import yoanBlog.entity.Role;
import yoanBlog.entity.User;
import yoanBlog.repository.ArticleRepository;
import yoanBlog.service.RoleService;
import yoanBlog.service.UserService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminUserController(UserService userService,
                               RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String listUsers(Model model) {
        List<User> users = this.userService.getAll();

        model.addAttribute("users", users);
        model.addAttribute("view", "admin/user/list");

        return "base-layout";
    }

    @GetMapping("/edit/{id}")
    public String editUserPage(@PathVariable Integer id, Model model) throws NotFoundException {
        User user = this.userService.getById(id);
        List<Role> roles = this.roleService.getAllRoles();

        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        model.addAttribute("view", "admin/user/edit");

        return "base-layout";
    }

   @PostMapping("/edit/{id}")
    public String editProcess(@PathVariable Integer id, UserEditBindingModel userBindingModel) throws NotFoundException, IOException {
        User user = this.userService.getById(id);
        this.userService.edit(user, userBindingModel);

        return "redirect:/admin/users/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) throws NotFoundException {
        User user = this.userService.getById(id);

        model.addAttribute("user", user);
        model.addAttribute("view", "admin/user/delete");

        return "base-layout";
    }

    @PostMapping("/delete/{id}")
    public String deleteProcess(@PathVariable Integer id) throws NotFoundException {
        User user = this.userService.getById(id);
        this.userService.delete(user);

        return "redirect:/admin/users/";
    }
}
