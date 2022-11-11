package com.example.smthing.controllers.equation.solving;

import com.example.smthing.controllers.equation.equationExceptions.EquationIsNotASurfaceException;
import com.example.smthing.controllers.equation.equationExceptions.EquationNotEnoughCoefsException;
import com.example.smthing.controllers.equation.solving.ClassificationSurfacesEnum.ClassificationOfSurfaces;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DefineTypeOfSurfaceTest {
    DefineTypeOfSurface defineTypeOfSurface1;
    DefineTypeOfSurface defineTypeOfSurface2;
    DefineTypeOfSurface defineTypeOfSurface3;
    @Before
    public void setDefineTypeOfSurface1() throws Exception {
        //Приклад 3
        defineTypeOfSurface1 = new DefineTypeOfSurface(new double[]
                {5,2,5,-4,-2,-4,10,-4,-2,4}, null);
    }
    @Before
    public void setDefineTypeOfSurface2() throws Exception {
        //Приклад 1
        defineTypeOfSurface2 = new DefineTypeOfSurface(new double[]
                {1,5,1,2,6,2,-2,6,2,0}, null);
    }

    @Test
    public void checkType1() throws EquationIsNotASurfaceException {
        assertEquals(ClassificationOfSurfaces.EllipticalCylinder, defineTypeOfSurface1.getType());
    }

    @Test
    public void checkType2() throws EquationIsNotASurfaceException {
        assertEquals(defineTypeOfSurface2.getType(), ClassificationOfSurfaces.OneSheetedHyperboloid);
    }

    @Test
    public void trySetNotRight_DefineTypeOfSurface(){
        String ExceptionText = null;
        try {
            defineTypeOfSurface3 = new DefineTypeOfSurface(new double[]
                    {5,2,5,-4,-2,-4,10,-4,-2}, null);
        } catch (EquationNotEnoughCoefsException e) {
            //I don`t know how to check Equality of the Exceptions, so, my var:
            ExceptionText = e.toString();
        }catch (EquationIsNotASurfaceException ignored){

        }
        assertEquals(ExceptionText, new EquationNotEnoughCoefsException().toString());
    }
}
