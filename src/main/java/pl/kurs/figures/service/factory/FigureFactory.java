package pl.kurs.figures.service.factory;

import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.interfaces.FigureCreator;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.request.CreateFigureRequest;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Getter
public class FigureFactory {

    private Map<String, FigureCreator> makeFigure;

    public FigureFactory(Set<FigureCreator> makeFigure) {
        this.makeFigure = makeFigure.stream().collect(Collectors.toMap(FigureCreator::type, Function.identity()));
    }

    public Figure createFigure(CreateFigureRequest createFigureRequest) throws ParameterNotFoundException, IllegalAccessException {
        return makeFigure.get(createFigureRequest.getType()).createFigure(createFigureRequest.getParameters());
    }
    public File drawFigure(Figure figure) throws IllegalAccessException, IOException {
        return makeFigure.get(figure.getType()).drawFigure(figure);
    }
}
