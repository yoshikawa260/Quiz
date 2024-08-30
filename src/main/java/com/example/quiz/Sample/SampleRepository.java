package com.example.quiz.Sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.quiz.Domain.Choices;

@Repository
public class SampleRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertSampleData(List<Choices> choicesList) {
        for (Choices choices : choicesList) {
            String sql = """
                    insert into choices(titles_id,question,choice1,choice2,choice3,choice4)
                    values (:titlesId,:question,:choice1,:choice2,:choice3,:choice4)
                    """;
            BeanPropertySqlParameterSource param=new BeanPropertySqlParameterSource(choices);
            namedParameterJdbcTemplate.update(sql, param);

        }
    }
}
