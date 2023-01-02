package com.example.smthing.equation.equationexceptions;

public class EquationNotEnoughCoefsException extends Exception {
    @Override
    public String toString() {
        return super.toString() + " Equation has not enough coefficients";
    }
}

