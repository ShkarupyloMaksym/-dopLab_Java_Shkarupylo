package com.example.smthing.equation.solving.equationinit;

import com.example.smthing.equation.solving.determinantcounter.Determinant;


import java.util.*;

public class Equation {
    private final double[][] coefs4 = new double[4][4];
    private final Map<String, Double> usedDeterminants = new LinkedHashMap<>();
//    DeterminantWriter determinantWriter;



    /*equationCoefs is array: a11, a22, a33, 2*a12, 2*a13, 2*23, 2*a1, 2*a2, 2*a3, a0*/
    public Equation(double[] equationCoefs) {

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

//        determinantWriter = new DeterminantWriter(coefs4, model);
    }

    public double countI1() {
        double sum = coefs4[0][0] + coefs4[1][1] + coefs4[2][2];
        usedDeterminants.put("I1", sum);
//        determinantWriter.writeCountI1(sum);
        return sum;
    }

    public double countI2() {
        double[][] Coefs3 = Determinant.cutArray(coefs4, 3, 3);
        double sum = 0;
        for (int i = 0; i < 3; i++)
            sum += Determinant.countDeterminant(Determinant.cutArray(Coefs3, i, i));
        usedDeterminants.put("I2", sum);
//        determinantWriter.writeCountI2(sum);
        return sum;
    }

    public double countI3() {
        double sum = Determinant.countDeterminant(Determinant.cutArray(coefs4, 3, 3));
        usedDeterminants.put("I3", sum);
//        determinantWriter.writeCountI3(sum);
        return sum;
    }

    public double countK4() {
        double sum = Determinant.countDeterminant(coefs4);
        usedDeterminants.put("K4", sum);
//        determinantWriter.writeCountK4(sum);
        return sum;
    }

    public double countK2() {
        double sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += Determinant.countDeterminant(Determinant.cutArray(coefs4, i, i));
        }
        usedDeterminants.put("K2", sum);
//        determinantWriter.writeCountK2(sum);
        return sum;
    }

    public double countK1() {
        double sum = 0;
        double[][] dopArrWithout11 = Determinant.cutArray(coefs4, 1, 1);//1,3,4
        sum += Determinant.countDeterminant(Determinant.cutArray(dopArrWithout11, 1, 1));//1 and 4
        sum += Determinant.countDeterminant(Determinant.cutArray(dopArrWithout11, 0, 0));//3 and 4
        double[][] dopArrWithout00 = Determinant.cutArray(coefs4, 0, 0);//2,3,4
        sum += Determinant.countDeterminant(Determinant.cutArray(dopArrWithout00, 1, 1));//2 and 4
        usedDeterminants.put("K1", sum);
//        determinantWriter.writeCountK1(sum);
        return sum;
    }

    public Map<String, Double> getUsedDeterminants() {
        return usedDeterminants;
    }
}
