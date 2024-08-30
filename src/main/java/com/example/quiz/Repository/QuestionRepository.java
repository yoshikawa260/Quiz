package com.example.quiz.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.quiz.Domain.Choices;

@Repository
public class QuestionRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final RowMapper<Choices> CHOICES_ROW_MAPPER = new BeanPropertyRowMapper<>(Choices.class);

    public List<Choices> findChoicesByTitlesId(Integer id){
        String sql="""
                select *
                from choices
                where titles_id=:id
                """;
        SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.query(sql, param, CHOICES_ROW_MAPPER);
    }
}
