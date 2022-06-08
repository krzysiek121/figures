package pl.kurs.figures.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pl.kurs.figures.FiguresApplication;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.model.dto.FigureDto;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.request.CreateFigureRequest;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {FiguresApplication.class})
@TestPropertySource(
        locations = "classpath:test.properties")
@AutoConfigureMockMvc
@Transactional
class ShapeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FigureRepository figureRepository;

    @Test
    @WithMockUser(username = "user", roles = "ADMIN")
    void shouldAddFigureToDatabase() throws Exception {

        Map<String, Double> map = Map.of("radius", 10.0);
        CreateFigureRequest c1 = new CreateFigureRequest("circle", map);


        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/shape/")
                .content(objectMapper.writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("circle"));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        FigureDto cd = objectMapper.readValue(contentAsString, FigureDto.class);

        Assertions.assertEquals(objectMapper.writeValueAsString(cd.getParameters()),
                objectMapper.writeValueAsString(map));

        Assertions.assertEquals(cd.getParameters(), map);
        Assertions.assertEquals(cd.getType(), c1.getType());
        Assertions.assertEquals(figureRepository.getById(cd.getId()).getParameters(), map);
        Assertions.assertEquals(figureRepository.getById(cd.getId()).getType(), c1.getType());
        //findAllFromDatabase
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/shape/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnWrongTypeException() throws Exception {
        Map<String, Double> map = Map.of("radius", 10.0);
        CreateFigureRequest c1 = new CreateFigureRequest("circleee", map);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/shape/")
                .content(objectMapper.writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.[0].message").value("INVALID_TYPE"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andReturn();


    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnWrongParametersException() throws Exception {
        Map<String, Double> map = Map.of("radiusssss", 10.0);
        CreateFigureRequest c1 = new CreateFigureRequest("circle", map);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/shape/")
                .content(objectMapper.writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("INVALID_PARAMETERS"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ParameterNotFoundException))
                .andReturn();


    }

}
