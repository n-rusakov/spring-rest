package com.example.springrest.web.model;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class NewsCommentsCountResponse {
    private Long id;

    private String title;

    private String text;

    private Instant createdAt;

    private Long categoryId;

    private Long userId;

    private Integer commentsCount;
}
