package yoanBlog.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yoanBlog.bindingModel.ArticleBindingModel;
import yoanBlog.entity.Article;
import yoanBlog.entity.Category;
import yoanBlog.entity.Tag;
import yoanBlog.entity.User;
import yoanBlog.repository.ArticleRepository;
import yoanBlog.repository.CategoryRepository;
import yoanBlog.repository.TagRepository;
import yoanBlog.viewModels.ArticleViewModel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;


    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              CategoryRepository categoryRepository,
                              TagRepository tagRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;

    }

    @Override
    public Article getById(Integer id) {
        return this.articleRepository.findOne(id);
    }


    @Override
    public Article create(User author, ArticleBindingModel bindingModel) throws NotFoundException {
        Category category = this.categoryRepository.findOne(bindingModel.getCategoryId());
        HashSet<Tag> tags = this.findTagsFromString(bindingModel.getTagString());

        if (category == null) {
            throw new NotFoundException("Invalid Category ID!");
        }

        Article articleEntity = new Article(bindingModel.getTitle(),
                bindingModel.getContent(),
                author,
                category,
                tags);

        return this.articleRepository.saveAndFlush(articleEntity);
    }

    @Override
    public Article edit(Article article, ArticleBindingModel bindingModel) throws IOException, NotFoundException {
        HashSet<Tag> tags = this.findTagsFromString(bindingModel.getTagString());
        Category category = this.categoryRepository.findOne(bindingModel.getCategoryId());

        article.setTitle(bindingModel.getTitle());
        article.setContent(bindingModel.getContent());
        article.setTags(tags);
        article.setCategory(category);



        //Check for picture
        if (bindingModel.getPicture() != null) {
            article.setPicture(bindingModel.getPicture().getBytes());
        }

        return this.articleRepository.saveAndFlush(article);
    }

    @Override
    public void delete(Article article) {
        this.articleRepository.delete(article);
    }

    @Override
    public List<ArticleViewModel> getLastTwoArticles() {
        List<Article> articles = this.articleRepository.findTop2By();
        List<ArticleViewModel> articleViewModels = new ArrayList<>();


        for (Article article : articles) {
            articleViewModels.add(this.convertToViewModel(article));

        }
        return articleViewModels;
    }

    @Override
    public List<ArticleViewModel> getAllArticles() {
        List<Article> articleList = this.articleRepository.findAllByOrderByIdDesc();
        List<ArticleViewModel> articleViewModelList = new ArrayList<>();
        for (Article article : articleList) {
            articleViewModelList.add(this.convertToViewModel(article));
            //articleViewModelList.add(this.convertToViewModel(data);
        }
        return articleViewModelList;
    }

    @Override
    public ArticleViewModel convertToViewModel(Article article) {
        ArticleViewModel articleViewModel = new ArticleViewModel();
        articleViewModel.setId(article.getId());
        articleViewModel.setTitle(article.getTitle());
        articleViewModel.setContent(article.getContent());
        articleViewModel.setSummary(article.getSummary());
        articleViewModel.setAuthor(article.getAuthor().getFullName());
        articleViewModel.setTags(article.getTags());
        articleViewModel.setDate(this.getDateString(article.getCreationDate()));

        if (article.getPicture() != null) {
            String picture = Base64.getEncoder().encodeToString(article.getPicture());
            articleViewModel.setPicture(picture);
        }

        return articleViewModel;
    }

    private String getDateString(Date date){
        String pattern = "dd'TH' MMMM, yyyy";
        DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
        String todayAsString = df.format(date);

        return todayAsString;
    }

    private HashSet<Tag> findTagsFromString(String tagString) {
        HashSet<Tag> tags = new HashSet<>();

        String[] tagNames = tagString.split(",\\s*");

        for (String tagName : tagNames) {
            Tag currentTag = this.tagRepository.findByName(tagName);

            if (currentTag == null) {
                currentTag = new Tag(tagName);
                this.tagRepository.saveAndFlush(currentTag);
            }
            tags.add(currentTag);
        }
        return tags;
    }


}

