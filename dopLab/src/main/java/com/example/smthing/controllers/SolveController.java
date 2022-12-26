package com.example.smthing.controllers;

import com.example.smthing.controllers.data.ITable_equationWithSolution;
import com.example.smthing.controllers.data.equationWithSolution;
import com.example.smthing.controllers.equation.EquationWithWritingToPage;
import com.example.smthing.controllers.equation.equationexceptions.EquationIsNotASurfaceException;
import com.example.smthing.controllers.equation.equationexceptions.EquationNotEnoughCoefsException;
import com.example.smthing.controllers.equation.equationexceptions.EquationNotNumberException;
import com.example.smthing.controllers.equation.equationexceptions.EquationNullException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SolveController {
    private final ITable_equationWithSolution equationRepo;
    private final String aStrAttrName = "aStrSave";
    private final String solutionAttrName = "SolutionSave";


    public SolveController(ITable_equationWithSolution equationRepo) {
        this.equationRepo = equationRepo;
    }


    @RequestMapping(value = "/solve", method = RequestMethod.POST)
    public ModelAndView getInput(Model model, String a11, String a22, String a33, String a12, String a13, String a23, String a1, String a2, String a3, String a0) throws EquationIsNotASurfaceException, EquationNotNumberException, EquationNotEnoughCoefsException, EquationNullException {
        String[] aStr;
        String solution;
        aStr = new String[]{a11, a22, a33, a12, a13, a23, a1, a2, a3, a0};
        EquationWithWritingToPage equation = new EquationWithWritingToPage(model, aStr);
        equation.fillEquation();
        solution = equation.addAnswer();
        // TODO виключення можуть виникати не лише по причині накоректно заданої площини
        //  (також подивіться на https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)

        ModelAndView modelAndView = new ModelAndView("solution");
        modelAndView.addObject(aStrAttrName, aStr);
        modelAndView.addObject(solutionAttrName, solution);
        modelAndView.addObject("action", "/solve/submitSolution");
        return modelAndView;
    }

    @RequestMapping(value = "/solve/submitSolution")
    public String sumbmitSolution(@RequestParam(name = aStrAttrName) String[] aStr,
                                  @RequestParam(name = solutionAttrName) String solution) {
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
