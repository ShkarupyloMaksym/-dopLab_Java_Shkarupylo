package com.example.smthing.controllers;

public class FormEquationParams {
    private String[] aStr;

    public FormEquationParams(String a11, String a22, String a33, String a12, String a13, String a23, String a1, String a2, String a3, String a0) {
        aStr = new String[]{a11, a22, a33, a12, a13, a23, a1, a2, a3, a0};
    }



    public String[] getaStr() {
        return aStr;
    }


}
