package com.example.springrest.mapper;

import com.example.springrest.entity.Comment;
import com.example.springrest.entity.News;
import com.example.springrest.web.model.NewsAllCommentsResponse;
import com.example.springrest.web.model.NewsCommentsCountResponse;
import com.example.springrest.web.model.NewsListResponse;
import com.example.springrest.web.model.UpsertNewsRequest;
import org.mapstruct.*;

import java.util.List;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {CommentMapper.class})
public interface NewsMapper {

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsertNewsRequest request);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "userId", source = "user.id")
    NewsAllCommentsResponse newsToAllCommentsResponse(News news);


    @Named("listSize")
    default int listSize(List<?> list) {
        return list.size();
    }

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "commentsCount", source = "comments", qualifiedByName = "listSize")
    NewsCommentsCountResponse newsToCommentsCountResponse(News news);

    @Named("newsListToResponseList")
    List<NewsCommentsCountResponse> newsListToResponseList(List<News> news);


    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        NewsListResponse response = new NewsListResponse();

        response.setNews(newsListToResponseList(news));

        return response;
    }

}
