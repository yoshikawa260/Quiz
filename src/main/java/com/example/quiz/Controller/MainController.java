package com.example.quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.quiz.Domain.Titles;
import com.example.quiz.Service.MainService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private HttpSession session;

    @Autowired
    private MainService mainService;

    private static final int PAGE_SIZE = 10;

    @RequestMapping("")
    public String Main() {
        session.invalidate();
        return showPage(1);
    }

    @RequestMapping("/showPage")
    public String showPage(Integer currentPage) {
        int totalSize = mainService.getCountTitles();

        int totalPage = (int) Math.ceil((double) totalSize / PAGE_SIZE);
        List<Integer> pageNumbersList = mainService.createPageNumbersList(totalPage);
        session.setAttribute("pageNumbersList", pageNumbersList);

        List<Titles> titlesList = mainService.findTitlesListWithPaging(PAGE_SIZE, currentPage);

        session.setAttribute("totalPage", totalPage);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("titlesList", titlesList);
        return "main";
    }

}
