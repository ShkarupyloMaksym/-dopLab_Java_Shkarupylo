package com.example.smthing.controllers.equation.equationExceptions;

public class EquationIsNotASurfaceException extends Exception {
    @Override
    public String toString() {
        return super.toString()+" Is not a surface";
    }
}
