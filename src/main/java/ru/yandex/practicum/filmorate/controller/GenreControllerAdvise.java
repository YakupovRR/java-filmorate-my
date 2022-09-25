package ru.yandex.practicum.filmorate.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.practicum.filmorate.exception.InputDataException;
import ru.yandex.practicum.filmorate.exception.ValidationException;

@Slf4j
@Service
@ControllerAdvice
public class GenreControllerAdvise {

    @ExceptionHandler
    public ResponseEntity<String> handleIncorrectValidation(ValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        log.warn("При обработке запроса возникло исключение " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundException(InputDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
