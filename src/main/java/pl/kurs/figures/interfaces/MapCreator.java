package pl.kurs.figures.interfaces;

import pl.kurs.figures.model.Figure;

import java.util.Map;

public interface MapCreator {

    String type();

    Map<String,Double> createFigureMap(Figure figure);
}
