package pl.kurs.figures.mappings;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.dto.FigureDto;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.service.factory.MapFactory;

@Service
@RequiredArgsConstructor
public class FigureToFigureDto implements Converter<Figure, FigureDto> {

    private final MapFactory mapFactory;
    @SneakyThrows
    @Override
    public FigureDto convert(MappingContext<Figure, FigureDto> mappingContext) {
        Figure figure = mappingContext.getSource();
        return FigureDto.builder()
                .id(figure.getId())
                .type(figure.getType())
                .parameters(mapFactory.createMap(java.util.Optional.of(figure)))
                .build();

    }
}
