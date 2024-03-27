package com.example.springrest.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertUserRequest {

    @NotBlank(message = "Имя пользователя должно быть заполнено")
    private String name;

}
