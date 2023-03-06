package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void noOperands() throws Exception {
        this.mvc.perform(get("/add").param("operand1","").param("operand2",""))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }
    @Test
    public void noOperandsJson() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","").param("operand2",""))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(""))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(""))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value("0"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void wrongOperator() throws Exception {
        this.mvc.perform(get("/subtract").param("operand1","1").param("operand2","1"))//.andDo(print())
            .andExpect(status().is4xxClientError());
    }
    @Test
    public void wrongOperatorJson() throws Exception {
        this.mvc.perform(get("/subtract_json").param("operand1","1").param("operand2","1"))//.andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    //Test 1 for AND
    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10"));
    }
	@Test
    public void and2() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    //Test 2 for AND
    @Test
    public void and3() throws Exception {
        this.mvc.perform(get("/and").param("operand1","1101").param("operand2","1011"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("1001"));
    }
    @Test
    public void and4() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","1101").param("operand2","1011"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1101))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1011))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1001))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    //Test 3 for AND
    @Test
    public void and5() throws Exception {
        this.mvc.perform(get("/and").param("operand1","1011").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("1011"));
    }
    @Test
    public void and6() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","1011").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1011))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1011))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Test 1 for OR
    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }
    @Test
    public void or2() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    //Test 2 for OR
    @Test
    public void or3() throws Exception {
        this.mvc.perform(get("/or").param("operand1","101").param("operand2","010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("111"));
    }
    @Test
    public void or4() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","101").param("operand2","010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(10))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    //Test 3 for OR
    @Test
    public void or5() throws Exception {
        this.mvc.perform(get("/or").param("operand1","10").param("operand2","10000"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10010"));
    }
    @Test
    public void or6() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","10").param("operand2","10000"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(10000))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Test 1 for MULTIPLY
    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("1000110"));
    }
    @Test
    public void multiply2() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1000110))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    //Test 2 for MULTIPLY
    @Test
    public void multiply3() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","101").param("operand2","11"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }
    @Test
    public void multiply4() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","101").param("operand2","11"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    //Test 3 for MULTIPLY
    @Test
    public void multiply5() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","1111").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("11100001"));
    }
    @Test
    public void multiply6() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","1111").param("operand2","1111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(11100001))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
}
