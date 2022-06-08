package pl.kurs.figures.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.test.context.support.WithMockUser;

import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;

import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.request.CreateFigureRequest;
import pl.kurs.figures.service.factory.FigureFactory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShapeServiceTest {

    private ShapeService underTest;
    private FigureRepository figureRepository;
    private FigureFactory figureFactory;

    @BeforeEach
    void init() {
        figureFactory = mock(FigureFactory.class);
        figureRepository = mock(FigureRepository.class);
        underTest = new ShapeService(figureRepository, figureFactory);
    }
    @Test
    void shouldCalculateAdditionEquation() throws IllegalAccessException {


        Map<String, Double> map = Map.of("radius", 10.0);
        CreateFigureRequest c1 = new CreateFigureRequest("circle", map);

        Circle f2 = new Circle();
        f2.setDataCreated(LocalDateTime.now());
        f2.setRadius(10.0);
        f2.setType("circle");
        f2.setUsername("username");
        f2.setId(1);

        when(figureRepository.getById(1)).thenReturn(f2);

        assertEquals(figureRepository.getById(1), f2);
        verify(figureRepository, times(1)).getById(1);

    }

/*    @Test
    void shouldReturnCorrectFigure() throws IllegalAccessException {
        //given:
        Map<String, Double> map = Map.of("radius", 10.0);
        CreateFigureRequest c1 = new CreateFigureRequest("circle", map);

        Circle f2 = new Circle();
        f2.setDataCreated(LocalDateTime.now());
        f2.setRadius(10.0);
        f2.setType("circle");
        f2.setUsername("username");
        f2.setId(1);


        when(figureRepository.saveAndFlush(underTest.buildFigure(c1,"username"))).thenReturn(f2);
        assertEquals(f2, underTest.buildFigure(c1,"username"));
        verify(underTest, times(1)).buildFigure(c1,"username");
    }*/
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnCorrectFigureList() throws IllegalAccessException {
        //given:
        Map<String, Double> map = Map.of("radius", 10.0);
        CreateFigureRequest c1 = new CreateFigureRequest("circle", map);

        Circle f2 = new Circle();
        f2.setDataCreated(LocalDateTime.now());
        f2.setRadius(10.0);
        f2.setType("circle");
        f2.setUsername("username");
        f2.setId(1);

        Rectangle f3 = new Rectangle();
        f3.setDataCreated(LocalDateTime.now());
        f3.setHeight(19.0);
        f3.setWidth(10.0);
        f3.setType("circle");
        f3.setUsername("username");
        f3.setId(2);
        List<Figure> figures = Arrays.asList(f2,f3);

        when(figureRepository.findAll()).thenReturn(figures);
        List<Figure> figuresHisotry = underTest.getHistoryFigures();
        assertEquals(2 , figuresHisotry.size());
        assertEquals(figuresHisotry, figures);
        verify(figureRepository, times(1)).findAll();
    }
}

/*
joba periodycznego  codziennie o 23:00 i zeby wyslal maila do administrator systemu z iloscia figur ktore zostaly stworzone danego dnia.
musi być gwarancja wysłania emaila, tylko raz.
 */
