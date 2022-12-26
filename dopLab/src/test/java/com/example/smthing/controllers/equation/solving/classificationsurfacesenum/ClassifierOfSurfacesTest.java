package com.example.smthing.controllers.equation.solving.classificationsurfacesenum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassifierOfSurfacesTest {
    @Test
    public void checkName1(){
        assertEquals(ClassifierOfSurfaces.Cone.getUkrName(), "Конус");
    }
    @Test
    public void checkName2(){
        assertEquals(ClassifierOfSurfaces.Dot.getUkrName(), "Точка");
    }
    @Test
    public void checkName3(){
        assertEquals(ClassifierOfSurfaces.APairOfIntersectingPlanes.getUkrName(), "Пара перетинаючихся площин");
    }
    @Test
    public void checkName4(){
        assertEquals(ClassifierOfSurfaces.EllipticalParaboloid.getUkrName(), "Еліптичний параболоїд");
    }
    @Test
    public void checkName5(){
        assertEquals(ClassifierOfSurfaces.ImaginaryEllipsoid.getUkrName(), "Уявний еліпсоїд");
    }

}
