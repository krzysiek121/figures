package pl.kurs.figures.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Circle extends Figure{
    @NotNull
    private Double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }

    @Override
    public Map<String, Double> getParameters() {
        Map<String, Double > map = new HashMap<>();
        map.put("radius", radius);
        return map;
    }
}
