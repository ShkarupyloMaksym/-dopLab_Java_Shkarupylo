package com.example.smthing.controllers.equation.solving.determinantcounter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeterminantTest {
    double[][] arr1;
    double[][] arr2;
    double[][] arr3;
    double[][] arr4;
    double[][] arr5;
    @Before
    public void ArraysInit(){
        arr1 = new double[][]{{1}};
        arr2 = new double[][]{{1,2},
                              {3,4}};
        arr3 = new double[][]{{3,1,6},
                              {1,7,3},
                              {7,2,8}};
        arr4 = new double[][]{{12,234,54,1},
                              {23,42,12,3},
                              {12,4,4,14,12},
                              {243,12,12,45}};
        arr5 = new double[][]{{2,5,4,1,2},
                              {2,1,3,5,1},
                              {6,1,4,1,6},
                              {3,3,2,3,5},
                              {2,6,1,8,5}};
    }

    @Test
    public void testArr1(){
        assertEquals(Determinant.countDeterminant(arr1),1,0);
    }
    @Test
    public void testArr2(){
        assertEquals(Determinant.countDeterminant(arr2),-2,0);
    }
    @Test
    public void testArr3(){
        assertEquals(Determinant.countDeterminant(arr3),-119,0);
    }
    @Test
    public void testArr4(){
        assertEquals(Determinant.countDeterminant(arr4),-1168560,0);
    }
    @Test
    public void testArr5(){
        assertEquals(Determinant.countDeterminant(arr5),-568,0);
    }
}
