package com.example.smthing.controllers.equation;

import com.example.smthing.controllers.equation.equationExceptions.EquationNotNumberException;
import com.example.smthing.controllers.equation.equationExceptions.EquationNullException;
import com.example.smthing.controllers.equation.solving.DefineTypeOfSurface;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Objects;

import static com.example.smthing.controllers.equation.writer.WriterConstants.EquationCoeffsInAType;
import static com.example.smthing.controllers.equation.writer.WriterConstants.XYZinEquation;

public class EquationWithWritingToPage {
    //TODO можливі гонки при паралельній роботі
    private final String[] coefficientsString;
    private double[] coefficientsDouble;
    //TODO не зрозуміло чи клас є частиною шару відображення чи бізнес логіки (для відобження забагато коду)
    private final Model pageModel;

    private boolean nullCheck() {
        for (double number : coefficientsDouble)
            if (number != 0)
                return false;
        return true;
    }

    private void MakeStringCoeffNotEmpty() {
        for (int i = 0; i < coefficientsString.length; i++)
            if (coefficientsString[i].isEmpty())
                coefficientsString[i] = "0";
    }

    private void MakeDoubleCoeffsArray() throws EquationNotNumberException {
        coefficientsDouble = new double[coefficientsString.length];
        for (int i = 0; i < coefficientsString.length; i++) {
            try {
                coefficientsDouble[i] = Double.parseDouble(coefficientsString[i]);
            } catch (Exception e) {
                pageModel.addAttribute("Error", coefficientsString[i] + " Is not a double number");
                throw new EquationNotNumberException();
            }
        }
    }

    public EquationWithWritingToPage(Model model, String[] coefficients) throws EquationNullException, EquationNotNumberException {
        pageModel = model;
        coefficientsString = Arrays.copyOf(coefficients, coefficients.length);
        MakeStringCoeffNotEmpty();
        MakeDoubleCoeffsArray();
        if (nullCheck())
            throw new EquationNullException();
    }

    public void FillEquation() {
        StringBuilder equation = new StringBuilder();
        for (int i = 0; i < coefficientsString.length; i++) {
            equation.append(Objects.equals(coefficientsString[i], "1") ? "" : coefficientsString[i]).append(XYZinEquation[i]);
            if (i < coefficientsString.length - 1) {
                equation.append(coefficientsDouble[i + 1] < 0 ? " " : " + ");
            }
        }
        pageModel.addAttribute("EquationText", equation);
    }

    private void FillEquationInAType() {
        StringBuilder equationInAType = new StringBuilder();
        for (int i = 0; i < XYZinEquation.length; i++) {
            equationInAType.append(EquationCoeffsInAType[i]).append(XYZinEquation[i]).append(i < XYZinEquation.length - 1 ? " + " : "");
        }

        pageModel.addAttribute("EquationInAType", equationInAType);
    }

    public String AddAnswer() throws Exception {
        DefineTypeOfSurface defineTypeOfSurface = new DefineTypeOfSurface(coefficientsDouble);
        pageModel.addAttribute("typeOfSurface", defineTypeOfSurface.type.getUkrName());
        pageModel.addAttribute("SmallSolution", defineTypeOfSurface.explanation);
        return defineTypeOfSurface.type.name();
    }
}
