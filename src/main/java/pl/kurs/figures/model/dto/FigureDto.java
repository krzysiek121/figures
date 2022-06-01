package pl.kurs.figures.model.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FigureDto {

    private int id;
    private String type;
    private Map<String, Double> parameters;

}
