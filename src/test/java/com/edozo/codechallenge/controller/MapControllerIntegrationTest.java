package com.edozo.codechallenge.controller;

import base.FileUtils;
import com.edozo.codechallenge.dto.MapDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import java.util.List;

import static base.FileUtils.getJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class MapControllerIntegrationTest {

    @Resource
    MapController mapController;

    @Resource
    MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Resource
    ObjectMapper objectMapper;

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

    @Test
    void getAllMaps() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/maps/")
                .contentType(APPLICATION_JSON_VALUE));

        List<MapDto> expectedResponseList = objectMapper.readValue(
                getJson("all-maps-response.json"), new TypeReference<>() {});
        List<MapDto> actualResponseList = objectMapper.readValue(
                resultActions.andReturn().getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(actualResponseList).hasSize(expectedResponseList.size());
        expectedResponseList.forEach(mapDto -> assertThat(actualResponseList).contains(mapDto));
    }
}
