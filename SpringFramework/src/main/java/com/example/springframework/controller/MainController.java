package com.example.springframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.springframework.model.MyModel;



@Controller
@RequestMapping("/blog")
public class MainController {
    private MyModelService service;

    @RequestMapping
    public String mainPage(Model model) {
        model.addAttribute("myModel", service.getAll());
        return "main";
    }

    @RequestMapping(value = "/editor")
    public String editorPage(Model model) {
        model.addAttribute("myModel", new MyModel());
        return "editor";
    }

    @RequestMapping(value = "/editor/submit", method = RequestMethod.POST)
    public String submitMyModel(@ModelAttribute MyModel myModel) {
        service.save(myModel);
        return "redirect:../";

    }
}