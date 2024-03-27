package com.example.springrest.web.controller;

import com.example.springrest.entity.Comment;
import com.example.springrest.mapper.CommentMapper;
import com.example.springrest.service.impl.CommentService;
import com.example.springrest.web.model.CommentListResponse;
import com.example.springrest.web.model.CommentResponse;
import com.example.springrest.web.model.UpsertCommentRequest;
import com.example.springrest.web.model.UserIdBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    private final CommentMapper commentMapper;

    /*@GetMapping
    ResponseEntity<CommentListResponse> findAll(){
        return ResponseEntity.ok(
                commentMapper.commentListToCommentListResponse(
                        commentService.findAll()));
    } */

    @GetMapping("/{id}")
    ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentMapper.commentToResponse(
                        commentService.findById(id)));
    }

    @PostMapping
    ResponseEntity<CommentResponse> insert(@RequestBody @Valid UpsertCommentRequest request) {
        Comment comment = commentService.insert(
                commentMapper.requestToComment(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(comment));
    }

    @PutMapping("/{id}")
    ResponseEntity<CommentResponse> update(
            @PathVariable Long id, @RequestBody UpsertCommentRequest request) {
        Comment comment = commentService.update(
                commentMapper.requestToComment(id, request));

        return ResponseEntity.status(HttpStatus.OK).body(
                commentMapper.commentToResponse(comment));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id,
                                    @RequestBody @Valid UserIdBody userIdBody) {
        commentService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
