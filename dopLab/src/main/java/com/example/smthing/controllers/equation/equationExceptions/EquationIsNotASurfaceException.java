package com.example.smthing.controllers.equation.equationExceptions;

public class EquationIsNotASurfaceException extends Exception {
    @Override
    public String toString() {
        return "Is not a surface";
    }
}
