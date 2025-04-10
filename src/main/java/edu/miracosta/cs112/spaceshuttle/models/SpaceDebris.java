package edu.miracosta.cs112.spaceshuttle.models;

import edu.miracosta.cs112.spaceshuttle.models.debris.ImageInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.Random;

public class SpaceDebris <T extends ImageInterface> extends GameObject {
    static Random random = new Random();
    private T debris;
    ImageView imageView;
    public SpaceDebris(T debris) {
        super(700, random.nextDouble(400), 25);
        setDebris(debris);
        imageView = new ImageView();
        imageView.setImage(debris.getImage());
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }

    @Override
    public ImageView getImageView() { return imageView; }

    public T getDebris() {
        return debris;
    }

    public void setDebris(T debris) {
        this.debris = debris;
    }

    @Override
    public void update() {
        this.positionX -= 1;
        this.imageView.setX(this.positionX);
        this.imageView.setY(this.positionY);
    }
}
