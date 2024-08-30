package com.example.quiz.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.Domain.Choices;
import com.example.quiz.Domain.Titles;
import com.example.quiz.Repository.CreateQuestionRepository;

@Service
@Transactional
public class CreateQuestionService {
    @Autowired
    private CreateQuestionRepository createQuestionRepository;

    public Boolean findTitleNameByname(String name){
        List<Titles>titlesList=createQuestionRepository.findTitleNameByname(name);
        if(titlesList.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public Integer insertTitleReturnTitlesId(Titles titles) {
        return createQuestionRepository.insertTitleReturnTitlesId(titles);
    }

    public void insertChoices(Choices choices){
        createQuestionRepository.insertChoices(choices);
    }
}
