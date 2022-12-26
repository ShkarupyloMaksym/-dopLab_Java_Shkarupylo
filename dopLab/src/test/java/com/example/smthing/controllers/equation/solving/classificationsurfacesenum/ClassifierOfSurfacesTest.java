package com.example.smthing.controllers.equation.solving.classificationsurfacesenum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClassifierOfSurfacesTest {
    @Test
    public void checkName1(){
        assertEquals(ClassifierOfSurfaces.CONE.getUkrName(), "Конус");
    }
    @Test
    public void checkName2(){
        assertEquals(ClassifierOfSurfaces.DOT.getUkrName(), "Точка");
    }
    @Test
    public void checkName3(){
        assertEquals(ClassifierOfSurfaces.A_PAIR_OF_INTERSECTING_PLANES.getUkrName(), "Пара перетинаючихся площин");
    }
    @Test
    public void checkName4(){
        assertEquals(ClassifierOfSurfaces.ELLIPTICAL_PARABOLOID.getUkrName(), "Еліптичний параболоїд");
    }
    @Test
    public void checkName5(){
        assertEquals(ClassifierOfSurfaces.IMAGINARY_ELLIPSOID.getUkrName(), "Уявний еліпсоїд");
    }

}
