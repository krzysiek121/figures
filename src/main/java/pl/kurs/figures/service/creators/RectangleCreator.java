package pl.kurs.figures.service.creators;

import org.springframework.stereotype.Service;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.interfaces.FigureCreator;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;

import java.util.Arrays;
import java.util.Map;

@Service
public class RectangleCreator implements FigureCreator {
    @Override
    public String type() {
        return "rectangle";
    }

    @Override
    public Figure createFigure(Map<String, Double> parameters) {

        if (!parameters.keySet().containsAll(Arrays.asList("width", "height"))) {

            throw new ParameterNotFoundException("INVALID_PARAMETERS");
        }
        return new Rectangle(getDouble("width", parameters), getDouble("height", parameters));
    }
}
