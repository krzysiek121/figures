package pl.kurs.figures.interfaces;

import pl.kurs.figures.model.Figure;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface FigureCreator {

    String type();

    Figure createFigure(Map<String, Double> parameters) throws IllegalAccessException;


    default Double getDouble(String parameter, Map<String, Double> parameters) {
       return parameters.get(parameter);
    }

    File drawFigure(Figure figure) throws IllegalAccessException, IOException;

}
