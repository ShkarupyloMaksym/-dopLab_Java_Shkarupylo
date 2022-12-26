package com.example.smthing.controllers.equation.solving.classificationsurfacesenum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassificationOfSurfacesTest {
    @Test
    public void checkName1(){
        assertEquals(ClassificationOfSurfaces.Cone.getUkrName(), "Конус");
    }
    @Test
    public void checkName2(){
        assertEquals(ClassificationOfSurfaces.Dot.getUkrName(), "Точка");
    }
    @Test
    public void checkName3(){
        assertEquals(ClassificationOfSurfaces.APairOfIntersectingPlanes.getUkrName(), "Пара перетинаючихся площин");
    }
    @Test
    public void checkName4(){
        assertEquals(ClassificationOfSurfaces.EllipticalParaboloid.getUkrName(), "Еліптичний параболоїд");
    }
    @Test
    public void checkName5(){
        assertEquals(ClassificationOfSurfaces.ImaginaryEllipsoid.getUkrName(), "Уявний еліпсоїд");
    }

}
