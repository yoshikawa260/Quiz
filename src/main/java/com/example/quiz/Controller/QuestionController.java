package com.example.quiz.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.quiz.Domain.Choices;
import com.example.quiz.Domain.Result;
import com.example.quiz.Service.QuestionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private HttpSession session;

    public static Integer questionCount;
    public static Integer rightCount;

    @RequestMapping("")
    public String PreparationForQuestions(Integer id,Model model) {
        questionCount = 0;
        rightCount = 0;
        List<Choices> choicesList = questionService.findChoicesByTitlesId(id);
        if(choicesList.isEmpty()){
            model.addAttribute("errorMessage", "クイズの登録がありませんでした。");
            return "/main";
        }
        Collections.shuffle(choicesList);
        session.setAttribute("choicesList", choicesList);
        session.setAttribute("titlesId", id);

        return question();
    }

    @RequestMapping("/question")
    public String question() {
        List<Choices> choicesList = (List<Choices>) session.getAttribute("choicesList");
        questionCount++;
        Choices choices = choicesList.get(0);
        session.setAttribute("question", choices.getQuestion());

        List<String> choiceList = new ArrayList<>();
        choiceList.add(choices.getChoice1());
        choiceList.add(choices.getChoice2());
        choiceList.add(choices.getChoice3());
        choiceList.add(choices.getChoice4());
        session.setAttribute("rightAnswer", choices.getChoice1());
        Collections.shuffle(choiceList);

        String questionInSequence = "第" + questionCount + "問";

        session.setAttribute("questionCount", questionCount);
        session.setAttribute("questionInSequence", questionInSequence);
        session.setAttribute("choiceList", choiceList);

        choicesList.remove(0);
        session.setAttribute("choicesList", choicesList);
        return "question";
    }

    @RequestMapping("/evaluateAnswer")
    @ResponseBody
    public Result evaluateAnswer(String answer) {
        String rightAnswer = (String) session.getAttribute("rightAnswer");
        boolean isCorrect = answer.equals(rightAnswer);
        if (isCorrect) {
            rightCount++;
        }
        Result result = new Result();
        String message;
        if (isCorrect) {
            message = "正解です！";
        } else {
            message = "不正解です。正解は「" + rightAnswer + "」です。";
        }
        result.setMessage(message);

        List<Choices> choicesList = (List<Choices>) session.getAttribute("choicesList");
        boolean isLastQuestion;
        if (questionCount == 5 || choicesList.isEmpty()) {
            isLastQuestion = true;
        } else {
            isLastQuestion = false;
        }
        result.setLastQuestion(isLastQuestion);
        return result;
    }

    @RequestMapping("/nextQuestion")
    public String nextQuestion() {
        return question();
    }

    @RequestMapping("/finalResult")
    public String finalResult(String answer) {
        double correctAnswerRate = (double) rightCount / questionCount * 100;
        session.setAttribute("rightCount", rightCount);
        session.setAttribute("correctAnswerRate", correctAnswerRate);
        return "finalResult";

    }
}
