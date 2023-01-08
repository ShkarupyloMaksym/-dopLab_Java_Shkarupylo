package com.example.smthing.data;

import com.example.smthing.SmthingApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = SmthingApplicationTests.class)
public class EquationWithSolutionTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ITable_equationWithSolution repository;

    private equationWithSolution tutorial1;
    private equationWithSolution tutorial2;
    private equationWithSolution tutorial3;
    private equationWithSolution tutorial4;
    private equationWithSolution tutorial5;

    @Before
    public void initializtion() {
        tutorial1 = new equationWithSolution(new double[]{0, 1, 4, 0, 0, 0, -4, 0, 0, 0}, "ELLIPTICAL_PARABOLOID");
        tutorial2 = new equationWithSolution(new double[]{1, 13, 24, 9, 18, 34, 15, 2, 7, 13}, "TWO_SHEETED_HYPERBOLOID");
        tutorial3 = new equationWithSolution(new double[]{123.0, 13.0, 24.0, 9.0, 18.0, 34.0, 15.0, 2.0, 7.0, 12.0}, "ELLIPSOID");
        tutorial4 = new equationWithSolution(new String[]{"1", "13", "24", "9", "18", "34", "15", "234", "7", "12"}, "ONE_SHEETED_HYPERBOLOID");
        tutorial5 = new equationWithSolution(new String[]{"4", "", "-8", "", "", "", "", "", "", "-1"}, "HYPERBOLIC_CYLINDER");
    }

    @Test
    public void testExample() {
        this.entityManager.persist(tutorial1);
    }

    @Test
    public void shouldBeEmpty() {
        Iterable<equationWithSolution> tutorials = repository.findAll();
        assertThat(tutorials).isEmpty();
    }

    @Test
    public void shouldStoreData() {
        equationWithSolution tutorial = repository.save(tutorial1);

        assertThat(tutorial).hasFieldOrPropertyWithValue("equation_coefs", "0.0, 1.0, 4.0, 0.0, 0.0, 0.0, -4.0, 0.0, 0.0, 0.0");
        assertThat(tutorial).hasFieldOrPropertyWithValue("solution", "ELLIPTICAL_PARABOLOID");
    }

    @Test
    public void checkClassGetters1() {
        assertThat(tutorial1.getSolution()).isEqualTo("ELLIPTICAL_PARABOLOID");
        assertThat(tutorial1.getSolutionText()).isEqualTo("Еліптичний параболоїд");
        assertThat(tutorial1.getEquationInNormalView()).isEqualTo("0.0x² + 1.0y² + 4.0z² + 0.0xy + 0.0xz + 0.0yz -4.0x + 0.0y + 0.0z + 0.0 = 0");
    }

    @Test
    public void checkClassGetters2() {
        assertThat(tutorial2.getSolution()).isEqualTo("TWO_SHEETED_HYPERBOLOID");
        assertThat(tutorial2.getSolutionText()).isEqualTo("Двопорожнинний гіперболоїд");
        assertThat(tutorial2.getEquationInNormalView()).isEqualTo("1.0x² + 13.0y² + 24.0z² + 9.0xy + 18.0xz + 34.0yz + 15.0x + 2.0y + 7.0z + 13.0 = 0");
    }

    @Test
    public void checkClassGetters3() {
        assertThat(tutorial3.getSolution()).isEqualTo("ELLIPSOID");
        assertThat(tutorial3.getSolutionText()).isEqualTo("Еліпсоїд");
        assertThat(tutorial3.getEquationInNormalView()).isEqualTo("123.0x² + 13.0y² + 24.0z² + 9.0xy + 18.0xz + 34.0yz + 15.0x + 2.0y + 7.0z + 12.0 = 0");
    }

    @Test
    public void checkClassGetters4() {
        assertThat(tutorial4.getSolution()).isEqualTo("ONE_SHEETED_HYPERBOLOID");
        assertThat(tutorial4.getSolutionText()).isEqualTo("Однопорожнинний гіперболоїд");
        assertThat(tutorial4.getEquationInNormalView()).isEqualTo("x² + 13y² + 24z² + 9xy + 18xz + 34yz + 15x + 234y + 7z + 12 = 0");
    }

    @Test
    public void checkClassGetters5() {
        assertThat(tutorial5.getSolution()).isEqualTo("HYPERBOLIC_CYLINDER");
        assertThat(tutorial5.getSolutionText()).isEqualTo("Гіперболічний циліндр");
        assertThat(tutorial5.getEquationInNormalView()).isEqualTo("4x² + 0y² -8z² + 0xy + 0xz + 0yz + 0x + 0y + 0z -1 = 0");
    }
}
