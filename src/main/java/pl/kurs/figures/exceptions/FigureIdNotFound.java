package pl.kurs.figures.exceptions;

import lombok.Value;

@Value
public class FigureIdNotFound extends RuntimeException{
    private int id;
}
