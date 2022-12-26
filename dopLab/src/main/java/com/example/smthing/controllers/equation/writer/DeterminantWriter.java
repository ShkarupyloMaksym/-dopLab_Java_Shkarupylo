package com.example.smthing.controllers.equation.writer;

import org.springframework.ui.Model;

public class DeterminantWriter {
    private final Model equationModel;

    private final String startMatrix = "\\(\\begin{vmatrix} ";
    private final String endMatrix = " \\end{vmatrix} \\)";
    double[][] Coefs4;

    public DeterminantWriter(double[][] Coefs4, Model model) {
        equationModel = model;
        this.Coefs4 = Coefs4;
    }

    public void writeCountI1(double result) {
        if (equationModel != null) {
            StringBuilder stringBuilder = new StringBuilder("I1 = ");
            for (int i = 0; i < 3; i++)
                stringBuilder.append(Coefs4[i][i]).append(i < 2 ? " + " : " = ");
            stringBuilder.append(result);

            equationModel.addAttribute("EquationCountI1", stringBuilder);
        }
    }


    public void writeCountI2(double result) {
        if (equationModel != null) {
            StringBuilder stringBuilder = new StringBuilder("I2 = ");
            stringBuilder.append(startMatrix);
            stringBuilder.append(Coefs4[0][0]).append("&").append(Coefs4[0][1]).append("\\\\").append(Coefs4[0][1]).append("&").append(Coefs4[1][1]);
            stringBuilder.append(endMatrix);
            stringBuilder.append(" + ");
            stringBuilder.append(startMatrix);
            stringBuilder.append(Coefs4[0][0]).append("&").append(Coefs4[0][2]).append("\\\\").append(Coefs4[0][2]).append("&").append(Coefs4[2][2]);
            stringBuilder.append(endMatrix);
            stringBuilder.append(" + ");
            stringBuilder.append(startMatrix);
            stringBuilder.append(Coefs4[1][1]).append("&").append(Coefs4[1][2]).append("\\\\").append(Coefs4[1][2]).append("&").append(Coefs4[2][2]);
            stringBuilder.append(endMatrix).append(" = ").append(result);

            equationModel.addAttribute("EquationCountI2", stringBuilder);
        }
    }

    public void writeCountI3(double result) {
        if (equationModel != null) {
            StringBuilder stringBuilder = new StringBuilder("I3 = ");
            stringBuilder.append(startMatrix);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    stringBuilder.append(Coefs4[i][j]).append("&");
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                stringBuilder.append("\\\\");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append(endMatrix).append(" = ").append(result);

            equationModel.addAttribute("EquationCountI3", stringBuilder);
        }

    }

    public void writeCountK4(double result) {
        if (equationModel != null) {
            StringBuilder stringBuilder = new StringBuilder("K4 = ");
            stringBuilder.append(startMatrix);
            for (double[] arr : Coefs4) {
                for (double elem : arr)
                    stringBuilder.append(elem).append("&");
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                stringBuilder.append("\\\\");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append(endMatrix).append(" = ").append(result);

            equationModel.addAttribute("EquationCountK4", stringBuilder);
        }
    }

    public void writeCountK2(double result) {
        if (equationModel != null) {
            StringBuilder stringBuilder = new StringBuilder("K2 = ");
            for (int i = 0; i < 3; i++) {
                stringBuilder.append(startMatrix);

                for (int j = 0; j < 4; j++) {
                    if (j != i) {
                        for (int k = 0; k < 4; k++)
                            if (k != i)
                                stringBuilder.append(Coefs4[j][k]).append("&");
                        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                        stringBuilder.append("\\\\");
                    }
                }
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                stringBuilder.append(endMatrix).append(i < 2 ? " + " : " = ");

            }
            stringBuilder.append(result);

            equationModel.addAttribute("EquationCountK2", stringBuilder);
        }
    }

    public void writeCountK1(double result) {
        if (equationModel != null) {
            StringBuilder stringBuilder = new StringBuilder("K1 = ");
            for (int i = 0; i < 3; i++) {
                stringBuilder.append(startMatrix);
                stringBuilder.append(Coefs4[i][i]).append("&").append(Coefs4[i][3]).append("\\\\").append(Coefs4[i][3]).append("&").append(Coefs4[3][3]);
                stringBuilder.append(endMatrix);
                stringBuilder.append(i < 2 ? " + " : " = ");
            }
            stringBuilder.append(result);

            equationModel.addAttribute("EquationCountK1", stringBuilder);
        }

    }
}
