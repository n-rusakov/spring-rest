package com.example.springrest.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsListResponse {
    private List<NewsCommentsCountResponse> news = new ArrayList<>();
}
