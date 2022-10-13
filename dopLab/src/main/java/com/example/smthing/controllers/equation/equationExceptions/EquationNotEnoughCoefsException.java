package com.example.smthing.controllers.equation.equationExceptions;

public class EquationNotEnoughCoefsException extends Exception {
    @Override
    public String toString() {
        return "Equation has not enough coefficients";
    }
}

