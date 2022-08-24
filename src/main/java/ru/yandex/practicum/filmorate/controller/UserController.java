package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validators.UserValidator;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private Map<String, User> users = new ConcurrentHashMap<>();

    private UserValidator userValidator = new UserValidator();

    @PostMapping
    public User createUser(@RequestBody User user) throws ValidationException {

        userValidator.isValid(user);

        users.put(user.getEmail(), user);

        log.info("Создание пользователя - {}", user.getEmail());

        return users.get(user.getEmail());
    }

    @PutMapping
    public void updateUser(@RequestBody User user) throws ValidationException {

        userValidator.isValid(user);

        users.put(user.getEmail(), user);

        log.info("Обновление пользователя - {}", user.getEmail());

    }

    @GetMapping
    public List<User> findAll() {

        return new ArrayList<>(users.values());

    }

}