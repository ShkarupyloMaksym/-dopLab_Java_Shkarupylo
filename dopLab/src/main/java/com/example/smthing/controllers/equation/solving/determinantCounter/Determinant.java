package com.example.smthing.controllers.equation.solving.determinantCounter;

public class Determinant {
    //TODO не зрозуміло чому статичні методи було винесено в цей клас

    public static double countDeterminant(double[][] arrayForDetermination) {
        if (arrayForDetermination.length == 1) return arrayForDetermination[0][0];
        double sum = 0;
        for (int i = 0; i < arrayForDetermination.length; i++) {
            sum += Math.pow(-1, i) * arrayForDetermination[0][i] * countDeterminant(cutArray(arrayForDetermination, 0, i));

        }
        return sum;
    }

    public static double[][] cutArray(double[][] arrForCut, int row, int column) {
        double[][] newArr = new double[arrForCut.length - 1][arrForCut[0].length - 1];
        for (int i = 0; i < arrForCut.length; i++)
            for (int j = 0; j < arrForCut[0].length; j++) {
                if (i != row && j != column) {
                    int rowNum = i < row ? i : i - 1;
                    int columnNum = j < column ? j : j - 1;
                    newArr[rowNum][columnNum] = arrForCut[i][j];
                }
            }

        return newArr;
    }
}
