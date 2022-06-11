package pl.kurs.figures.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.figures.model.Figure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FigureRepository extends JpaRepository<Figure, Integer> {

    Optional<Figure> findById(int id);
    List<Figure> findFigureByCreatedDateAfter(LocalDateTime time);
}
