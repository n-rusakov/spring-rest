package com.example.springrest.web.controller;

import com.example.springrest.aop.CheckNewsUserId;
import com.example.springrest.entity.News;
import com.example.springrest.mapper.NewsMapper;
import com.example.springrest.service.impl.NewsService;
import com.example.springrest.web.filter.NewsFilter;
import com.example.springrest.web.model.NewsAllCommentsResponse;
import com.example.springrest.web.model.NewsListResponse;
import com.example.springrest.web.model.UpsertNewsRequest;
import com.example.springrest.web.model.UserIdBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;

    @GetMapping
    ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter filter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(
                newsService.findAllFiltered(filter)));
    }

    @GetMapping("/{id}")
    ResponseEntity<NewsAllCommentsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                newsMapper.newsToAllCommentsResponse(
                        newsService.findById(id)));
    }

    @PostMapping
    ResponseEntity<NewsAllCommentsResponse> insert(@RequestBody @Valid UpsertNewsRequest request) {
        News news = newsService.insert(newsMapper.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.newsToAllCommentsResponse(news));
    }

    @PutMapping("/{id}")
    //@CheckNewsUserId
    ResponseEntity<NewsAllCommentsResponse> update(
            @PathVariable Long id, @RequestBody UpsertNewsRequest request) {
        News news = newsService.update(newsMapper.requestToNews(id, request));

        return ResponseEntity.status(HttpStatus.OK)
                .body(newsMapper.newsToAllCommentsResponse(news));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id,
                                    @RequestBody @Valid UserIdBody userIdBody) {
        newsService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
