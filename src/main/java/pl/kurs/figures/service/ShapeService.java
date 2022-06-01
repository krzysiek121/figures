package pl.kurs.figures.service;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.request.CreateFigureRequest;
import pl.kurs.figures.response.CommandResponse;
import pl.kurs.figures.service.factory.FigureFactory;
import pl.kurs.figures.service.factory.MapFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShapeService {

    private final FigureRepository figureRepository;
    private final FigureFactory figureFactory;
    private final MapFactory mapFactory;
    @Transactional
    public Figure buildFigure(CreateFigureRequest createFigureRequest, String username) throws ParameterNotFoundException, IllegalAccessException {

        Figure toSave = figureFactory.createFigure(createFigureRequest);
        toSave.setArea(toSave.getArea());
        toSave.setUsername(username);
        toSave.setDataCreated(LocalDateTime.now());
        //toSave.setParameters(createFigureRequest.getParameters());
        toSave.setType(createFigureRequest.getType());
        figureRepository.saveAndFlush(toSave);
        System.out.println(mapFactory.createMap(java.util.Optional.of(toSave)));
        return toSave;
    }
    @Transactional(readOnly = true)
    public List<Figure> getHistoryFigures() {
        return figureRepository.findAll();
    }
}
