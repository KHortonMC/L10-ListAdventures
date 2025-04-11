package edu.miracosta.cs112.ListAdventures.models;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Random;

public class SpaceDebris extends GameObject {
    static Random random = new Random();
    public enum Type { ASTEROID, MEDICAL, FOOD, PARTS }

    final String[] types = {
            "Asteroid",
            "Medical",
            "Food",
            "Parts" };

    final String[] icons = {
            "file:./src/main/resources/images/asteroid.png",
            "file:./src/main/resources/images/medical.png",
            "file:./src/main/resources/images/food.png",
            "file:./src/main/resources/images/parts.png"
    };

    Type type;
    public String getType() { return types[this.type.ordinal()]; }

    public SpaceDebris(Type type) {
        super();
        this.type = type;
        Image image = new Image(icons[this.type.ordinal()]);
        imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        setPosition(700, random.nextDouble(400));
    }

    @Override
    public void update() {
        final double speed = 1;
        double x = imageView.getX();
        double y = imageView.getY() - speed;
        setPosition(x, y);
    }
}
