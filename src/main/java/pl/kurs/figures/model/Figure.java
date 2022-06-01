package pl.kurs.figures.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.figures.request.CreateFigureRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Figure {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;
    private double area;
    private String username;
    private LocalDateTime dataCreated;
    private String type;
    //@ElementCollection
    //private Map<String, Double> parameters;
    public abstract double getArea();
}
