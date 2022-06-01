package pl.kurs.figures.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pl.kurs.figures.FiguresApplication;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.request.CreateFigureRequest;
import pl.kurs.figures.response.CommandResponse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {FiguresApplication.class})
@TestPropertySource(
        locations = "classpath:test.properties")
@AutoConfigureMockMvc
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

        Circle c2 = new Circle(11);
        CreateFigureRequest c1 = new CreateFigureRequest("circle", c2);


        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/shape/getShape/")
                .content(objectMapper.writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("circle"));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        String jsonDataString = objectMapper.writeValueAsString(contentAsString);

        JsonNode deserializedFleet = objectMapper.readTree(jsonDataString);
        CommandResponse cd = objectMapper.readValue(contentAsString, CommandResponse.class);

        Assertions.assertEquals(objectMapper.writeValueAsString(cd.getParameters()),
                objectMapper.writeValueAsString(c2));

        //findAllFromDatabase
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/shape/getShape/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

    }

    @Test
    @WithMockUser(username = "user", roles = "ADMIN")
    void shouldReturnWrongTypeException() throws Exception {
        Circle c2 = new Circle(11);
        CreateFigureRequest c1 = new CreateFigureRequest("circleee", c2);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/shape/getShape/")
                .content(objectMapper.writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("INVALID_TYPE"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException))
                .andReturn();


    }
}
*/
