package com.example.quiz.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.Domain.Choices;
import com.example.quiz.Repository.EditQuestionRepository;

@Service
@Transactional
public class EditQuestionService {
    @Autowired
    private EditQuestionRepository editQuestionRepository;

    public List<Choices> findChoicesListByTItlesId(Integer id) {
        return editQuestionRepository.findChoicesListByTItlesId(id);
    }

    public Integer countChoicesByTitlesId(Integer titlesId) {
        return editQuestionRepository.countChoicesByTitlesId(titlesId);
    }

    public void insertChoices(Choices choices) {
        editQuestionRepository.insertChoices(choices);
    }

    public void updateChoices(Choices choices) {
        editQuestionRepository.updateChoices(choices);
    }

    public void deleteChoicesById(Integer id) {
        editQuestionRepository.deleteChoicesByid(id);
    }

    public void deleteTitlesANDChoicesByTitlesId(Integer titlesId) {
        editQuestionRepository.deleteChoicesByTitlesId(titlesId);
        editQuestionRepository.deleteTitlesById(titlesId);
    }

    public List<Choices> findChoicesListByTItlesIdWithPaging(Integer titlesId,Integer pageSize,Integer currentPage) {
        Integer offset;
        if (currentPage==1) {
            offset=0;
        }else{
            offset=(currentPage-1)*pageSize;
        }
        return editQuestionRepository.findChoicesListByTItlesIdWithPaging(titlesId,offset);
    }

    public List<Integer> createPageNumbersList(int totalPages) {
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers;
    }

    

}
