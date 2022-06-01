package pl.kurs.figures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.figures.model.Figure;

import java.util.List;

public interface FigureRepository extends JpaRepository<Figure, Integer> {

    //@Query("select distinct s from Figure s left join fetch s.parameters")
    //List<Figure> findAllFigures();
}
