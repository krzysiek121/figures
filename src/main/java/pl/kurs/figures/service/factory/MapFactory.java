package pl.kurs.figures.service.factory;

import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.interfaces.FigureCreator;
import pl.kurs.figures.interfaces.MapCreator;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.request.CreateFigureRequest;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class MapFactory {

    private Map<String, MapCreator> makeFigure;

    public MapFactory(Set<MapCreator> makeFigure) {
        this.makeFigure = makeFigure.stream().collect(Collectors.toMap(MapCreator::type, Function.identity()));;;
    }
    public Map<String,Double> createMap(Optional<Figure> figure) throws ParameterNotFoundException, IllegalAccessException {
        return makeFigure.get(figure.get().getType()).createFigureMap(figure.get());
    }
}
