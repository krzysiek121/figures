package pl.kurs.figures.validations;

import lombok.RequiredArgsConstructor;
import pl.kurs.figures.service.factory.FigureFactory;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
public class CheckFigureTypeValidator implements ConstraintValidator<CheckFigureType, String> {

    private final FigureFactory figureFactory;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return figureFactory.getMakeFigure().containsKey(s);
    }
}
