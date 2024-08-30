package com.example.quiz.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.Domain.Choices;
import com.example.quiz.Repository.QuestionRepository;

@Service
@Transactional
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Choices>findChoicesByTitlesId(Integer id){
        return questionRepository.findChoicesByTitlesId(id);
    }

}
