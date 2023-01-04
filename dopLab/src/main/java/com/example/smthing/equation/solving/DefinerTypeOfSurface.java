package com.example.smthing.equation.solving;

import com.example.smthing.equation.equationexceptions.EquationIsNotASurfaceException;
import com.example.smthing.equation.solving.classificationsurfacesenum.ClassifierOfSurfaces;
import com.example.smthing.equation.solving.equationinit.Equation;

public class DefinerTypeOfSurface {
    private final Equation equationToDefine;
    private ClassifierOfSurfaces type;
    private final StringBuilder explanation = new StringBuilder();

    public DefinerTypeOfSurface(Equation equation){
        equationToDefine = equation;
    }


    //TODO не дуже зрозумілі імена методів,
    // вся класифікація виконана як ланцюжок викликів, тому складно зрозуміти стейтмашину (знову ж вона не покрита тестами).
    // Можливо варто винести стейтмашину на 1 рівень (наприклад якось зробити через світч)
    private ClassifierOfSurfaces defineType() throws EquationIsNotASurfaceException {
        if (equationToDefine.countI3() == 0)
            return I3is0();
        return I3isNot0();

    }

    public ClassifierOfSurfaces getType() throws EquationIsNotASurfaceException {
        if(type==null)
            type = defineType();
        return type;
    }
    public String getExplanation(){
        return explanation.toString();
    }

    private ClassifierOfSurfaces I3isNot0() throws EquationIsNotASurfaceException {
        //TODO  пояснення формується у вигляді строки, тобто його буде складно аналізувати в коді після завершення обчислень
        // (змішані рівні бізнес логіки та відображення)
        explanation.append("I3 не дорівнює 0 -> ");
        double I1 = equationToDefine.countI1();
        double I2 = equationToDefine.countI2();
        double I3 = equationToDefine.countI3();
        if (I2 > 0 && I1 * I3 > 0)
            return I1I3biggerThan0_I3();
        if (I2 <= 0 || I1 * I3 <= 0)
            return I1I3lessThan0_I3();
        throw new EquationIsNotASurfaceException();
    }

    private ClassifierOfSurfaces I1I3lessThan0_I3() {
        explanation.append("I1I3 ≤ 0, або I2 дорівнює 0 -> ");
        double K4 = equationToDefine.countK4();
        if (K4 > 0)
            return K4biggerThan0_I1I3lessThan0_I3();
        if (K4 < 0)
            return K4lessThan0_I1I3lessThan0_I3();
        return K4is0_I1I3lessThan0_I3();
    }

    private ClassifierOfSurfaces K4is0_I1I3lessThan0_I3() {
        explanation.append("К4 дорівнює 0");
        return ClassifierOfSurfaces.CONE;
    }

    private ClassifierOfSurfaces K4lessThan0_I1I3lessThan0_I3() {
        explanation.append("К4 менше 0");
        return ClassifierOfSurfaces.TWO_SHEETED_HYPERBOLOID;
    }

    private ClassifierOfSurfaces K4biggerThan0_I1I3lessThan0_I3() {
        explanation.append("К4 більше 0");
        return ClassifierOfSurfaces.ONE_SHEETED_HYPERBOLOID;
    }

    private ClassifierOfSurfaces I1I3biggerThan0_I3() {
        explanation.append("I1I3 > 0, та I2 > 0 -> ");
        double K4 = equationToDefine.countK4();
        if (K4 > 0)
            return K4biggerThan0_I1I3biggerThan0_I3();
        if (K4 < 0)
            return K4lessThan0_I1I3biggerThan0_I3();
        return K4is0_I1I3biggerThan0_I3();
    }

    private ClassifierOfSurfaces K4is0_I1I3biggerThan0_I3() {
        explanation.append("К4 дорівнює 0");
        return ClassifierOfSurfaces.DOT;
    }

    private ClassifierOfSurfaces K4lessThan0_I1I3biggerThan0_I3() {
        explanation.append("К4 менше 0");
        return ClassifierOfSurfaces.IMAGINARY_ELLIPSOID;
    }

    private ClassifierOfSurfaces K4biggerThan0_I1I3biggerThan0_I3() {
        explanation.append("К4 більше 0");
        return ClassifierOfSurfaces.ELLIPSOID;
    }

    private ClassifierOfSurfaces I3is0() throws EquationIsNotASurfaceException {
        explanation.append("I3 дорівнює 0 -> ");
        if (equationToDefine.countK4() == 0)
            return K4is0_I3is0();
        return K4isNot0_I3is0();
    }

