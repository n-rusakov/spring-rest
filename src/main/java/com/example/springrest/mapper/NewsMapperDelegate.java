package com.example.springrest.mapper;

import com.example.springrest.entity.News;
import com.example.springrest.service.impl.CategoryService;
import com.example.springrest.service.impl.UserService;
import com.example.springrest.web.model.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class NewsMapperDelegate implements NewsMapper{

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        news.setTitle(request.getTitle());
        news.setText(request.getText());
        news.setCategory(categoryService.findById(request.getCategoryId()));
        news.setUser(userService.findById(request.getUserId()));

        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(newsId);

        return news;
    }

}
