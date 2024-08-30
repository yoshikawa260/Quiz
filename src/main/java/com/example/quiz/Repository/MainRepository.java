package com.example.quiz.Repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.quiz.Domain.Titles;

@Repository
public class MainRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<Titles> TITLES_ROWMAPPER = new BeanPropertyRowMapper<>(Titles.class);

    public List<Titles> getTitlesList() {
        String sql = """
                select *
                from titles
                """;
        return namedParameterJdbcTemplate.query(sql, TITLES_ROWMAPPER);
    }

    public Integer getCountTitles() {
        String countTitlesSQL = """
                SELECT COUNT(*)
                FROM titles
                """;
        return namedParameterJdbcTemplate.queryForObject(countTitlesSQL, new HashMap<>(), Integer.class);
    }

    public List<Titles> findTitlesListWithPaging(Integer offset) {
        String sql = """
                SELECT *
                FROM titles
                LIMIT 10
                OFFSET :offset
                """;
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, param, TITLES_ROWMAPPER);
    }

}
