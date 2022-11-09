package com.example.smthing.controllers.equation.equationExceptions;

public class EquationIsNotASurfaceException extends Exception {
    @Override
    public String toString() {
        //TODO скоріше за все буде втрачено стектрейс при виводі. Краще було б перевикористати меседж java.lang.Exception#Exception(java.lang.String)
        return "Is not a surface";
    }
}
