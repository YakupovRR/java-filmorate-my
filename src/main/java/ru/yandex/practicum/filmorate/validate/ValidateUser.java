package ru.yandex.practicum.filmorate.validate;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
public class ValidateUser {

    private final User user;

    public ValidateUser(User user) {
        this.user = user;
    }

    public boolean checkAllData() {
        if(isCorrectEmail() && isCorrectLogin() && isCorrectBirthday()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCorrectEmail() {
        if(!user.getEmail().isEmpty() && user.getEmail().contains("@")) {
            return true;
        } else {
            log.warn("Адрес электронной почты пуст/не содержит @");
            return false;
        }
    }

    private boolean isCorrectLogin() {
        if(!user.getLogin().isEmpty() && !user.getLogin().contains(" ")) {
            return true;
        } else {
            log.warn("Логин пустой или содержит пробелы");
            return false;
        }
    }

    private boolean isCorrectBirthday() {
        if(user.getBirthday().isBefore(LocalDate.now())) {
            return true;
        } else {
            log.warn("Дата рождения не может быть в будующем");
            return false;
        }
    }
}