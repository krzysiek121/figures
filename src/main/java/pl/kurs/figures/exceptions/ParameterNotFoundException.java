package pl.kurs.figures.exceptions;

import lombok.Value;

@Value
public class ParameterNotFoundException extends RuntimeException {
    private String message;
}
