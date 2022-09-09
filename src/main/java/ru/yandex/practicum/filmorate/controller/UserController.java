package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.InputDataException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.validate.ValidateUser;

import java.util.HashSet;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;
    private final ValidateUser validateUser;

    private static int id = 0;
    @Autowired
    public UserController(UserService userService, ValidateUser validateUser) {
        this.userService = userService;
        this.validateUser = validateUser;
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        log.info("Получен запрос к эндпоинту: GET /users");
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int id) {
        log.info("Получен запрос к эндпоинту: GET /users/{id}");
        if(!userService.isContainsUser(id)) {
            log.warn("Пользователь с таким id не найден, id=" + id);
            throw new InputDataException("Пользователь с таким id не найден");
        }
        return userService.getUserById(id);
    }

    @GetMapping("/users/{id}/friends")
    public List<User> getAllFriends(@PathVariable("id") int id) {
        log.info("Получен запрос к эндпоинту: GET /users/{id}/friends");
        return userService.getAllFriend(id);
    }
    @GetMapping("/users/{id}/friends/common/{otherId}")
    public List<User> getMutualFriends(@PathVariable("id") int id, @PathVariable("otherId") int otherId) {
        log.info("Получен запрос к эндпоинту: GET /users/{id}/friends/common/{otherId}");
        if (!userService.isContainsUser(id) || !userService.isContainsUser(otherId)) {
            log.warn("Пользователь с таким id не найден, id1=" + id + ", id2=" + otherId);
            throw new InputDataException("Один из двух друзей не найден по своему id");
        }
        return userService.mutualFriends(id, otherId);
    }

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Получен запрос к эндпоинту: POST /users");
        if(user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        if(user.getFriends() == null) {
            user.setFriends(new HashSet<>());
        }
        if(validateUser.checkAllData(user)) {
            user.setId(getId());
            userService.addUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            log.warn("Запрос к эндпоинту POST /users не обработан.");
            throw new ValidationException("Одно или несколько условий не выполняются");
        }
    }
    @PutMapping("/users")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        log.info("Получен запрос к эндпоинту: PUT /users");
        if(!userService.isContainsUser(user.getId())) {
            throw new InputDataException("Пользователь с таким id не найден");
        }
        if(user.getFriends() == null) {
            user.setFriends(new HashSet<>());
        }
        if(user.getName().isEmpty()) {
            user.setName(user.getEmail());
        }
        if(validateUser.checkAllData(user) && user.getId() > 0) {
            userService.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            log.warn("Запрос к эндпоинту PUT /users не обработан.");
            throw new ValidationException("Одно или несколько условий не выполняются");
        }
    }
    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        log.info("Получен запрос к эндпоинту: PUT /users/{id}/friends/{friendId}");
        if(!userService.isContainsUser(id) || !userService.isContainsUser(friendId)) {
            log.warn("Один или оба пользователя не найдены в базе данных по id; id1=" + id + ", id2=" +friendId);
            throw new InputDataException("Один или оба пользователя не найдены");
        }
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable("id") int id, @PathVariable("friendId") int friendId) {
        log.info("Получен запрос к эндпоинту: DELETE /users/{id}/friends/{friendId}");
        userService.deleteFriend(id, friendId);
    }



    public int getId() {
        this.id++;
        return id;
    }
}