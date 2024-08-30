package com.example.quiz.Component;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.quiz.Domain.Choices;

/**
 * フォーム入力のバリデーション処理を行うユーティリティクラスです。
 *
 */
@Component
public class FormValidator {

    /**
     * バインディング結果オブジェクト (`BindingResult`) から、
     * question, choice1, choice2, choice3, choice4 フィールドのエラーメッセージを取得し、
     * Model オブジェクトに設定します。
     *
     * @param bindingResult バインディング結果オブジェクト
     * @param model         Model オブジェクト
     */
    public void setChoicesErrorMessages(BindingResult bindingResult, Model model) {
        // エラーメッセージ
        String errorMessage;

        // フィールド名配列
        String[] fieldNames = { "question", "choice1", "choice2", "choice3", "choice4" };
        for (String field : fieldNames) {
            if (bindingResult.getFieldError(field) != null) {
                errorMessage = bindingResult.getFieldError(field).getDefaultMessage();
                model.addAttribute(field + "Message", errorMessage);
            }
        }
    }

    /**
     * 選択肢 (`Choices` オブジェクト) の妥当性を検証します。
     * 4つの選択肢が全て異なっていることを確認します。
     *
     * @param choices 選択肢オブジェクト
     * @param model   Model オブジェクト (エラーメッセージ設定に使用)
     * @return 選択肢が全て異なっていれば true、そうでなければ false
     */
    public Boolean validateChoices(Choices choices, Model model) {
        
        // 選択肢を小文字に変換して重複を排除したリストを作成
        Set<String> choicesList = new HashSet<>();
        choicesList.add(choices.getChoice1().toLowerCase());
        choicesList.add(choices.getChoice2().toLowerCase());
        choicesList.add(choices.getChoice3().toLowerCase());
        choicesList.add(choices.getChoice4().toLowerCase());

        // 選択肢が4つない場合、エラーメッセージを設定してfalseを返す
        if (choicesList.size() != 4) {
            model.addAttribute("errorMessage", "同じ選択肢があります。選択肢は全て異なる内容を入力してください");
            return false;
        } else {
            return true;
        }
    }
}
