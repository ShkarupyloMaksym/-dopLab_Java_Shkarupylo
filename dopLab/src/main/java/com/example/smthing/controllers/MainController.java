package com.example.smthing.controllers;

import com.example.smthing.controllers.data.Table_equationWithSolution;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {
    private final Table_equationWithSolution equationRepo;

    public MainController(Table_equationWithSolution equationRepo) {
        this.equationRepo = equationRepo;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        System.out.println("Go to the home");
        model.addAttribute("DataEquatations", equationRepo.findAll());
        return "home";
    }

    @RequestMapping(value = "/FreeDataBase")
    public String deleteAllData(Model model) {

        equationRepo.deleteAll();

        return "redirect:";
    }


}

