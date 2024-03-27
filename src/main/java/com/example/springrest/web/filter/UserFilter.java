package com.example.springrest.web.filter;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserFilter {

    @NotNull(message = "Размер страницы pageSize должен быть указан")
    private Integer pageSize;

    @NotNull(message = "Номер страницы pageNumber должен быть указан")
    private Integer pageNumber;
}
