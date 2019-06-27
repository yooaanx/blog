package yoanBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yoanBlog.entity.Article;
import yoanBlog.entity.Category;
import yoanBlog.repository.ArticleRepository;
import yoanBlog.repository.CategoryRepository;
import yoanBlog.service.ArticleService;
import yoanBlog.viewModels.ArticleViewModel;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final ArticleService articleService;


    @Autowired
    public HomeController(CategoryRepository categoryRepository,
                          ArticleService articleService) {
        this.categoryRepository = categoryRepository;
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(Model model){

        List<Category> categories = this.categoryRepository.findAll();
        List<ArticleViewModel> articles = this.articleService.getLastTwoArticles();
        List<ArticleViewModel> articleList = this.articleService.getAllArticles();

        model.addAttribute("articlesList", articleList);
        model.addAttribute("articles", articles);
        model.addAttribute("view", "home/home");
        model.addAttribute("home", true);
        model.addAttribute("category", categories);

        //return "base-layout";
        return "layout";
    }

    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/error/403")
    public String accessDenied(Model model){
        model.addAttribute("view", "error/403");

        return "base-layout";
    }

    @GetMapping("/category/{id}")
    public String listArticles(Model model, @PathVariable Integer id){
        model.addAttribute("view", "home/list-articles");
        if (!this.categoryRepository.exists(id)){
            return "redirect:/";
        }

        Category category = this.categoryRepository.findOne(id);
        Set<Article>articles =  category.getArticles();

        model.addAttribute("articles", articles);
        model.addAttribute("category", category);


        return "layout";
    }
}
