package com.example.springrest.aop;

import com.example.springrest.entity.Comment;
import com.example.springrest.entity.News;
import com.example.springrest.exception.AccessDeniedException;
import com.example.springrest.service.impl.CommentService;
import com.example.springrest.service.impl.NewsService;
import com.example.springrest.web.model.UpsertCommentRequest;
import com.example.springrest.web.model.UpsertNewsRequest;
import com.example.springrest.web.model.UserIdBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class UserIdChecker {

    private final NewsService newsService;

    private final CommentService commentService;

    @Before("execution(* com.example.springrest.web.controller.NewsController.update(..))")
    public void checkNewsPost(JoinPoint jp){
        log.info("NewsController.update intercepted");

        Long newsId = (Long) jp.getArgs()[0];
        News news = newsService.findById(newsId);

        UpsertNewsRequest request = (UpsertNewsRequest) jp.getArgs()[1];
        Long userId = request.getUserId();

        if (!userId.equals(news.getUser().getId())) {
            throw new AccessDeniedException("Пользователь может редактировать только свои новости");
        }

    }

    @Before("execution(* com.example.springrest.web.controller.NewsController.deleteById(..))")
    public void checkNewsDelete(JoinPoint jp){
        log.info("NewsController.update intercepted");

        Long newsId = (Long) jp.getArgs()[0];
        News news = newsService.findById(newsId);

        UserIdBody request = (UserIdBody) jp.getArgs()[1];
        Long userId = request.getUserId();

        if (!userId.equals(news.getUser().getId())) {
            throw new AccessDeniedException("Пользователь может удалять только свои новости");
        }

    }


    @Before("execution(* com.example.springrest.web.controller.CommentController.update(..))")
    public void checkCommentPost(JoinPoint jp){
        log.info("CommentController.update intercepted");

        Long commentId = (Long) jp.getArgs()[0];
        Comment comment = commentService.findById(commentId);

        UpsertCommentRequest request = (UpsertCommentRequest) jp.getArgs()[1];
        Long userId = request.getUserId();

        if (!userId.equals(comment.getUser().getId())) {
            throw new AccessDeniedException("Пользователь может редактировать только свои комментарии");
        }

    }

    @Before("execution(* com.example.springrest.web.controller.CommentController.deleteById(..))")
    public void checkCommentDelete(JoinPoint jp){
        log.info("CommentController.update intercepted");

        Long commentId = (Long) jp.getArgs()[0];
        Comment comment = commentService.findById(commentId);

        UserIdBody request = (UserIdBody) jp.getArgs()[1];
        Long userId = request.getUserId();

        if (!userId.equals(comment.getUser().getId())) {
            throw new AccessDeniedException("Пользователь может удалять только свои комментарии");
        }

    }






}
