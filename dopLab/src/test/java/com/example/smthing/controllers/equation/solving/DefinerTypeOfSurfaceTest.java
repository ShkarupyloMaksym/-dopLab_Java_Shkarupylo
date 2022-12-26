package com.example.smthing.controllers.equation.solving;

import com.example.smthing.controllers.equation.equationexceptions.EquationIsNotASurfaceException;
import com.example.smthing.controllers.equation.equationexceptions.EquationNotEnoughCoefsException;
import com.example.smthing.controllers.equation.solving.classificationsurfacesenum.ClassifierOfSurfaces;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DefinerTypeOfSurfaceTest {
    DefinerTypeOfSurface definerTypeOfSurface1;
    DefinerTypeOfSurface definerTypeOfSurface2;
    DefinerTypeOfSurface definerTypeOfSurface3;
    @Before
    public void setDefineTypeOfSurface1() throws Exception {
        //Приклад 3
        definerTypeOfSurface1 = new DefinerTypeOfSurface(new double[]
                {5,2,5,-4,-2,-4,10,-4,-2,4}, null);
    }
    @Before
    public void setDefineTypeOfSurface2() throws Exception {
        //Приклад 1
        definerTypeOfSurface2 = new DefinerTypeOfSurface(new double[]
                {1,5,1,2,6,2,-2,6,2,0}, null);
    }

    @Test
    public void checkType1() throws EquationIsNotASurfaceException {
        assertEquals(ClassifierOfSurfaces.ELLIPTICAL_CYLINDER, definerTypeOfSurface1.getType());
    }

    @Test
    public void checkType2() throws EquationIsNotASurfaceException {
        assertEquals(definerTypeOfSurface2.getType(), ClassifierOfSurfaces.ONE_SHEETED_HYPERBOLOID);
    }

    @Test
    public void trySetNotRight_DefineTypeOfSurface(){
        String ExceptionText = null;
        try {
            definerTypeOfSurface3 = new DefinerTypeOfSurface(new double[]
                    {5,2,5,-4,-2,-4,10,-4,-2}, null);
        } catch (EquationNotEnoughCoefsException e) {
            //I don`t know how to check Equality of the Exceptions, so, my var:
            ExceptionText = e.toString();
        }catch (EquationIsNotASurfaceException ignored){

        }
        assertEquals(ExceptionText, new EquationNotEnoughCoefsException().toString());
    }
}
