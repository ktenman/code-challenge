package com.edozo.codechallenge.controller;

import com.edozo.codechallenge.CodeChallengeApplication;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class MapControllerIntegrationTest {

    @Resource
    MapController mapController;

    @Resource
    MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(mapController)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Test
    void getMapById() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/maps/1")
                .contentType(APPLICATION_JSON_VALUE));

        resultActions.andExpect(jsonPath("$.id").value(1));
    }
}
