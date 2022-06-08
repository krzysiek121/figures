package pl.kurs.figures.mappings;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.dto.FigureDto;


@Service
public class FigureToFigureDto implements Converter<Figure, FigureDto> {

    @Override
    public FigureDto convert(MappingContext<Figure, FigureDto> mappingContext) {
        Figure figure = mappingContext.getSource();
        return FigureDto.builder()
                .id(figure.getId())
                .type(figure.getType())
                .area(figure.getArea())
                .parameters(figure.getParameters())
                .build();

    }
}
