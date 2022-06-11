package pl.kurs.figures.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rectangle extends Figure {
    @NotNull
    private Double width;
    @NotNull
    private Double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width*height;
    }

    @Override
    public Map<String, Double> getParameters() {
        Map<String, Double > map = new HashMap<>();
        map.put("width", width);
        map.put("height", height);
        return map;
    }
}
