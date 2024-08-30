package com.example.quiz.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.Domain.Titles;
import com.example.quiz.Repository.MainRepository;

@Service
@Transactional
public class MainService {

    @Autowired
    private MainRepository mainRepository;

    public List<Titles> getTitlesList() {
        return mainRepository.getTitlesList();
    }

    public Integer getCountTitles() {
        return mainRepository.getCountTitles();
    }

    public List<Titles> findTitlesListWithPaging(Integer pageSize, Integer currentPage) {
        Integer offset;
        if (currentPage == 1) {
            offset = 0;
        } else {
            offset = (currentPage - 1) * pageSize;
        }
        return mainRepository.findTitlesListWithPaging(offset);
    }

    public List<Integer> createPageNumbersList(int totalPages) {
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers;
    }
}
