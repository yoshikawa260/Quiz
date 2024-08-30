package com.example.quiz.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.quiz.Domain.Choices;

@Repository
public class EditQuestionRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RowMapper<Choices> CHOICES_ROWMAPPER = new BeanPropertyRowMapper<>(Choices.class);

    public List<Choices> findChoicesListByTItlesId(Integer id) {
        String findChoicesListByTitlesIdSQL = """
                select *
                from choices
                where titles_id=:titlesId
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("titlesId", id);
        return namedParameterJdbcTemplate.query(findChoicesListByTitlesIdSQL, param, CHOICES_ROWMAPPER);
    };

    public Integer countChoicesByTitlesId(Integer id) {
        String countChoicesByTitlesIdSQL = """
                select count(*)
                from choices
                where titles_id=:titlesId
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("titlesId", id);
        return namedParameterJdbcTemplate.queryForObject(countChoicesByTitlesIdSQL, param, Integer.class);
    }

    public List<Choices> findChoicesListByTItlesIdWithPaging(Integer titlesId,Integer offset) {
       
        String sql = """
                select *
                from choices
                where titles_id=:titlesId
                limit 10
                offset :offset;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("titlesId", titlesId).addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, param, CHOICES_ROWMAPPER);
    }

    /**
     * クイズの選択肢を登録します。
     * 
     * @param choices 登録する選択肢 (Choices クラスのオブジェクト)
     */
    public void insertChoices(Choices choices) {
        String insertChoicesSQL = """
                insert into choices(titles_id,question,choice1,choice2,choice3,choice4)
                values (:titlesId,:question,:choice1,:choice2,:choice3,:choice4)
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(choices);
        namedParameterJdbcTemplate.update(insertChoicesSQL, param);
    }

    public void updateChoices(Choices choices) {
        String updateChoicesSQL = """
                update choices
                set question=:question,choice1=:choice1,choice2=:choice2,choice3=:choice3,choice4=:choice4
                where id=:id
                """;
        SqlParameterSource param = new BeanPropertySqlParameterSource(choices);
        namedParameterJdbcTemplate.update(updateChoicesSQL, param);
    }

    public void deleteChoicesByid(Integer id) {
        String deleteChoicesSQL = """
                delete from choices
                where id=:id
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(deleteChoicesSQL, param);
    };

    public void deleteChoicesByTitlesId(Integer titlesId) {
        String deleteChoicesSQL = """
                delete from choices
                where titles_id=:titlesId
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("titlesId", titlesId);
        namedParameterJdbcTemplate.update(deleteChoicesSQL, param);
    };

    public void deleteTitlesById(Integer id) {
        String deleteTitlesByIdSQL = """
                delete from titles
                where id=:id
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(deleteTitlesByIdSQL, param);
    }
}
