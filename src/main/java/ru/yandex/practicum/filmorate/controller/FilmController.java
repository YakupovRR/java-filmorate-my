package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validators.FilmValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private Map<Integer, Film> films = new ConcurrentHashMap<>();

    private FilmValidator filmValidator = new FilmValidator();

    @PostMapping
    public void addFilm(@RequestBody Film film) throws ValidationException {

        filmValidator.isValid(film);

        films.put(film.getId(), film);

        log.info("Добавление фильма - {}", film.getName());

    }

    @PutMapping
    public void updateFilm(@RequestBody Film film) throws ValidationException {


        filmValidator.isValid(film);

        films.put(film.getId(), film);

        log.info("Обновление фильма - {}", film.getName());

    }

    @GetMapping
    public List<Film> findAll() {

        return new ArrayList<>(films.values());

    }


}