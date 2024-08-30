package com.example.quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz.Component.FormValidator;
import com.example.quiz.Domain.Choices;
import com.example.quiz.Service.EditQuestionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/edit")
public class EditQuestionController {
    @Autowired
    private EditQuestionService editQuestionService;

    @Autowired
    private FormValidator formValidator;

    @Autowired
    private HttpSession session;

    public static final Integer PAGE_SIZE = 10;
    public int totalSize;
    public int totalPage;
    public int currentPage = 1;

    @RequestMapping("")
    public String prepareShow(Integer titlesId,Model model) {
        if (titlesId == null) {
            return "redirect:/main";
        }
        session.setAttribute("titlesId", titlesId);
        return showQuestionDetails(1,model);
    }

    @RequestMapping("/showPage")
    public String showQuestionDetails(Integer currentPage, Model model) {
        Integer titlesId = (Integer) session.getAttribute("titlesId");
        totalSize = editQuestionService.countChoicesByTitlesId(titlesId);
        if (totalSize < 1) {
            model.addAttribute("errorMessage", "登録がありません。追加してください。");
            return "showQuestionDetails";
        }

        totalPage = (int) Math.ceil((double) totalSize / PAGE_SIZE);
        List<Integer> pageNumbersList = editQuestionService.createPageNumbersList(totalPage);
        session.setAttribute("pageNumbersList", pageNumbersList);

        List<Choices> choicesList = editQuestionService.findChoicesListByTItlesIdWithPaging(titlesId, PAGE_SIZE,
                currentPage);

        session.setAttribute("totalPage", totalPage);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("choicesList", choicesList);
        return "showQuestionDetails";
    }

    @RequestMapping("/editDetails")
    public String editDetails(Integer id) {
        List<Choices> choicesList = (List<Choices>) session.getAttribute("choicesList");
        if (choicesList == null || choicesList.isEmpty() || id == null) {
            return "redirect:/main";
        }
        for (Choices choices : choicesList) {
            if (choices.getId() == id) {
                session.setAttribute("choices", choices);
                break;
            }
        }
        return "editChoices";
    }

    @RequestMapping("/update")
    public String update(@Validated Choices choices, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model) {
        System.out.println(choices.toString());
        if (bindingResult.hasErrors()) {
            formValidator.setChoicesErrorMessages(bindingResult, model);
            formValidator.validateChoices(choices, model);
            return "editChoices";
        }
        if (formValidator.validateChoices(choices, model)) {
            editQuestionService.updateChoices(choices);
            redirectAttributes.addFlashAttribute("successMessage","更新が完了しました。<br>Quiz:"+choices.getQuestion());
            return "redirect:/edit?titlesId=" + choices.getTitlesId();
        } else {
            System.out.println("重複エラー");
            return "editChoices";
        }

    }

    @RequestMapping("/addChoices")
    public String addChoices() {
        return "addChoices";
    }

    @RequestMapping("/add")
    public String add(@Validated Choices choices, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model) {
        if (bindingResult.hasErrors()) {
            formValidator.setChoicesErrorMessages(bindingResult, model);
            formValidator.validateChoices(choices, model);
            return "addChoices";
        }
        if (formValidator.validateChoices(choices, model)) {
            editQuestionService.insertChoices(choices);
            redirectAttributes.addFlashAttribute("successMessage","クイズの追加が完了しました。<br>Quiz:"+choices.getQuestion());
            return "redirect:/edit?titlesId=" + choices.getTitlesId();
        } else {
            return "addChoices";
        }
    }

    @RequestMapping("/deleteChoices")
    public String deleteChoices(Integer id, Integer titlesId,RedirectAttributes redirectAttributes) {
        editQuestionService.deleteChoicesById(id);
      redirectAttributes.addFlashAttribute("successMessage","削除しました");
        return "redirect:/edit?titlesId=" + titlesId;
    }

    @RequestMapping("/deleteQuestion")
    public String deleteTitlesANDChoicesByTitlesId(RedirectAttributes redirectAttributes) {
        Integer titlesId = (Integer) session.getAttribute("titlesId");
        editQuestionService.deleteTitlesANDChoicesByTitlesId(titlesId);
        redirectAttributes.addFlashAttribute("successMessage","削除しました");
        return "redirect:/main";
    }

}
