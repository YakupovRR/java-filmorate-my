package ru.yandex.practicum.filmorate.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
public class UserValidator {

    public boolean isValid(User user) throws ValidationException {

        emailValidator(user.getEmail());

        loginValidator(user.getLogin());

        BirthdayValidator(user.getBirthday());

        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
            log.info("Имя для отображения пустое, в качестве имени будет использован логин.");
        }

        return true;

    }

    private boolean emailValidator(String email) throws ValidationException {

        if (!StringUtils.hasText(email)) {
            log.info("Пустой адрес электронной почты");
            throw new ValidationException("E-mail не может быть пустым.");
        }

        if (!email.contains("@")) {
            log.info("Неправильный формат электронной почты.");
            throw new ValidationException("E-mail должен содержать символ \"@\".");
        }

        return true;
    }

    private boolean loginValidator(String login) throws ValidationException {

        if (!StringUtils.hasText(login)) {
            log.info("Пустой логин.");
            throw new ValidationException("Логин не может быть пустым.");
        }

        if (login.contains(" ")) {
            log.info("Логин с пробелами.");
            throw new ValidationException("Логин не должен содержать пробелов");
        }

        return true;

    }

    private boolean BirthdayValidator(LocalDate birthdate) throws ValidationException {

        if (birthdate.isAfter(LocalDate.now())) {
            log.info("Дата рождения позже текущей.");
            throw new ValidationException("Дата рождения не может быть позже текущей");
        }

        return true;

    }

}