package com.example.springrest.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertCategoryRequest {

    @NotBlank(message = "Название категории должно быть заполнено")
    private String title;
}
