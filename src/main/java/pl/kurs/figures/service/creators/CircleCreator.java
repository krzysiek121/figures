package pl.kurs.figures.service.creators;

import org.springframework.stereotype.Service;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.exceptions.TooLargeFigureException;
import pl.kurs.figures.interfaces.FigureCreator;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Service
public class CircleCreator implements FigureCreator {
    @Override
    public String type() {
        return "circle";
    }

    @Override
    public Figure createFigure(Map<String, Double> parameters) throws ParameterNotFoundException {

        if (!parameters.containsKey("radius")) {
            throw new ParameterNotFoundException("INVALID_PARAMETERS");
        }

        return new Circle(getDouble("radius", parameters));
    }

    @Override
    public File drawFigure(Figure figure) throws IOException {
        if (figure.getParameters().get("radius") > 200) {
            throw new TooLargeFigureException(Collections.singletonList("radius"));
        }

        BufferedImage b1 = new BufferedImage(400, 400, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2= b1.createGraphics();
        g2.setColor(Color.blue);

        int x = figure.getParameters().get("radius").intValue();

        g2.drawOval(200 - x, 200 - x, x * 2, x * 2);
        g2.setColor(Color.blue);
        g2.setBackground(Color.blue);
        g2.dispose();

        File file = new File("image.jpg");
        ImageIO.write(b1, "jpg", file);


        return file;
    }
}
