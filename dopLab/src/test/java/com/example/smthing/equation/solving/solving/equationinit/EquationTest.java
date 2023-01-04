package com.example.smthing.equation.solving.solving.equationinit;

import com.example.smthing.equation.solving.equationinit.Equation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EquationTest {
    Equation equation1;
    Equation equation2;
    @Before
    public void setDefineTypeOfSurface1(){
        //Приклад 3
        equation1 = new Equation(new double[]
                {5,2,5,-4,-2,-4,10,-4,-2,4});
    }
    @Before
    public void setDefineTypeOfSurface2(){
        //Приклад 1
        equation2 = new Equation(new double[]
                {1,5,1,2,6,2,-2,6,2,0});
    }

    @Test
    public void checkEquation1(){
        assertEquals(equation1.countI3(), 0,0);
        assertEquals(equation1.countI1(), 12,0);
        assertEquals(equation1.countI2(), 36, 0);
        assertEquals(equation1.countK4(), 0, 0);
        assertEquals(equation1.countK1(), 18, 0);
        assertEquals(equation1.countK2(), -36, 0);
    }

    @Test
    public void checkEquation2(){
        assertEquals(equation2.countI3(), -36,0);
        assertEquals(equation2.countI1(), 7,0);
        assertEquals(equation2.countI2(), 0, 0);
        assertEquals(equation2.countK4(), 36, 0);
    }
}
