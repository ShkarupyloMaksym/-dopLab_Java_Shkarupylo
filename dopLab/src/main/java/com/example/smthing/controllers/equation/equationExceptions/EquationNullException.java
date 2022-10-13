package com.example.smthing.controllers.equation.equationExceptions;

public class EquationNullException extends Exception {
    @Override
    public String toString() {
        return "Equation has only nulls, it`s not a plain";
    }
}
