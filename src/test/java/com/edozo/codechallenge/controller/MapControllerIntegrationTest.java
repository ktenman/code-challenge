package com.edozo.codechallenge.controller;

import com.edozo.codechallenge.dto.MapDto;
import com.edozo.codechallenge.repository.MapRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MapControllerIntegrationTest {

    @Resource
    MapController mapController;

    @Resource
    MapRepository mapRepository;

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
        mapRepository.init();
    }

    @Test
    void getMapById() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/maps/122")
                .contentType(APPLICATION_JSON_VALUE));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(122))
                .andExpect(jsonPath("$.userId").value(321))
                .andExpect(jsonPath("$.address").value("Bag End, 1 Bagshot Row, Hobbiton, The Shire"))
                .andExpect(jsonPath("$.coordinates.bottomLeftX").value(601519.775390625))
                .andExpect(jsonPath("$.coordinates.bottomLeftY").value(198284.912109375))
                .andExpect(jsonPath("$.coordinates.topRightX").value(629290.771484375))
                .andExpect(jsonPath("$.coordinates.topRightY").value(233514.404296875))
                .andExpect(jsonPath("$.downloadUrl")
                        .value("https://https://storage-development.s3.eu-west-2.amazonaws.com/321/122/map.png"));
    }

    @Test
    void getMapById_whenNotFound() throws Exception {
        mockMvc.perform(get("/maps/1")
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
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

    @Test
    void save() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/maps")
                .contentType(APPLICATION_JSON_VALUE)
                .content(getJson("create-map-request.json")));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Validate against empty fields")
    void saveWhenEmptyRequest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/maps")
                .contentType(APPLICATION_JSON_VALUE));

        resultActions.andExpect(status().isBadRequest());
    }
}
