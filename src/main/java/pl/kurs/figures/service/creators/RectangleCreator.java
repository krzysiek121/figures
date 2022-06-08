package pl.kurs.figures.service.creators;

import org.springframework.stereotype.Service;
import pl.kurs.figures.exceptions.ParameterNotFoundException;
import pl.kurs.figures.interfaces.FigureCreator;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Figure;
import pl.kurs.figures.model.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Service
public class RectangleCreator implements FigureCreator {
    @Override
    public String type() {
        return "rectangle";
    }

    @Override
    public Figure createFigure(Map<String, Double> parameters) {

        if (!parameters.keySet().containsAll(Arrays.asList("width", "height"))) {

            throw new ParameterNotFoundException("INVALID_PARAMETERS");
        }
        return new Rectangle(getDouble("width", parameters), getDouble("height", parameters));
    }

    @Override
    public File drawFigure(Figure figure) throws IllegalAccessException, IOException {

        if(figure.getArea() > 1600) {
            throw new IllegalArgumentException();
        }

        BufferedImage b1 = new BufferedImage(400, 400,BufferedImage.TYPE_3BYTE_BGR );
        Graphics2D g2;

        g2 = b1.createGraphics();
        g2.setColor(Color.blue);

        int x = figure.getParameters().get("width").intValue();
        int y = figure.getParameters().get("height").intValue();



        g2.drawRect(200 - (x/2),200 - (y/2), x, y);
        g2.setColor(Color.blue);
        g2.setBackground(Color.blue);
        g2.dispose();

        File file = new File("image.jpg");
        ImageIO.write(b1, "jpg", file);



        return file;
    }
}
