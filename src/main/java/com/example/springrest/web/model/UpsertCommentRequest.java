package com.example.springrest.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class UpsertCommentRequest {

    @NotBlank(message = "Текст комментария должен быть заполнен")
    private String text;

    @NotNull(message = "Id новости должен быть указан")
    private Long newsId;

    @NotNull(message = "Id пользователя должен быть указан")
    private Long userId;

}
