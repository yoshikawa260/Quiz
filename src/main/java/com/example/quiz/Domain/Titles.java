package com.example.quiz.Domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Titles {

    private Integer id;

    // クイズタイトル
    @NotBlank(message = "入力必須です")
    @Size(max=30,message="30文字以内で入力してください")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Titles [id=" + id + ", name=" + name + "]";
    }

}
