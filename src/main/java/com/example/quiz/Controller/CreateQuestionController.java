package com.example.quiz.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz.Component.FormValidator;
import com.example.quiz.Domain.Choices;
import com.example.quiz.Domain.Titles;
import com.example.quiz.Service.CreateQuestionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/createQuestion")
public class CreateQuestionController {
    @Autowired
    private HttpSession session;

    @Autowired
    private CreateQuestionService createQuestionService;

    @Autowired
    public FormValidator formValidator;

    /**
     * 問題作成画面に遷移します。
     * 
     * @return 問題作成画面のテンプレート名 ("createQuestion")
     */
    @RequestMapping("")
    public String createQuestion() {
        return "createQuestion";
    }

    /**
     * クイズタイトルの登録処理を行います。
     * 
     * "/createQuestion/insertTitle" に対する POST リクエストを処理します。
     * 
     * @param titles 登録するタイトルの情報 (Titles クラスのオブジェクト)
     * @return タイトル登録完了後の遷移先 (タイトルがない場合は、問題作成画面 "createQuestion"に戻る)
     * @throws Exception データベース操作中に例外が発生した場合
     */
    @RequestMapping("/insertTitle")
    public String insertQuestion(@Validated Titles titles, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        Boolean insertFlag;

        if (bindingResult.hasErrors()) {
            // エラーメッセージ設定
            String errorMessage = bindingResult.getFieldError("name").getDefaultMessage();
            model.addAttribute("errorMessage", errorMessage);
            return createQuestion();
        }

        insertFlag = createQuestionService.findTitleNameByname(titles.getName());
        if (insertFlag) {
            Integer TitlesId = createQuestionService.insertTitleReturnTitlesId(titles);
            titles.setId(TitlesId);

            System.out.println("titleInsert完了" + titles.toString());
            redirectAttributes.addFlashAttribute("successMessage", "クイズタイトルを登録しました。<br>タイトル：" + titles.getName());
            session.setAttribute("titles", titles);
            return "redirect:/createQuestion/toCreateChoices";
        } else {
            model.addAttribute("errorMessage", "既に登録済みのクイズタイトルです");
            return createQuestion();
        }
    }

    @RequestMapping("/toCreateChoices")
    public String toCreateChoices() {
        return "createChoices";
    }

    /**
     * 選択肢の登録処理を行います。
     * 
     * "/createQuestion/insertChoices" に対する POST リクエストを処理します。
     * 
     * @param choices 登録する選択肢の情報 (Choices クラスのオブジェクト)
     * @return 選択肢登録完了後の遷移先 (入力エラーがある場合は、選択肢作成画面 "createChoices"に戻る)
     * @throws Exception データベース操作中に例外が発生した場合
     */
    @RequestMapping("/insertChoices")
    public String insertChoices(@Validated Choices choices, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            // エラーメッセージ設定
            formValidator.setChoicesErrorMessages(bindingResult, model);
            formValidator.validateChoices(choices, model);
            return "createChoices";
        }
        if (formValidator.validateChoices(choices, model)) {
            Titles title = (Titles) session.getAttribute("titles");
            choices.setTitlesId(title.getId());
            createQuestionService.insertChoices(choices);
            System.out.println("choicesInsert完了" + choices.toString());
            redirectAttributes.addFlashAttribute("successMessage", "クイズの登録が完了しました");
            return "redirect:/main";
        } else {
            return "createChoices";
        }

    }
}