    private ClassifierOfSurfaces K4isNot0_I3is0() {
        explanation.append("К4 не дорівнює 0 -> ");
        if (equationToDefine.countK4() < 0)
            return K4LessThan0_I3is0();
        return K4biggerThan0_I3is0();
    }

    private ClassifierOfSurfaces K4biggerThan0_I3is0() {
        explanation.append("К4 більше 0");
        return ClassifierOfSurfaces.HYPERBOLIC_PARABOLOID;
    }

    private ClassifierOfSurfaces K4LessThan0_I3is0() {
        explanation.append("К4 менше 0");
        return ClassifierOfSurfaces.ELLIPTICAL_PARABOLOID;
    }

    private ClassifierOfSurfaces K4is0_I3is0() throws EquationIsNotASurfaceException {
        explanation.append("К4 рівне 0 -> ");
        double I2 = equationToDefine.countI2();
        if (I2 > 0)
            return I2BiggerThan0_I3is0();
        if (I2 < 0)
            return I2LessThan0_I3is0();
        return I2is0_I3is0();
    }

    private ClassifierOfSurfaces I2is0_I3is0() {
        explanation.append("I2 рівне 0 -> ");
        if (equationToDefine.countK2() == 0)
            return K2is0_I2is0_I3is0();
        return K2isNot0_I2is0_I3is0();
    }

    private ClassifierOfSurfaces K2isNot0_I2is0_I3is0() {
        explanation.append("К2 не рівне 0");
        return ClassifierOfSurfaces.PARABOLIC_CYLINDER;
    }

    private ClassifierOfSurfaces K2is0_I2is0_I3is0() {
        explanation.append("К2 рівне 0 -> ");
        double K1 = equationToDefine.countK1();
        if (K1 < 0)
            return K1LessThan0_K2is0_I2is0_I3is0();
        if (K1 > 0)
            return K1biggerThan0_K2is0_I2is0_I3is0();
        return K1is0_K2is0_I2is0_I3is0();
    }

    private ClassifierOfSurfaces K1is0_K2is0_I2is0_I3is0() {
        explanation.append("К1 рівне 0");
        return ClassifierOfSurfaces.PLANE;
    }


    private ClassifierOfSurfaces K1biggerThan0_K2is0_I2is0_I3is0() {
        explanation.append("К1 більше 0");
        return ClassifierOfSurfaces.PAIR_OF_IMAGINARY_PARALLEL_PLANES;
    }

    private ClassifierOfSurfaces K1LessThan0_K2is0_I2is0_I3is0() {
        explanation.append("К1 менше 0");
        return ClassifierOfSurfaces.PAIR_OF_PARALLEL_PLANES;
    }

    private ClassifierOfSurfaces I2LessThan0_I3is0() {
        explanation.append("І2 менше 0 -> ");
        if (equationToDefine.countK2() == 0)
            return K2is0_I2LessThan0_I3is0();
        return K2isNot0_I2LessThan0_I3is0();
    }

    private ClassifierOfSurfaces K2isNot0_I2LessThan0_I3is0() {
        explanation.append("К2 не рівне 0");
        return ClassifierOfSurfaces.HYPERBOLIC_CYLINDER;
    }

    private ClassifierOfSurfaces K2is0_I2LessThan0_I3is0() {
        explanation.append("І2 рівне 0");
        return ClassifierOfSurfaces.A_PAIR_OF_INTERSECTING_PLANES;
    }

    private ClassifierOfSurfaces I2BiggerThan0_I3is0() throws EquationIsNotASurfaceException {
        explanation.append("І2 більше 0 -> ");
        double I1 = equationToDefine.countI1();
        double K2 = equationToDefine.countK2();
        if (I1 * K2 < 0)
            return I1K2lessThan0_I3is0();
        if (I1 * K2 > 0)
            return I1K2biggerThan0_I3is0();
        if (K2 == 0)
            return K2is0_I2BiggerThan0_I3is0();
        throw new EquationIsNotASurfaceException();
    }

    private ClassifierOfSurfaces K2is0_I2BiggerThan0_I3is0() {
        explanation.append("К2 рівне 0");
        return ClassifierOfSurfaces.LINE;
    }

    private ClassifierOfSurfaces I1K2biggerThan0_I3is0() {
        explanation.append("І1К2 більше 0");
        return ClassifierOfSurfaces.IMAGINARY_ELLIPTICAL_CYLINDER;
    }

    private ClassifierOfSurfaces I1K2lessThan0_I3is0() {
        explanation.append("І1К2 менше 0");
        return ClassifierOfSurfaces.ELLIPTICAL_CYLINDER;
    }
}

