package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    @Test
    void validateFilm() {
        FilmController fc = new FilmController();
        Film film = new Film("Дом дракона");
        film.setDescription("Члены дома Таргариенов оставляют обреченную Валирию и отправляются на запад," +
                " где обнаруживают огромную территорию, населенную враждующими королевствами.\n");
        film.setReleaseDate(LocalDate.of(1895, 12, 28));
        film.setDuration(90);

        assertAll(
                () -> assertNotNull(film),
                () -> assertNotNull(film.getName(), "Имя фильма не пустое"),
                () -> assertFalse(film.getName().isEmpty(), "Имя фильма не пустое"),
                () -> assertTrue(film.getDescription().length() <= 200,
                        "Длина описания не превышает 200 символов"),
                () -> assertTrue(film.getReleaseDate().isAfter(LocalDate.of(1895, 12, 27)),
                        "Дата релиза не раньше 28 декабря 1895 года")
        );
    }
}