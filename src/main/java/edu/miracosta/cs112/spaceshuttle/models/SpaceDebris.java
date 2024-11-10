package edu.miracosta.cs112.spaceshuttle.models;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class SpaceDebris <T extends Drawable> extends GameObject {
    static Random random = new Random();
    private T debris;
    public SpaceDebris(T debris) {
        super(700, random.nextDouble(400), 25);
        this.debris = debris;
    }

    public T getDebris() {
        return debris;
    }

    public void setDebris(T debris) {
        this.debris = debris;
    }

    @Override
    public void update() {
        this.positionX -= 1;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (debris != null) {
            debris.draw(gc,
                    this.positionX-this.radius,
                    this.positionY-this.radius,
                    this.radius*2,
                    this.radius*2);
        }
    }
}
