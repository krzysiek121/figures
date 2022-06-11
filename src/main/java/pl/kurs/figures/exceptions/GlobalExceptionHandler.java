package pl.kurs.figures.exceptions;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ParameterNotFoundException.class)
    public ResponseEntity handleNullPointerException(ParameterNotFoundException exc) {
        return new ResponseEntity(new ErrorDto(exc.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        return new ResponseEntity(
                exc.getFieldErrors().stream()
                        .map(fe -> ErrorDto.builder()
                                .message(fe.getDefaultMessage())
                                .build()).collect(Collectors.toList())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FigureIdNotFound.class)
    public ResponseEntity handleFigureIdNotFound(FigureIdNotFound exc) {
        return new ResponseEntity(new ErrorIdDto("FIGURE_NOT_FOUND", exc.getId()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TooLargeFigureException.class)
    public ResponseEntity handleTooLargeFigureException(TooLargeFigureException exc) {
        return new ResponseEntity(new ErrorDtoFigure("PARAMETER_SIZE_TOO_LARGE",
                exc.getTooLargeParameters()), HttpStatus.BAD_REQUEST);
    }

    @Value
    @Builder
    static class ErrorDto {
        private String message;
    }

    @Value
    static class ErrorIdDto {
        private String message;
        private int id;
    }

    @Value
    static class ErrorDtoFigure {
        private String message;
        private List<String> parameters;
    }
}
