package ru.yandex.practicum.filmorate.validate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

@Slf4j
@Service
public class ValidateFilm {

    private static final LocalDate dateFirstFilm = LocalDate.of(1895, 12, 28);

    private static final int maxLengthDescription = 200;

    public ValidateFilm() {
    }

    public boolean checkAllData(Film film) {
        if (isCorrectName(film) && isCorrectLengthDescription(film) && isCorrectReleaseDate(film) &&
                isPositiveDuration(film)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCorrectName(Film film) {
        if (!film.getName().isBlank()) {
            return true;
        } else {
            log.warn("Ошибка во входных данных фильма. Пустое название фильма");
            return false;
        }
    }

    private boolean isCorrectLengthDescription(Film film) {
        if (film.getDescription().length() <= maxLengthDescription) {
            return true;
        } else {
            log.warn("Ошибка во входных данных фильма. Превышено максимально допустимое описание фильма в "
                    + maxLengthDescription + " символов. Текущая длина описания фильма "
                    + film.getDescription().length() + " символов.");
            return false;
        }
    }

    private boolean isCorrectReleaseDate(Film film) {
        if (film.getReleaseDate().isAfter(dateFirstFilm)) {
            return true;
        } else {
            log.warn("Ошибка во входных данных фильма. Некорректная дата релиза " + film.getReleaseDate()
                    + ". Релиз должен быть позже " + dateFirstFilm);
            return false;
        }
    }

    private boolean isPositiveDuration(Film film) {
        if (film.getDuration() > 0) {
            return true;
        } else {
            log.warn("Ошибка во входных данных фильма. Продолжительность фильма должна быть положительной.");
            return false;
        }
    }
}

