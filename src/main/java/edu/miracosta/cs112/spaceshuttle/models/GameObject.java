package edu.miracosta.cs112.spaceshuttle.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.List;

public abstract class GameObject {
    protected double positionX;
    protected double positionY;
    protected double radius;

    protected GameObject(double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    public double getPositionX() { return positionX; }

    public abstract ImageView getImageView();
    public abstract void update();

    public GameObject isColliding(GameObject other) {
        if (this != other && other != null) {
            double distance = Math.pow(this.positionX - other.positionX, 2)
                    + Math.pow(this.positionY - other.positionY, 2);
            double radii = Math.pow(this.radius + other.radius, 2);
            if (distance < radii) {
                return other;
            }
        }
        return null;
    }
}
