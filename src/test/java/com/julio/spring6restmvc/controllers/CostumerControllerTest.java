package com.julio.spring6restmvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julio.spring6restmvc.helpers.AnomalyCostumer;
import com.julio.spring6restmvc.model.Costumer;
import com.julio.spring6restmvc.services.CostumerService;
import com.julio.spring6restmvc.services.CostumerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CostumerController.class)
class CostumerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CostumerService costumerService;

    CostumerServiceImpl costumerServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @BeforeEach
    void setup(){
        costumerServiceImpl = new CostumerServiceImpl();
    }

    @Test
    void testPatchBeer(){

    }

    @Test
    void testDeleteCostumer() throws Exception {
        Costumer costumer = costumerServiceImpl.listCostumers().get(0);

        mockMvc.perform(delete("/api/v1/costumer/" + costumer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(costumerService).deleteById(uuidArgumentCaptor.capture());

        assertThat(costumer.getId()).isEqualTo(uuidArgumentCaptor.getValue());

    }

    @Test
    void testUpdateCostumer() throws Exception {
        Costumer costumer = costumerServiceImpl.listCostumers().get(0);

        mockMvc.perform(put("/api/v1/costumer/" + costumer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(costumer)))
                .andExpect(status().isNoContent());

        verify(costumerService).updateById(any(UUID.class), any(Costumer.class));
    }

    @Test
    void testCreateNewCostumer() throws Exception{
        Costumer costumer = costumerServiceImpl.listCostumers().get(0);

        costumer.setId(null);
        costumer.setVersion(null);

        given(costumerService.createCostumer(any(Costumer.class))).willReturn(costumerServiceImpl.listCostumers().get(1));

        mockMvc.perform(
                post("/api/v1/costumer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(costumer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

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
