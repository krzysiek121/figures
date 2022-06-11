package pl.kurs.figures.exceptions;

import lombok.Value;

import java.util.List;

@Value
public class TooLargeFigureException extends RuntimeException {
    private List<String> tooLargeParameters;
}
