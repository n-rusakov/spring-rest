package com.example.springrest.mapper;

import com.example.springrest.entity.Comment;
import com.example.springrest.web.model.CommentListResponse;
import com.example.springrest.web.model.CommentResponse;
import com.example.springrest.web.model.UpsertCommentRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment requestToComment(UpsertCommentRequest request);

    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "news.id", target = "newsId")
    CommentResponse commentToResponse(Comment comment);

    List<CommentResponse> commentListToResponseList(List<Comment> comments);

    default CommentListResponse commentListToCommentListResponse(List<Comment> comments) {
        CommentListResponse response = new CommentListResponse();

        response.setComments(commentListToResponseList(comments));

        return response;
    }

}
