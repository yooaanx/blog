package yoanBlog.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yoanBlog.bindingModel.UserBindingModel;
import yoanBlog.entity.User;
import yoanBlog.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");

        return "layout";
    }

    @PostMapping("/register")
    public String registerProcess(UserBindingModel userBindingModel) throws IOException {
        if (!userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword())) {
            return "redirect:/register";
        }
        this.userService.create(userBindingModel);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "user/login");

        return "layout";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model) {
        User user = this.userService.getCurrentUser();

        model.addAttribute("user", user);
        model.addAttribute("view", "user/profile");

        return "layout";
    }

    @GetMapping("/user/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String edit(Model model) throws NotFoundException {
        User user = this.userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("view", "user/edit");


        return "layout";
    }

    @PostMapping("/user/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editProcess(@PathVariable Integer id,UserBindingModel userBindingModel) throws IOException, NotFoundException {
        User user = this.userService.getById(id);
        this.userService.edit(user, userBindingModel);

        return "redirect:/";
    }
}
