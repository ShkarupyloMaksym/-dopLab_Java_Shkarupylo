package com.example.smthing.equation.equationexceptions;

public class EquationNullException extends Exception {
    @Override
    public String toString() {
        return super.toString() + " Equation has only nulls, it`s not a plain";
    }
}
