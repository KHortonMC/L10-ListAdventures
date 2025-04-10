package edu.miracosta.cs112.spaceshuttle.models.debris;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Asteroid implements ImageInterface {
    static Image image;

    public Asteroid() {
        if (image == null) {
            image = new Image("file:./src/main/resources/images/asteroid.png");
        }
    }

    @Override
    public Image getImage() {
        return image;
    }
}
