package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.validate.ValidateFilm;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final Map<Integer, Film> films = new HashMap<>();
    ValidateFilm validate = new ValidateFilm();
    @GetMapping
    public Collection<Film> getFilms() {
        log.info("Количество фильмов: {}", films.size());
        return films.values();
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) throws ValidationException {
        validate.validateFilm(film);
        if (films.containsKey(film.getId())) {
            throw new ValidationException("Фильм \"" +
                    film.getName() + "\" уже есть в списке.");
        } else {
            int id = film.getId();
            film.setId(++id);
            films.put(film.getId(), film);
            log.info("Фильм {} создан.", film.getName());
        }
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) throws ValidationException {
        validate.validateFilm(film);
        if (!films.containsKey(film.getId())) {
            createFilm(film);
        } else {
            films.put(film.getId(), film);
            log.info("Фильм {} обновлен.", film.getName());
        }
        return film;
    }


}