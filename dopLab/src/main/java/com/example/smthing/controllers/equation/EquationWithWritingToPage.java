package com.example.smthing.controllers.equation;

import com.example.smthing.controllers.equation.equationExceptions.EquationNotNumberException;
import com.example.smthing.controllers.equation.equationExceptions.EquationNullException;
import com.example.smthing.controllers.equation.solving.DefineTypeOfSurface;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Objects;

public class EquationWithWritingToPage {
    final String[] XYZinEquation = {"x²", "y²", "z²", "xy", "xz", "yz", "x", "y", "z", " = 0"};
    final String[] EquationCoeffsInAType = {"a11", "a22", "a33", "a12", "a13", "a23", "a1", "a2", "a3", "a0"};

    String[] coefficientsString;
    double[] coefficientsDouble;
    Model pageModel;

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
