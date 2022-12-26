package com.example.smthing.controllers;

import com.example.smthing.controllers.data.Table_equationWithSolution;
import com.example.smthing.controllers.data.equationWithSolution;
import com.example.smthing.controllers.equation.EquationWithWritingToPage;
import com.example.smthing.controllers.equation.equationExceptions.EquationIsNotASurfaceException;
import com.example.smthing.controllers.equation.equationExceptions.EquationNotEnoughCoefsException;
import com.example.smthing.controllers.equation.equationExceptions.EquationNotNumberException;
import com.example.smthing.controllers.equation.equationExceptions.EquationNullException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.Param;
import org.springframework.web.servlet.tags.UrlTag;

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
    public ModelAndView getInput(HttpServletRequest request, Model model, String a11, String a22, String a33, String a12, String a13, String a23, String a1, String a2, String a3, String a0) throws EquationIsNotASurfaceException, EquationNotNumberException, EquationNotEnoughCoefsException, EquationNullException {
        String[] aStr;
        String solution;
        aStr = new String[]{a11, a22, a33, a12, a13, a23, a1, a2, a3, a0};
        EquationWithWritingToPage equation = new EquationWithWritingToPage(model, aStr);
        equation.FillEquation();
        solution = equation.AddAnswer();
        // TODO виключення можуть виникати не лише по причині накоректно заданої площини
        //  (також подивіться на https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)

        //model.put(aStrAttrName, aStr);
        //model.put(solutionAttrName, solution);
        request.getSession().setAttribute(aStrAttrName, aStr);
        request.getSession().setAttribute(solutionAttrName, solution);
        //System.out.println(Arrays.toString(aStr) +" " + solution + " " + model.size());
        //aStr = (String[]) model.getAttribute(aStrAttrName);
        //solution = (String) model.getAttribute(solutionAttrName);
        //System.out.println(Arrays.toString(aStr) +" " + solution);
        ModelAndView modelAndView = new ModelAndView("solution");
        modelAndView.addObject(aStrAttrName, aStr);
        modelAndView.addObject(solutionAttrName, solution);
//        modelAndView.addObject("fhgj", "fgh");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!"+modelAndView.getModel());
//        System.out.println(modelAndView.getView());
//        System.out.println(modelAndView.getStatus());
//        System.out.println(modelAndView.toString());
        modelAndView.addObject("action", "/solve/submitSolution");
        return modelAndView;
    }

    @RequestMapping(value = "/solve/submitSolution")
    public String sumbmitSolution(HttpServletRequest request,
                                  @RequestParam(name = aStrAttrName) String[] aStr1,
                                  @RequestParam(name = solutionAttrName) String solution1
//                                  @ModelAttribute(name = aStrAttrName) String[] aStr,
//                                  @ModelAttribute(name = solutionAttrName) String solution1
    ) {
        String[] aStr;
        String solution;

        aStr = (String[]) request.getSession().getAttribute(aStrAttrName);
        solution = (String) request.getSession().getAttribute(solutionAttrName);
        System.out.println(request.toString());
        System.out.println(request.getHttpServletMapping());
        System.out.println(request.getQueryString());
        System.out.println(Arrays.toString(aStr1) +" fg "+solution1);
        equationWithSolution e = new equationWithSolution(aStr, solution);
        equationRepo.save(e);
        return "redirect:/";
    }

    @ExceptionHandler({EquationIsNotASurfaceException.class, EquationNullException.class,
                       EquationNotNumberException.class, EquationNotEnoughCoefsException.class})
    public ModelAndView conflict() {
        ModelAndView modelAndView = new ModelAndView("redirect:");
        modelAndView.addObject("Error", "Така площина неможлива");
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
        public ModelAndView conflictN () {
        ModelAndView modelAndView = new ModelAndView("ExceptionNotFound");
        modelAndView.addObject("Error", "Err");
        return modelAndView;
    }
}
