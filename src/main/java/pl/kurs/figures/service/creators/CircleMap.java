package pl.kurs.figures.service.creators;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.kurs.figures.interfaces.MapCreator;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;

import java.util.HashMap;
import java.util.Map;
@Service

public class CircleMap implements MapCreator {
    @Override
    public String type() {
        return "circle";
    }

    @Override
    public Map<String, Double> createFigureMap(Figure figure) {
        Map<String, Double > map = new HashMap<>();
        Circle c1 = (Circle) figure;
        map.put("radius", c1.getRadius());
        return map;
    }
}
