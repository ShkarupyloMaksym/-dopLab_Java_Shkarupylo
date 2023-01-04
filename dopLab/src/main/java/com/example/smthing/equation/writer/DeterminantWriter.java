package com.example.smthing.equation.writer;

import org.springframework.ui.Model;

import java.util.Map;

public class DeterminantWriter {
    private final Model equationModel;

    private final String startMatrix = "\\(\\begin{vmatrix} ";
    private final String endMatrix = " \\end{vmatrix} \\)";
    double[][] Coefs4;

    public DeterminantWriter(double[] equationCoefs, Model model){
        final double[][] coefs4 = new double[4][4];
        for (int i = 0; i < 3; i++)
            coefs4[i][i] = equationCoefs[i];
        coefs4[0][1] = equationCoefs[3] / 2;
        coefs4[0][2] = equationCoefs[4] / 2;
        coefs4[1][2] = equationCoefs[5] / 2;
        coefs4[0][3] = equationCoefs[6] / 2;
        coefs4[1][3] = equationCoefs[7] / 2;
        coefs4[2][3] = equationCoefs[8] / 2;
        coefs4[3][3] = equationCoefs[9];

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < i; j++)
                coefs4[i][j] = coefs4[j][i];

        equationModel = model;
        Coefs4 = coefs4;
    }
    public void write(Map<String, Double> explain){
        for (String action: explain.keySet()){
            switch (action){
                case "I1":
                    writeCountI1(explain.get(action));
                    break;
                case "I2":
                    writeCountI2(explain.get(action));
                    break;
                case "I3":
                    writeCountI3(explain.get(action));
                    break;
                case "K1":
                    writeCountK1(explain.get(action));
                    break;
                case "K2":
                    writeCountK2(explain.get(action));
                    break;
                case "K4":
                    writeCountK4(explain.get(action));
                    break;
            }
        }
    }

    private void writeCountI1(double result) {
        if (equationModel != null) {
            StringBuilder stringBuilder = new StringBuilder("I1 = ");
            for (int i = 0; i < 3; i++)
                stringBuilder.append(Coefs4[i][i]).append(i < 2 ? " + " : " = ");
            stringBuilder.append(result);

            equationModel.addAttribute("EquationCountI1", stringBuilder);
        }
    }


    private void writeCountI2(double result) {
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

    private void writeCountI3(double result) {
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

    private void writeCountK4(double result) {
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

    private void writeCountK2(double result) {
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

    private void writeCountK1(double result) {
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
