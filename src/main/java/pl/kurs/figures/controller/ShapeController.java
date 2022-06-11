package pl.kurs.figures.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.model.dto.FigureDto;
import pl.kurs.figures.request.CreateFigureRequest;
import pl.kurs.figures.service.ShapeService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shape/")
@RequiredArgsConstructor
public class ShapeController {

    private final ShapeService shapeService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FigureDto makeFigure(@RequestBody @Valid CreateFigureRequest createFigureRequest) throws ParameterNotFoundException, IllegalAccessException {
        return modelMapper.map(shapeService.buildFigure(createFigureRequest), FigureDto.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FigureDto> getFigures() {
        return shapeService.getHistoryFigures().stream().map(f -> modelMapper.map(f, FigureDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}/draw")
    @ResponseStatus(HttpStatus.CREATED)
    public File draw(@PathVariable int id) throws IOException, IllegalAccessException {
        return shapeService.draw(id);
    }

    @Scheduled(cron = "@midnight", zone = "Europe/Warsaw")
    public void sendNewFigures() throws MessagingException {
        shapeService.getNewFiguresAndSend();
    }
}