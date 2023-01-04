package com.example.smthing.equation;

import com.example.smthing.equation.equationexceptions.EquationIsNotASurfaceException;
import com.example.smthing.equation.equationexceptions.EquationNotEnoughCoefsException;
import com.example.smthing.equation.equationexceptions.EquationNotNumberException;
import com.example.smthing.equation.equationexceptions.EquationNullException;
import com.example.smthing.equation.solving.DefinerTypeOfSurface;
import com.example.smthing.equation.solving.equationinit.Equation;
import com.example.smthing.equation.writer.DeterminantWriter;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Objects;

import static com.example.smthing.equation.writer.WriterConstants.EQUATION_COEFFS_IN_A_TYPE;
import static com.example.smthing.equation.writer.WriterConstants.XYZ_IN_EQUATION;

public class EquationWithWritingToPage {
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

    private void checkEquationCoefsLength(double[] equationCoefs) throws EquationNotEnoughCoefsException {
        if (equationCoefs.length != 10)
            throw new EquationNotEnoughCoefsException();
    }

    private void makeStringCoeffNotEmpty() {
        for (int i = 0; i < coefficientsString.length; i++)
            if (coefficientsString[i].isEmpty())
                coefficientsString[i] = "0";
    }

    private void makeDoubleCoeffsArray() throws EquationNotNumberException {
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
        makeStringCoeffNotEmpty();
        makeDoubleCoeffsArray();
        if (nullCheck())
            throw new EquationNullException();
    }

    public void fillEquation() {
        StringBuilder equation = new StringBuilder();
        for (int i = 0; i < coefficientsString.length; i++) {
            equation.append(Objects.equals(coefficientsString[i], "1") ? "" : coefficientsString[i]).append(XYZ_IN_EQUATION[i]);
            if (i < coefficientsString.length - 1) {
                equation.append(coefficientsDouble[i + 1] < 0 ? " " : " + ");
            }
        }
        pageModel.addAttribute("EquationText", equation);
    }

    private void fillEquationInAType() {
        StringBuilder equationInAType = new StringBuilder();
        for (int i = 0; i < XYZ_IN_EQUATION.length; i++) {
            equationInAType.append(EQUATION_COEFFS_IN_A_TYPE[i]).append(XYZ_IN_EQUATION[i]).append(i < XYZ_IN_EQUATION.length - 1 ? " + " : "");
        }

        pageModel.addAttribute("EquationInAType", equationInAType);
    }

    public String addAnswer() throws EquationIsNotASurfaceException, EquationNotEnoughCoefsException {
        checkEquationCoefsLength(coefficientsDouble);
        Equation equation = new Equation(coefficientsDouble);

        DefinerTypeOfSurface definerTypeOfSurface = new DefinerTypeOfSurface(equation);
        pageModel.addAttribute("typeOfSurface", definerTypeOfSurface.getType().getUkrName());
        pageModel.addAttribute("SmallSolution", definerTypeOfSurface.getExplanation());
        DeterminantWriter determinantWriter = new DeterminantWriter(coefficientsDouble, pageModel);
        determinantWriter.write(equation.getUsedDeterminants());
        return definerTypeOfSurface.getType().name();
    }
}
