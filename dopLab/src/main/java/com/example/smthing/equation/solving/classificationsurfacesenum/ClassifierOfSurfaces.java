package com.example.smthing.equation.solving.classificationsurfacesenum;

public enum ClassifierOfSurfaces {
    ELLIPSOID("Еліпсоїд"),
    IMAGINARY_ELLIPSOID("Уявний еліпсоїд"),
    DOT("Точка"),
    ONE_SHEETED_HYPERBOLOID("Однопорожнинний гіперболоїд"),
    TWO_SHEETED_HYPERBOLOID("Двопорожнинний гіперболоїд"),
    CONE("Конус"),
    ELLIPTICAL_PARABOLOID("Еліптичний параболоїд"),
    HYPERBOLIC_PARABOLOID("Гіперболічний парабалоїд"),
    ELLIPTICAL_CYLINDER("Еліптичний циліндр"),
    IMAGINARY_ELLIPTICAL_CYLINDER("Уявний еліптичний циліндр"),
    LINE("Пряма"),
    HYPERBOLIC_CYLINDER("Гіперболічний циліндр"),
    A_PAIR_OF_INTERSECTING_PLANES("Пара перетинаючихся площин"),
    PARABOLIC_CYLINDER("Параболічний циліндр"),
    PAIR_OF_PARALLEL_PLANES("Пара паралельних площин"),
    PAIR_OF_IMAGINARY_PARALLEL_PLANES("Пара уявних паралельних площин"),
    PLANE("Площина");

    private final String ukrName;

    ClassifierOfSurfaces(String ukrName) {
        this.ukrName = ukrName;
    }

    public String getUkrName() {
        return ukrName;
    }
}
