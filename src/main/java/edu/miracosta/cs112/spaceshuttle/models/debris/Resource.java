package edu.miracosta.cs112.spaceshuttle.models.debris;

import edu.miracosta.cs112.spaceshuttle.Constants;
import edu.miracosta.cs112.spaceshuttle.models.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Resource implements Drawable {
    public enum Type {
        MEDICAL,
        FOOD,
        PARTS
    }

    static Random random = new Random();

    Image icon;
    String contents;
    Type type;

    public Resource(Type type) {
        this.type = type;
        icon = new Image(Constants.iconFiles[type.ordinal()]);
        contents = Constants.contentsList[type.ordinal()][random.nextInt(Constants.contentsList[type.ordinal()].length)];
    }

    public Type getType() { return type; }
    public String getContents() { return contents; }

    @Override
    public void draw(GraphicsContext gc, double x, double y, double width, double height) {
        gc.drawImage(icon, x, y, width, height);
    }
}
