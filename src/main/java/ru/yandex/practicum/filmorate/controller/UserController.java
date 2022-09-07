package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.validate.ValidateUser;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();
    private int id = 0;
    ValidateUser validate = new ValidateUser();

    @GetMapping
    public Collection<User> getUsers() {
        log.info("Количество пользователей: {}", users.size());
        return users.values();
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws ValidationException {
        validate.validateUser(user);
        if (users.containsKey(user.getId())) {
            throw new ValidationException("Пользователь с электронной почтой " +
                    user.getEmail() + " уже зарегистрирован.");
        } else {
            user.setId(++id);
            users.put(user.getId(), user);
            log.info("Пользователь с адресом электронной почты {} создан", user.getEmail());
        }
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws ValidationException {
        validate.validateUser(user);
        if (!users.containsKey(user.getId())) {
            createUser(user);
        } else {
            users.put(user.getId(), user);
            log.info("Пользователь с адресом электронной почты {} обновлен", user.getEmail());
        }
        return user;
    }
}