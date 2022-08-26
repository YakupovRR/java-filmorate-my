package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {
    private Film film;
    private FilmController controller;

    @BeforeEach
    protected void beforeEach() {
        controller = new FilmController();
        film = new Film();
        film.setName("nisi eiusmod");
        film.setDescription("adipisicing");
        film.setReleaseDate(LocalDate.of(1967, 03, 25));
        film.setDuration(100);
    }

    @Test
    @DisplayName("название фильма null")
    protected void validateNameNullTest() {
        film.setName(null);
        Exception ex = assertThrows(ValidationException.class, () -> controller.validate.validateFilm(film));
        assertEquals("Название фильма не указано.", ex.getMessage());
    }

    @Test
    @DisplayName("название фильма пустое")
    protected void validateNameTest() {
        film.setName("");
        Exception ex = assertThrows(ValidationException.class, () -> controller.validate.validateFilm(film));
        assertEquals("Название фильма не указано.", ex.getMessage());
    }
    @Test
    @DisplayName("описание больше 200 символов")
    protected void validateDescriptionMore200Test() {
        film.setDescription("Пятеро друзей ( комик-группа «Шарло»), приезжают в город Бризуль. Здесь они хотят" +
                " разыскать господина Огюста Куглова, который задолжал им деньги, а именно 20 миллионов. о Куглов, " +
                "который за время «своего отсутствия», стал кандидатом Коломбани.");
        Exception ex = assertThrows(ValidationException.class, () -> controller.validate.validateFilm(film));
        assertEquals("Описание фильма не должно превышать 200 символов.", ex.getMessage());
    }

    @Test
    @DisplayName("id отрицательный")
    protected void validateIdTest() {
        film.setId(-1);
        Exception ex = assertThrows(ValidationException.class, () -> controller.validate.validateFilm(film));
        assertEquals("id не может быть отрицательным.", ex.getMessage());
    }

    @Test
    @DisplayName("продолжительность отрицательная")
    protected void validateDurationTest() {
        film.setDuration(-10);
        Exception ex = assertThrows(ValidationException.class, () -> controller.validate.validateFilm(film));
        assertEquals("Продолжительность фильма не может быть отрицательной.", ex.getMessage());
    }

    @Test
    @DisplayName("релиз раньше 20 декабря 1895 года")
    protected void validateReleaseTest() {
        film.setReleaseDate(LocalDate.of(1890, 03,25));
        Exception exception = assertThrows(ValidationException.class, () -> controller.validate.validateFilm(film));
        assertEquals("Дата релиза не может быть раньше 28 декабря 1895 года.", exception.getMessage());
    }
}