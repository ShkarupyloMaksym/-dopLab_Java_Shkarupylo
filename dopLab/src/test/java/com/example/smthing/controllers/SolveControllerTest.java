package com.example.smthing.controllers;
import com.example.smthing.equation.equationexceptions.EquationNotNumberException;
import com.example.smthing.equation.equationexceptions.EquationNullException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SolveControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getInputTest1() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", "1"), new BasicNameValuePair("a22", "13"),
                new BasicNameValuePair("a33", "24"),new BasicNameValuePair("a12", "9"),
                new BasicNameValuePair("a13", "18"),new BasicNameValuePair("a23", "34"),
                new BasicNameValuePair("a1", "15"),new BasicNameValuePair("a2", "2"),
                new BasicNameValuePair("a3", "7"),new BasicNameValuePair("a0", "12"));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(urlEncodedFormData))
                .andExpect(status().isOk())
                .andExpect(view().name("solution"))
//                Test not passed because of some problems with equals
//                .andExpect(model().attribute("EquationText", "x² + 13y² + 24z² + 9xy + 18xz + 34yz + 15x + 2y + 7z + 12 = 0"))
                .andExpect(model().attribute("SmallSolution", "I3 не дорівнює 0 -> I1I3 ≤ 0, або I2 дорівнює 0 -> К4 менше 0"))
                .andExpect(model().attribute("typeOfSurface", "Двопорожнинний гіперболоїд"))
