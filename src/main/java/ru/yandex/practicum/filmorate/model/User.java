package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class User {
    private Integer id;
    @NotNull(message = "Адрес электронной почты пользователя не может быть пустым.")
    @NotBlank(message = "Адрес электронной почты пользователя не может быть пустым.")
    @Email(message = "Некорректный адрес электронной почты")
    private String email;
    @NotNull(message = "Логин пользователя не может быть пустым.")
    @NotBlank(message = "Логин пользователя не может быть пустым.")
    private String login;

    private String name;
    @Past(message = "Дата рождения не может быть позже текущей.")
    private LocalDate birthday;
}