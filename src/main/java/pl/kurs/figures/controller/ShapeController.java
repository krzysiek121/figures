package pl.kurs.figures.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.model.dto.FigureDto;
import pl.kurs.figures.request.CreateFigureRequest;
import pl.kurs.figures.service.ShapeService;

import javax.validation.Valid;
import java.awt.image.BufferedImage;
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
    public FigureDto makeFigure(@RequestBody @Valid CreateFigureRequest createFigureRequest,
                               @CurrentSecurityContext(expression = "authentication?.name")
                                       String username) throws ParameterNotFoundException, IllegalAccessException {
        return modelMapper.map(shapeService.buildFigure(createFigureRequest, username), FigureDto.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FigureDto> getFigures() {
        return shapeService.getHistoryFigures().stream().map(f -> modelMapper.map(f, FigureDto.class)).collect(Collectors.toList());
    }
    @GetMapping("/{id}/draw")
    @ResponseStatus(HttpStatus.CREATED)
    public File draw(@PathVariable int id) throws IOException, IllegalAccessException {
        //JPG/PNG jako zalacznik z narysowana figura o danym ID.
        //400x400 - rozmiar obrazka
        //40x40 - przyklad
        //ma srodku
        //czarno
        //404 - nie ma figury
        //400 - nie mozna narysowac figury bo nie zmiesci sie na plotnie
        return shapeService.draw(id);
    }
}