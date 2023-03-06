package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.validation.constraints.Null;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }

	@Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    @Test
    public void noOperands() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","+").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    @Test
    public void noOperator() throws Exception {
        this.mvc.perform(post("/").param("operand1","1").param("operator","").param("operand2","1"))
            .andExpect(status().isOk())
            .andExpect(view().name("Error"));
    }

    @Test
    public void add() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10001"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand2", "1010"));
    }

    @Test
    public void and() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","&").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand2", "1010"));
    }

    @Test
    public void and2() throws Exception {
        this.mvc.perform(post("/").param("operand1","1101").param("operator","&").param("operand2","1011"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1001"))
            .andExpect(model().attribute("operand1", "1101"))
            .andExpect(model().attribute("operand2", "1011"));
    }

    @Test
    public void and3() throws Exception {
        this.mvc.perform(post("/").param("operand1","1011").param("operator","&").param("operand2","1111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1011"))
            .andExpect(model().attribute("operand1", "1011"))
            .andExpect(model().attribute("operand2", "1111"));
    }
    
    @Test
    public void or() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","|").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand2", "1010"));
    }

    @Test
    public void or2() throws Exception {
        this.mvc.perform(post("/").param("operand1","101").param("operator","|").param("operand2","010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "111"))
            .andExpect(model().attribute("operand1", "101"))
            .andExpect(model().attribute("operand2", "010"));
    }

    @Test
    public void or3() throws Exception {
        this.mvc.perform(post("/").param("operand1","10").param("operator","|").param("operand2","10000"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10010"))
            .andExpect(model().attribute("operand1", "10"))
            .andExpect(model().attribute("operand2", "10000"));
    }

    @Test
    public void multiply() throws Exception {
    this.mvc.perform(post("/").param("operand1", "10").param("operator", "*").param("operand2", "101"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1010"))
            .andExpect(model().attribute("operand1", "10"))
            .andExpect(model().attribute("operand2", "101"));
    }

    @Test
    public void multiply2() throws Exception {
    this.mvc.perform(post("/").param("operand1", "111").param("operator", "*").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1000110"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand2", "1010"));
    }

    @Test
    public void multiply3() throws Exception {
    this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "101"))
            .andExpect(model().attribute("operand2", "11"));
    }

    @Test
    public void multiply4() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1111").param("operator", "*").param("operand2", "1111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "11100001"))
            .andExpect(model().attribute("operand1", "1111"))
            .andExpect(model().attribute("operand2", "1111"));
    }

}