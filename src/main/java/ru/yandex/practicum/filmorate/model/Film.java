package ru.yandex.practicum.filmorate.model;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class Film {
    private int id;
    @NonNull
    private String name;
    private String description;
    private LocalDate releaseDate;
    private int duration;

}
