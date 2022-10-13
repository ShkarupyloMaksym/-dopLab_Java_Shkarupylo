package com.example.smthing.controllers.equation.solving.EquationInit;

import com.example.smthing.controllers.equation.solving.determinantCounter.Determinant;
import com.example.smthing.controllers.equation.writer.DeterminantWriter;

public class Equation {
    double[][] Coefs4 = new double[4][4];
    DeterminantWriter determinantWriter;


    /*equationCoefs is array: a11, a22, a33, 2*a12, 2*a13, 2*23, 2*a1, 2*a2, 2*a3, a0*/
    public Equation(double[] equationCoefs) {

        for (int i = 0; i < 3; i++)
            Coefs4[i][i] = equationCoefs[i];
        Coefs4[0][1] = equationCoefs[3] / 2;
        Coefs4[0][2] = equationCoefs[4] / 2;
        Coefs4[1][2] = equationCoefs[5] / 2;
        Coefs4[0][3] = equationCoefs[6] / 2;
        Coefs4[1][3] = equationCoefs[7] / 2;
        Coefs4[2][3] = equationCoefs[8] / 2;
        Coefs4[3][3] = equationCoefs[9];

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < i; j++)
                Coefs4[i][j] = Coefs4[j][i];

        determinantWriter = new DeterminantWriter(Coefs4);
    }

    public double countI1() {
        double sum = Coefs4[0][0] + Coefs4[1][1] + Coefs4[2][2];
        determinantWriter.Write_countI1(sum);
        return sum;
    }

    public double countI2() {
        double[][] Coefs3 = Determinant.cutArray(Coefs4, 3, 3);
        double sum = 0;
        for (int i = 0; i < 3; i++)
            sum += Determinant.countDeterminant(Determinant.cutArray(Coefs3, i, i));
        determinantWriter.Write_countI2(sum);
        return sum;
    }

    public double countI3() {
        double sum = Determinant.countDeterminant(Determinant.cutArray(Coefs4, 3, 3));
        determinantWriter.Write_countI3(sum);
        return sum;
    }

    public double countK4() {
        double sum = Determinant.countDeterminant(Coefs4);
        determinantWriter.Write_countK4(sum);
        return sum;
    }

    public double countK2() {
        double sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += Determinant.countDeterminant(Determinant.cutArray(Coefs4, i, i));
        }
        determinantWriter.Write_countK2(sum);
        return sum;
    }

    public double countK1() {
        double sum = 0;
        double[][] dopArrWithout11 = Determinant.cutArray(Coefs4, 1, 1);//1,3,4
        sum += Determinant.countDeterminant(Determinant.cutArray(dopArrWithout11, 1, 1));//1 and 4
        sum += Determinant.countDeterminant(Determinant.cutArray(dopArrWithout11, 0, 0));//3 and 4
        double[][] dopArrWithout00 = Determinant.cutArray(Coefs4, 0, 0);//2,3,4
        sum += Determinant.countDeterminant(Determinant.cutArray(dopArrWithout00, 1, 1));//2 and 4
        determinantWriter.Write_countK1(sum);
        return sum;
    }

}
