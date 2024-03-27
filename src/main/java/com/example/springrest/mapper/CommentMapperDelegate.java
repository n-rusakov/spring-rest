package com.example.springrest.mapper;

import com.example.springrest.entity.Comment;
import com.example.springrest.service.impl.NewsService;
import com.example.springrest.service.impl.UserService;
import com.example.springrest.web.model.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper{

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;


    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setUser(userService.findById(request.getUserId()));
        comment.setNews(newsService.findById(request.getNewsId()));

        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsertCommentRequest request) {
        Comment comment = requestToComment(request);
        comment.setId(commentId);

        return comment;
    }

}
