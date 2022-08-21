package ru.yandex.practicum.filmorate.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NonNull;
import javax.validation.constraints.*;


@Data
public class Film {
    private int id;
    @NonNull
    private String name;
    @Size(max = 200, message = "Максимальная длина описания 200 символов.")
    private String description;
    @PastOrPresent(message = "Дата релиза не должна быть позже текущей.")
    private LocalDate releaseDate;
    @PositiveOrZero(message = "Продолжительность фильма должна быть больше 0.")
    private int duration;


}
