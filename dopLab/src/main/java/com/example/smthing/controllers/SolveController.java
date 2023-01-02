package com.example.smthing.controllers;

import com.example.smthing.data.ITable_equationWithSolution;
import com.example.smthing.data.equationWithSolution;
import com.example.smthing.equation.EquationWithWritingToPage;
import com.example.smthing.equation.equationexceptions.EquationIsNotASurfaceException;
import com.example.smthing.equation.equationexceptions.EquationNotEnoughCoefsException;
import com.example.smthing.equation.equationexceptions.EquationNotNumberException;
import com.example.smthing.equation.equationexceptions.EquationNullException;
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
    public ModelAndView getInput(Model model, FormEquationParams formEquationParams) throws EquationIsNotASurfaceException, EquationNotNumberException, EquationNotEnoughCoefsException, EquationNullException {
        String[] aStr;
        String solution;
        aStr = formEquationParams.getaStr();
        EquationWithWritingToPage equation = new EquationWithWritingToPage(model, aStr);
        equation.fillEquation();
        solution = equation.addAnswer();

        ModelAndView modelAndView = new ModelAndView("solution");
        modelAndView.addObject(aStrAttrName, aStr);
        modelAndView.addObject(solutionAttrName, solution);
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
