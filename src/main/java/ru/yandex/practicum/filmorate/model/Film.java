package ru.yandex.practicum.filmorate.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.Size;


@Data
public class Film {
    private int id;
    @NonNull
    private String name;
    @Size (min=1, max=200)
    private String description;
    private LocalDate releaseDate;
    private int duration;

}
