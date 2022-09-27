package ru.yandex.practicum.filmorate.storage.film.ratingMpa;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Mpa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("ratingMpaDbStorage")
public class RatingMpaDbStorage implements RatingMpaStorage {
    private final JdbcTemplate jdbcTemplate;

    public RatingMpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Mpa getMpaRatingById(int id) {
        String sql = "SELECT name FROM ratings WHERE rating_id = ?";
        String ratingStr = jdbcTemplate.queryForObject(sql, String.class, id);
        Mpa mpa = new Mpa();
        mpa.setId(id);
        mpa.setName(ratingStr);
        return mpa;
    }

    @Override
    public List<Mpa> findAllMpaRatings() {
        String sql = "SELECT * FROM ratings ORDER BY rating_id";
        List<Mpa> allMpa = new ArrayList<>(jdbcTemplate.query(sql, this::mapRowToMPA));
        List<Mpa> allMpaCleaned = new ArrayList<>();
        for (Mpa i : allMpa) {
            String name = i.getName();
            boolean notInClearnedList = true;
            for (Mpa j : allMpaCleaned) {
                if (j.getName().equals(name)) {
                    notInClearnedList = false;
                }
            }
            if (notInClearnedList) {
                allMpaCleaned.add(i);
            }
        }
        return allMpaCleaned;
    }

    public Mpa mapRowToMPA(ResultSet resultSet, int rowNum) throws SQLException {
        Mpa mpa = new Mpa();
        mpa.setId(resultSet.getInt("rating_id"));
        mpa.setName(resultSet.getString("name"));
        return mpa;
    }
}