package com.example.quiz.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.quiz.Domain.Choices;
import com.example.quiz.Domain.Titles;

@Repository
public class CreateQuestionRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final RowMapper<Choices> CHOICES_ROW_MAPPER = new BeanPropertyRowMapper<>(Choices.class);
    public static final RowMapper<Titles>TITLES_ROW_MAPPER=new BeanPropertyRowMapper<>(Titles.class);

    public List<Titles> findTitleNameByname(String name){
        String findTitleNameBynameSQL="""
                select *
                from titles
                where name=:name;
                """;
        SqlParameterSource param=new MapSqlParameterSource().addValue("name", name);
        return namedParameterJdbcTemplate.query(findTitleNameBynameSQL, param,TITLES_ROW_MAPPER);

    }

    /**
     * クイズタイトルを登録し、登録されたタイトルの ID を返します。
     * 
     * @param titles 登録するタイトルの情報 (Titles クラスのオブジェクト)
     * @return 登録されたタイトルの ID (Integer 型)
     */
    public Integer insertTitleReturnTitlesId(Titles titles) {
        try {
            String insertTitleSQL = """
                    insert into titles(name)
                    values (:name)
                    returning id
                    """;
            SqlParameterSource param = new MapSqlParameterSource().addValue("name", titles.getName());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insertTitleSQL, param, keyHolder);
            return keyHolder.getKey().intValue();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
}
