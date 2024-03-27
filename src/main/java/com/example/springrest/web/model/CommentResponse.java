package com.example.springrest.web.model;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentResponse {

    private Long id;

    private String text;

    private Instant createdAt;

    private Long newsId;

    private Long userId;

}
