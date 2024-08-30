package com.example.quiz.Sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;
    
    @RequestMapping("")
    public String index(){
        return "sample/index";
    }

    @RequestMapping("/insert")
    public String insert(){
        sampleService.insertSampleData();
        return "redirect:/sample";
    }
}
