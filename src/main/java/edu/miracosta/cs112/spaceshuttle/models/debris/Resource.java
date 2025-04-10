package edu.miracosta.cs112.spaceshuttle.models.debris;

import edu.miracosta.cs112.spaceshuttle.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Resource implements ImageInterface {
    public enum Type {
        MEDICAL,
        FOOD,
        PARTS
    }

    static Random random = new Random();

    Image image;
    String contents;
    Type type;

    public Resource(Type type) {
        this.type = type;
        image = new Image(Constants.iconFiles[type.ordinal()]);
        contents = Constants.contentsList[type.ordinal()][random.nextInt(Constants.contentsList[type.ordinal()].length)];
    }

    @Override
    public Image getImage() {
        return image;
    }

    public Type getType() { return type; }
    public String getContents() { return contents; }
}
