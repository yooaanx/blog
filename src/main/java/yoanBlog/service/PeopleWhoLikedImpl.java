package yoanBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yoanBlog.entity.Article;
import yoanBlog.entity.PeopleWhoLiked;
import yoanBlog.repository.PeopleWhoLikedRepository;

import java.util.List;

@Service
public class PeopleWhoLikedImpl implements PeopleWhoLikedService {
    private final PeopleWhoLikedRepository peopleWhoLikedRepository;

    @Autowired
    public PeopleWhoLikedImpl(PeopleWhoLikedRepository peopleWhoLikedRepository) {
        this.peopleWhoLikedRepository = peopleWhoLikedRepository;
    }
    @Override
    public List<PeopleWhoLiked> getAllByArticle (Article article){
        return this.peopleWhoLikedRepository.findPeopleWhoLikedByArticle(article);
    }


}
