package pl.kurs.figures.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.figures.exceptions.FigureIdNotFound;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.repository.FigureRepository;
import pl.kurs.figures.request.CreateFigureRequest;
import pl.kurs.figures.service.factory.FigureFactory;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShapeService {

    private final FigureRepository figureRepository;
    private final FigureFactory figureFactory;
    private final EmailSenderService emailSenderService;

    @Transactional
    public Figure buildFigure(CreateFigureRequest createFigureRequest) throws ParameterNotFoundException, IllegalAccessException {

        Figure toSave = figureFactory.createFigure(createFigureRequest);
        toSave.setType(createFigureRequest.getType());
        figureRepository.saveAndFlush(toSave);

        return toSave;
    }

    @Transactional(readOnly = true)
    public List<Figure> getHistoryFigures() {
        return figureRepository.findAll();
    }

    @Transactional
    public File draw(int id) throws IOException, IllegalAccessException {
        Figure f1 = figureRepository.findById(id).orElseThrow(() -> new FigureIdNotFound(id));
        return figureFactory.drawFigure(f1);
    }

    public void getNewFiguresAndSend() throws MessagingException {
        LocalDateTime targetTime = LocalDateTime.now().minusHours(24);
        emailSenderService.sendNewFigures(figureRepository.findFigureByCreatedDateAfter(targetTime));
    }

}
