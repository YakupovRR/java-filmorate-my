package ru.yandex.practicum.filmorate.validate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
@Service
public class ValidateUser {

    public ValidateUser() {
    }

    public boolean checkAllData(User user) {
        if (isCorrectEmail(user) && isCorrectLogin(user) && isCorrectBirthday(user)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCorrectEmail(User user) {
        if (!user.getEmail().isEmpty() && user.getEmail().contains("@")) {
            return true;
        } else {
            log.warn("Ошибка во входных данных. Электронная почта пустая или не содержит @");
            return false;
        }
    }

    private boolean isCorrectLogin(User user) {
        if (!user.getLogin().isEmpty() && !user.getLogin().contains(" ")) {
            return true;
        } else {
            log.warn("Ошибка во входных данных. Логин пустой или содержит пробелы");
            return false;
        }
    }

    private boolean isCorrectBirthday(User user) {
        if (user.getBirthday().isBefore(LocalDate.now())) {
            return true;
        } else {
            log.warn("Ошибка во входных данных. Дата рождения указана в будующем");
            return false;
        }
    }
}

