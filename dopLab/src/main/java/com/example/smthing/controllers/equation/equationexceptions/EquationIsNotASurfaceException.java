package com.example.smthing.controllers.equation.equationexceptions;

public class EquationIsNotASurfaceException extends Exception {
    @Override
    public String toString() {
        return super.toString()+" Is not a surface";
    }
}
