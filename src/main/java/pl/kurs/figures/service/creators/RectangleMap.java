package pl.kurs.figures.service.creators;

import org.springframework.stereotype.Service;
import pl.kurs.figures.interfaces.MapCreator;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;

import java.util.HashMap;
import java.util.Map;
@Service
public class RectangleMap implements MapCreator {
    @Override
    public String type() {
        return "rectangle";
    }

    @Override
    public Map<String, Double> createFigureMap(Figure figure) {

            Map<String, Double > map = new HashMap<>();
            Rectangle c1 = (Rectangle) figure;
            map.put("width", c1.getWidth());
            map.put("heightt", c1.getHeight());
            return map;
    }
}
