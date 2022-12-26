package com.example.smthing.controllers.equation.solving.classificationsurfacesenum;

public enum ClassifierOfSurfaces {
    Ellipsoid("Еліпсоїд"),
    ImaginaryEllipsoid("Уявний еліпсоїд"),
    Dot("Точка"),
    OneSheetedHyperboloid("Однопорожнинний гіперболоїд"),
    TwoSheetedHyperboloid("Двопорожнинний гіперболоїд"),
    Cone("Конус"),
    EllipticalParaboloid("Еліптичний параболоїд"),
    HyperbolicParaboloid("Гіперболічний парабалоїд"),
    EllipticalCylinder("Еліптичний циліндр"),
    ImaginaryEllipticalCylinder("Уявний еліптичний циліндр"),
    Line("Пряма"),
    HyperbolicCylinder("Гіперболічний циліндр"),
    APairOfIntersectingPlanes("Пара перетинаючихся площин"),
    ParabolicCylinder("Параболічний циліндр"),
    PairOfParallelPlanes("Пара паралельних площин"),
    PairOfImaginaryParallelPlanes("Пара уявних паралельних площин"),
    Plane("Площина");

    private final String ukrName;

    ClassifierOfSurfaces(String ukrName) {
        this.ukrName = ukrName;
    }

    public String getUkrName() {
        return ukrName;
    }
}
