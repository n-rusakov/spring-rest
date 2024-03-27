package com.example.springrest.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpsertNewsRequest {

    @NotBlank(message = "Заголовок новости должен быть заполнен")
    private String title;

    @NotBlank(message = "Текст новости должен быть заполнен")
    private String text;

    @NotNull(message = "Id категории должен быть указан")
    private Long categoryId;

    @NotNull(message = "Id пользователя должен быть указан")
    private Long userId;

}
