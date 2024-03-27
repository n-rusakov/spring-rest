package com.example.springrest.web.filter;

import com.example.springrest.validation.NewsFilterValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@NewsFilterValid
public class NewsFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private Long categoryId;

    private Long userId;

    private Instant createdBefore;

}
