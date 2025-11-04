package com.julio.spring6restmvc.controllers;

import com.julio.spring6restmvc.model.Costumer;
import com.julio.spring6restmvc.services.CostumerService;
import com.julio.spring6restmvc.services.CostumerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CostumerController.class)
class CostumerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CostumerService costumerService;

    CostumerServiceImpl costumerServiceImpl = new CostumerServiceImpl();

    @Test
    void testGetCostumers() throws Exception {
        given(costumerService.listCostumers()).willReturn(costumerServiceImpl.listCostumers());

        mockMvc.perform(get("/api/v1/costumer").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)));
    }


    @Test
    void testGetCostumerById() throws Exception {

        Costumer testCostumer = costumerServiceImpl.listCostumers().get(0);

        given(costumerService.getCostumerById(testCostumer.getId())).willReturn(testCostumer);

        mockMvc.perform(get("/api/v1/costumer/" + testCostumer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testCostumer.getId().toString())));

    }
}
