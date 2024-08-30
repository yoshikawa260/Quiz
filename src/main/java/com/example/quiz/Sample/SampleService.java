package com.example.quiz.Sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.Domain.Choices;

@Service
@Transactional
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    public void insertSampleData() {

        List<Choices>choicesList=new ArrayList<>();

        Sample sample=new Sample();
        for(String[]data:sample.quizData){

            Choices choices=new Choices();
            choices.setTitlesId(1);
            choices.setQuestion(data[0]);
            choices.setChoice1(data[1]);
            choices.setChoice2(data[2]);
            choices.setChoice3(data[3]);
            choices.setChoice4(data[4]);
            
            choicesList.add(choices);
        }

        System.out.println("リストのサイズは"+choicesList.size());
        sampleRepository.insertSampleData(choicesList);
    }
}
