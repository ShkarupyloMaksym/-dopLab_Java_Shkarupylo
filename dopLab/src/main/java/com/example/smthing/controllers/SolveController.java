package com.example.smthing.controllers;

import com.example.smthing.controllers.data.Table_equationWithSolution;
import com.example.smthing.controllers.data.equationWithSolution;
import com.example.smthing.controllers.equation.EquationWithWritingToPage;
import com.example.smthing.controllers.equation.writer.DeterminantWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class SolveController {
    private final Table_equationWithSolution equationRepo;
    private final String aStrAttrName = "aStrSave";
    private final String solutionAttrName = "SolutionSave";


    //TODO при конкурентному використанні пожлива гонка
    public SolveController(Table_equationWithSolution equationRepo) {
        this.equationRepo = equationRepo;
    }


    @RequestMapping(value = "/solve", method = RequestMethod.POST)
    public String getInput(HttpServletRequest request, Model model, String a11, String a22, String a33, String a12, String a13, String a23, String a1, String a2, String a3, String a0) {
        String[] aStr;
        String solution;
        aStr = new String[]{a11, a22, a33, a12, a13, a23, a1, a2, a3, a0};
        //DeterminantWriter.equationModel = model;
        try {
            EquationWithWritingToPage equation = new EquationWithWritingToPage(model, aStr);
            equation.FillEquation();
            solution = equation.AddAnswer();
        } catch (Exception e) {
            // TODO виключення можуть виникати не лише по причині накоректно заданої площини
            //  (також подивіться на https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)
            model.addAttribute("Error", "Така площина неможлива");
            return "redirect:";
        }

        //model.put(aStrAttrName, aStr);
        //model.put(solutionAttrName, solution);
        request.getSession().setAttribute(aStrAttrName, aStr);
        request.getSession().setAttribute(solutionAttrName, solution);
        //System.out.println(Arrays.toString(aStr) +" " + solution + " " + model.size());

        //aStr = (String[]) model.getAttribute(aStrAttrName);
        //solution = (String) model.getAttribute(solutionAttrName);
        //System.out.println(Arrays.toString(aStr) +" " + solution);

        return "solution";
    }

    @RequestMapping(value = "/solve/submitSolution")
    public String sumbmitSolution(HttpServletRequest request) {
        String[] aStr;
        String solution;

        aStr = (String[]) request.getSession().getAttribute(aStrAttrName);
        solution = (String) request.getSession().getAttribute(solutionAttrName);

        System.out.println(Arrays.toString(aStr) +" " + solution);
        equationWithSolution e = new equationWithSolution(aStr, solution);
        equationRepo.save(e);
        return "redirect:/";
    }


}
