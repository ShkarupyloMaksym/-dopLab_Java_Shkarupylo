package com.example.smthing.controllers;

import com.example.smthing.controllers.data.Table_equationWithSolution;
import com.example.smthing.controllers.data.equationWithSolution;
import com.example.smthing.controllers.equation.EquationWithWritingToPage;
import com.example.smthing.controllers.equation.writer.DeterminantWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SolveController {
    private final Table_equationWithSolution equationRepo;
    private String[] aStr;
    private String solution;

    public SolveController(Table_equationWithSolution equationRepo) {
        this.equationRepo = equationRepo;
    }


    @RequestMapping(value = "/solve", method = RequestMethod.POST)
    public String getInput(Model model, String a11, String a22, String a33, String a12, String a13, String a23, String a1, String a2, String a3, String a0) {
        aStr = new String[]{a11, a22, a33, a12, a13, a23, a1, a2, a3, a0};
        DeterminantWriter.equationModel = model;
        try {
            EquationWithWritingToPage equation = new EquationWithWritingToPage(model, aStr);
            equation.FillEquation();
            solution = equation.AddAnswer();
        } catch (Exception e) {
            model.addAttribute("Error", "Така площина неможлива");
            return "redirect:";
        }


        return "solution";
    }

    @RequestMapping(value = "/solve/submitSolution")
    public String sumbmitSolution(Model model) {
        equationWithSolution e = new equationWithSolution(2, aStr, solution);
        equationRepo.save(e);
        return "redirect:/";
    }
}
