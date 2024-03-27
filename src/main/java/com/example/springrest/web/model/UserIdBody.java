package com.example.springrest.web.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserIdBody {

    @NotNull(message = "Необходимо передать id пользователя, совершающего действие")
    private Long userId;
}
