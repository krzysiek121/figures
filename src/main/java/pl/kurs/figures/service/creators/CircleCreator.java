package pl.kurs.figures.service.creators;

import org.springframework.stereotype.Service;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.interfaces.FigureCreator;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;

import java.util.Map;
@Service
public class CircleCreator implements FigureCreator {
    @Override
    public String type() {
        return "circle";
    }

    @Override
    public Figure createFigure(Map<String, Double> parameters) throws ParameterNotFoundException {

        if(!parameters.containsKey("radius")) {
            throw new ParameterNotFoundException("INVALID_PARAMETERS");
        }

        return new Circle(getDouble("radius", parameters));
    }
}
