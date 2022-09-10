package ru.yandex.practicum.filmorate.validate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ValidateUserTest {

    private ValidateUser validateUser;

    static User user = User.builder()
            .name("name")
            .email("email@yandex.ru")
            .login("login")
            .birthday(LocalDate.of(1994, 10, 01))
            .build();
    @Autowired
    ValidateUserTest(ValidateUser validateUser) {
        this.validateUser = validateUser;
    }


    @AfterEach
    public void user() {
        user = User.builder()
                .name("name")
                .email("email@yandex.ru")
                .login("login")
                .birthday(LocalDate.of(1994, 10, 01))
                .build();
    }

    @Test
    public void correctAllData() {
        assertTrue(new ValidateUser().checkAllData(user));
    }

    @Test
    public void incorrectEmail() {
        user.setEmail("emailyandex.ru");
        assertFalse(validateUser.checkAllData(user));
    }

    @Test
    public void incorrectLogin() {
        user.setLogin("log in");
        assertFalse(validateUser.checkAllData(user));
    }

    @Test
    public void incorrectBirthday() {
        user.setBirthday(LocalDate.of(2025, 10, 01));
        assertFalse(validateUser.checkAllData(user));
    }

}

