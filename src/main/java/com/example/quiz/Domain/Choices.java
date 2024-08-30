package com.example.quiz.Domain;

import jakarta.validation.constraints.NotBlank;

public class Choices {

    private Integer id;

    // クイズタイトルのID
    private Integer titlesId;

    // 問題
    @NotBlank(message = "タイトルは入力必須です")
    private String question;

    // 正答
    @NotBlank(message = "選択肢は入力必須です")
    private String choice1;

    // 誤答
    @NotBlank(message = "選択肢は入力必須です")
    private String choice2;
    @NotBlank(message = "選択肢は入力必須です")
    private String choice3;
    @NotBlank(message = "選択肢は入力必須です")
    private String choice4;

    @Override
    public String toString() {
        return "Choices [id=" + id + ", titlesId=" + titlesId + ", question=" + question + ", choice1=" + choice1
                + ", choice2=" + choice2 + ", choice3=" + choice3 + ", choice4=" + choice4 + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTitlesId() {
        return titlesId;
    }

    public void setTitlesId(Integer titlesId) {
        this.titlesId = titlesId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

}
