package pl.kurs.figures.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.request.CreateFigureRequest;
import pl.kurs.figures.service.factory.FigureFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShapeService {

    private final FigureRepository figureRepository;
    private final FigureFactory figureFactory;

    @Transactional
    public Figure buildFigure(CreateFigureRequest createFigureRequest, String username) throws ParameterNotFoundException, IllegalAccessException {

        Figure toSave = figureFactory.createFigure(createFigureRequest);
        //TODO: EntitListner, @CreatedBy, @CreatedDate,
        //spring boot data jpa audit example
        toSave.setUsername(username);
        toSave.setDataCreated(LocalDateTime.now());
        toSave.setType(createFigureRequest.getType());
        figureRepository.saveAndFlush(toSave);

        return toSave;
    }

    @Transactional(readOnly = true)
    public List<Figure> getHistoryFigures() {
        return figureRepository.findAll();
    }
    @Transactional
    public File draw(int id) throws IOException, IllegalAccessException {

        Figure f1 = figureRepository.findById(id).orElseThrow(()->new ParameterNotFoundException("12"));

        return figureFactory.drawFigure(f1);
    }
}
