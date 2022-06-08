package pl.kurs.figures.repository;

import liquibase.pro.packaged.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.figures.model.Figure;

import java.util.List;
import java.util.Optional;

public interface FigureRepository extends JpaRepository<Figure, Integer> {

    Optional<Figure> findById(int id);
}
