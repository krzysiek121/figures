package pl.kurs.figures.response;


import lombok.Value;
import pl.kurs.figures.model.Figure;

import java.util.Map;


@Value
public class CommandResponse {

    private int id;
    private double area;
    private String type;
    private Map<String, Double> parameters;


}
