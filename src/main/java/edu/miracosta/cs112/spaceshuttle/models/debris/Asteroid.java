package edu.miracosta.cs112.spaceshuttle.models.debris;

import edu.miracosta.cs112.spaceshuttle.models.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Asteroid implements Drawable {
    static Image junk;

    public Asteroid() {
        if (junk == null) {
            junk = new Image("file:./src/main/resources/images/asteroid.png");
        }
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y, double width, double height) {
        gc.drawImage(junk, x, y, width, height);
    }
}