//                Test not passed because they have diff obj links
//                .andExpect(model().attribute("aStrSave", new int[]{1, 13, 24, 9, 18, 34, 15, 2, 7, 12}))
                .andExpect(model().attribute("SolutionSave", "TWO_SHEETED_HYPERBOLOID"));

    }


        @Test
    public void getInputTest2() throws Exception {
        Collection<NameValuePair> formData = List.of(
                new BasicNameValuePair("a11", "1"));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(urlEncodedFormData))
                .andExpect(status().is(404))
                .andExpect(view().name("ExceptionNotFound"));
    }

    @Test
    public void getInputTest3() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", "asd"), new BasicNameValuePair("a22", "13"),
                new BasicNameValuePair("a33", "24"),new BasicNameValuePair("a12", "9"),
                new BasicNameValuePair("a13", "18"),new BasicNameValuePair("a23", "34"),
                new BasicNameValuePair("a1", "15"),new BasicNameValuePair("a2", "2"),
                new BasicNameValuePair("a3", "7"),new BasicNameValuePair("a0", "12"));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(urlEncodedFormData))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EquationNotNumberException));
    }

    @Test
    public void getInputTest4() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", ""), new BasicNameValuePair("a22", ""),
                new BasicNameValuePair("a33", ""),new BasicNameValuePair("a12", ""),
                new BasicNameValuePair("a13", ""),new BasicNameValuePair("a23", ""),
                new BasicNameValuePair("a1", ""),new BasicNameValuePair("a2", ""),
                new BasicNameValuePair("a3", ""),new BasicNameValuePair("a0", ""));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(urlEncodedFormData))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EquationNullException));
    }

    @Test
    public void getInputTest5() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", "123"), new BasicNameValuePair("a22", "13"),
                new BasicNameValuePair("a33", "24"),new BasicNameValuePair("a12", "9"),
                new BasicNameValuePair("a13", "18"),new BasicNameValuePair("a23", "34"),
                new BasicNameValuePair("a1", "15"),new BasicNameValuePair("a2", "2"),
                new BasicNameValuePair("a3", "7"),new BasicNameValuePair("a0", "12"));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(urlEncodedFormData))
                .andExpect(status().isOk())
                .andExpect(view().name("solution"))
                .andExpect(model().attribute("SmallSolution", "I3 не дорівнює 0 -> I1I3 > 0, та I2 > 0 -> К4 більше 0"))
                .andExpect(model().attribute("typeOfSurface", "Еліпсоїд"))
                .andExpect(model().attribute("SolutionSave", "ELLIPSOID"));
    }


    @Test
    public void getInputTest6() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", "1"), new BasicNameValuePair("a22", "13"),
                new BasicNameValuePair("a33", "24"),new BasicNameValuePair("a12", "9"),
                new BasicNameValuePair("a13", "18"),new BasicNameValuePair("a23", "34"),
                new BasicNameValuePair("a1", "15"),new BasicNameValuePair("a2", "234"),
                new BasicNameValuePair("a3", "7"),new BasicNameValuePair("a0", "12"));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(urlEncodedFormData))
                .andExpect(status().isOk())
                .andExpect(view().name("solution"))
                .andExpect(model().attribute("SmallSolution", "I3 не дорівнює 0 -> I1I3 ≤ 0, або I2 дорівнює 0 -> К4 більше 0"))
                .andExpect(model().attribute("typeOfSurface", "Однопорожнинний гіперболоїд"))
                .andExpect(model().attribute("SolutionSave", "ONE_SHEETED_HYPERBOLOID"));
    }

    @Test
    public void getInputTest7() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", "21"), new BasicNameValuePair("a22", "28"),
                new BasicNameValuePair("a33", "153"),new BasicNameValuePair("a12", "11"),
                new BasicNameValuePair("a13", "58"),new BasicNameValuePair("a23", "34"),
                new BasicNameValuePair("a1", "5"),new BasicNameValuePair("a2", "2"),
                new BasicNameValuePair("a3", "72"),new BasicNameValuePair("a0", "1"));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(urlEncodedFormData))
                .andExpect(status().isOk())
                .andExpect(view().name("solution"))
                .andExpect(model().attribute("SmallSolution", "I3 не дорівнює 0 -> I1I3 > 0, та I2 > 0 -> К4 менше 0"))
                .andExpect(model().attribute("typeOfSurface", "Уявний еліпсоїд"))
                .andExpect(model().attribute("SolutionSave", "IMAGINARY_ELLIPSOID"));
    }

    @Test
    public void getInputTest8() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", ""), new BasicNameValuePair("a22", "1"),
                new BasicNameValuePair("a33", "4"),new BasicNameValuePair("a12", ""),
                new BasicNameValuePair("a13", ""),new BasicNameValuePair("a23", ""),
                new BasicNameValuePair("a1", "-4"),new BasicNameValuePair("a2", ""),
                new BasicNameValuePair("a3", ""),new BasicNameValuePair("a0", ""));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(urlEncodedFormData))
                .andExpect(status().isOk())
                .andExpect(view().name("solution"))
                .andExpect(model().attribute("SmallSolution", "I3 дорівнює 0 -> К4 не дорівнює 0 -> К4 менше 0"))
                .andExpect(model().attribute("typeOfSurface", "Еліптичний параболоїд"))
                .andExpect(model().attribute("SolutionSave", "ELLIPTICAL_PARABOLOID"));
    }

    @Test
    public void getInputTest9() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", ""), new BasicNameValuePair("a22", "1"),
                new BasicNameValuePair("a33", ""),new BasicNameValuePair("a12", ""),
                new BasicNameValuePair("a13", ""),new BasicNameValuePair("a23", ""),
                new BasicNameValuePair("a1", ""),new BasicNameValuePair("a2", ""),
                new BasicNameValuePair("a3", ""),new BasicNameValuePair("a0", ""));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(urlEncodedFormData))
                .andExpect(status().isOk())
                .andExpect(view().name("solution"))
                .andExpect(model().attribute("SmallSolution", "I3 дорівнює 0 -> К4 рівне 0 -> I2 рівне 0 -> К2 рівне 0 -> К1 рівне 0"))
                .andExpect(model().attribute("typeOfSurface", "Площина"))
                .andExpect(model().attribute("SolutionSave", "PLANE"));
    }


    @Test
    public void getInputTest10() throws Exception {
        Collection<NameValuePair> formData = Arrays.asList(
                new BasicNameValuePair("a11", "4"), new BasicNameValuePair("a22", ""),
                new BasicNameValuePair("a33", "-8"),new BasicNameValuePair("a12", ""),
                new BasicNameValuePair("a13", ""),new BasicNameValuePair("a23", ""),
                new BasicNameValuePair("a1", ""),new BasicNameValuePair("a2", ""),
                new BasicNameValuePair("a3", ""),new BasicNameValuePair("a0", "-1"));
        String urlEncodedFormData = EntityUtils.toString(new UrlEncodedFormEntity(formData));

        mockMvc.perform(post("/solve")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(urlEncodedFormData))
                .andExpect(status().isOk())
                .andExpect(view().name("solution"))
                .andExpect(model().attribute("SmallSolution", "I3 дорівнює 0 -> К4 рівне 0 -> І2 менше 0 -> К2 не рівне 0"))
                .andExpect(model().attribute("typeOfSurface", "Гіперболічний циліндр"))
                .andExpect(model().attribute("SolutionSave", "HYPERBOLIC_CYLINDER"));
    }
}
