package ru.yandex.practicum.filmorate.validate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Service
class ValidateFilmTest {
    private final ValidateFilm validateFilm;

    static Film film = Film.builder()
            .id(1)
            .name("name1")
            .description("description1")
            .duration(120)
            .releaseDate(LocalDate.of(2021, 5, 21))
            .build();
    @Autowired
    ValidateFilmTest(ValidateFilm validateFilm) {
        this.validateFilm = validateFilm;
    }

    @AfterEach
    public void film() {
        film = Film.builder()
                .id(1)
                .name("name1")
                .description("description1")
                .duration(120)
                .releaseDate(LocalDate.of(2021, 5, 21))
                .build();
    }


    @Test
    public void correctAllData() {
        assertTrue(validateFilm.checkAllData(film));
    }

    @Test
    public void inCorrectName() {
        film.setName("");
        assertFalse(validateFilm.checkAllData(film));
    }

    @Test
    public void inCorrectLengthDescription() {
        film.setDescription(" ".repeat(201));
        assertFalse(validateFilm.checkAllData(film));
    }

    @Test
    public void inCorrectReleaseDate() {
        film.setReleaseDate(LocalDate.of(1894, 5, 21));
        assertFalse(validateFilm.checkAllData(film));
    }

    @Test
    public void inCorrectDuration() {
        film.setDuration(-1);
        assertFalse(validateFilm.checkAllData(film));
    }

}

