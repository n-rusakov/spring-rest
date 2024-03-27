package com.example.springrest.service.impl;

import com.example.springrest.entity.News;
import com.example.springrest.exception.EntityNotFoundException;
import com.example.springrest.repository.NewsRepository;
import com.example.springrest.repository.specification.NewsSpecification;
import com.example.springrest.service.CRUDService;
import com.example.springrest.utils.BeanUtils;
import com.example.springrest.web.filter.NewsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService implements CRUDService<News> {
    private final NewsRepository newsRepository;

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(
                        MessageFormat.format("Новость с id {0} не найдена", id)));
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public List<News> findAllFiltered(NewsFilter filter) {
        return newsRepository.findAll(NewsSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize()))
                    .getContent();
    }

    @Override
    public News insert(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        News existedNews = findById(news.getId());

        BeanUtils.copyNonNullProperties(news, existedNews);

        return newsRepository.save(existedNews);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
