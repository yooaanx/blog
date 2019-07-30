package yoanBlog.controller;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yoanBlog.bindingModel.ArticleBindingModel;
import yoanBlog.bindingModel.CommentBindingModel;
import yoanBlog.entity.*;
import yoanBlog.service.*;
import yoanBlog.viewModels.ArticleViewModel;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CommentService commentService;
    private final PeopleWhoLikedService peopleWhoLikedService;

    @Autowired
    public ArticleController(ArticleService articleService,
                             UserService userService,
                             CategoryService categoryService,
                             CommentService commentService,
                             PeopleWhoLikedService peopleWhoLikedService) {
        this.articleService = articleService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.peopleWhoLikedService = peopleWhoLikedService;
    }

    @GetMapping("/article/create")
    @PreAuthorize("isAuthenticated()")
    public String create(Model model){
        List<Category> categories = this.categoryService.getAll();
        Date date = new Date();



        model.addAttribute("categories", categories);
        model.addAttribute("view", "article/create");
        model.addAttribute("date", date);



        return "layout";
    }

    @PostMapping("/article/create")
    @PreAuthorize("isAuthenticated()")
    public String createProcess(ArticleBindingModel articleBindingModel) throws NotFoundException {
        User author = this.userService.getCurrentUser();
        this.articleService.create(author, articleBindingModel);

        return "redirect:/";
    }

    @GetMapping("/article/{id}")
    public String details(Model model, @PathVariable Integer id){
        Article article = this.articleService.getById(id);
        User author = article.getAuthor();
        ArticleViewModel articleViewModel = this.articleService.convertToViewModel(article);
        List<Category> categories = this.categoryService.getAll();
        List<Comment> comments = this.commentService.getAllByArticle(article);

        model.addAttribute("user", author);
        model.addAttribute("categories", categories);
        model.addAttribute("comments", comments);
        model.addAttribute("article", articleViewModel);
        model.addAttribute("view", "article/new/details");

        return "layout";
    }

    @GetMapping("/article/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String edit(@PathVariable Integer id, Model model){

        Article article = this.articleService.getById(id);
        List<Category> categories = this.categoryService.getAll();

        String tagString =article.getTags()
                .stream()
                .map(Tag::getName)
                .collect(Collectors
                        .joining(", "));

        model.addAttribute("view", "article/edit");
        model.addAttribute("article", article);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tagString);


        if (!isUserAuthorOrAdmin(article)){
            return "redirect:/article/" + id;
        }

        return "layout";
    }

    @PostMapping("/article/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editProcess(@PathVariable Integer id, ArticleBindingModel articleBindingModel) throws IOException, NotFoundException {
        Article article = this.articleService.getById(id);
        if (!isUserAuthorOrAdmin(article)){
            return "redirect:/article/" + id;
        }

        this.articleService.edit(article, articleBindingModel);
        return "redirect:/article/" + article.getId();
    }

    @GetMapping("/article/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String delete (Model model, @PathVariable Integer id){
        Article article = this.articleService.getById(id);
        model.addAttribute("article", article);
        model.addAttribute("view", "article/delete");

        if (!isUserAuthorOrAdmin(article)){
            return "redirect:/article/" + id;
        }

        return "base-layout";
    }

    @PostMapping("/article/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteProcess(@PathVariable Integer id){

        Article article = this.articleService.getById(id);

        this.articleService.delete(article);

        if (!isUserAuthorOrAdmin(article)){
            return "redirect:/article/" + id;
        }

        return "redirect:/";
    }

    @PostMapping("/article/search")
    public String searchArticle(@RequestParam String criteria, Model model){
        List<ArticleViewModel> articles = this.articleService.search(criteria);

        model.addAttribute("articles", articles);
        model.addAttribute("view", "article/search");
        return "layout";
    }

    private boolean isUserAuthorOrAdmin(Article article){
        ArticleViewModel articleViewModel = this.articleService.convertToViewModel(article);

        User userEntity = this.userService.getCurrentUser();

        return userEntity.isAdmin() || userEntity.isAuthor(articleViewModel);
    }

    @PostMapping("/article/{id}/comment/create")
    @PreAuthorize("isAuthenticated()")
    public String createComment(@PathVariable("id") Integer id, CommentBindingModel bindingModel){
        User author = this.userService.getCurrentUser();
        Article article = this.articleService.getById(id);
        this.commentService.create(bindingModel, author, article);
        return "redirect:/article/" + id;
    }

    @PostMapping("/article/{id}")
    @PreAuthorize("isAuthenticated()")
    public String showLike (@PathVariable("id") Integer id){
        User user = this.userService.getCurrentUser();
        Article article = this.articleService.getById(id);
        this.peopleWhoLikedService.getAllByArticle(article);

        return "redirect:/article/ + id";
    }


}
