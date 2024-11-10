package edu.miracosta.cs112.spaceshuttle.models;

import javafx.scene.canvas.GraphicsContext;

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
    public double getPositionY() { return positionY; }
    public double getRadius() { return radius; }

    public void setPositionX(double positionX) { this.positionX = positionX; }
    public void setPositionY(double positionY) { this.positionY = positionY; }
    public void setRadius(double radius) { this.radius = radius; }

    public abstract void update();
    public abstract void draw(GraphicsContext gc);

    public GameObject isColliding(List<GameObject> gameobjects) {
        for (GameObject other : gameobjects) {
            if (isColliding(other)) {
                return other;
            }
        }
        return null;
    }

    public boolean isColliding(GameObject other) {
        if (this != other && other != null) {
            double distance = Math.pow(this.positionX - other.positionX, 2)
                    + Math.pow(this.positionY - other.positionY, 2);
            double radii = Math.pow(this.radius + other.radius, 2);
            if (distance < radii) {
                return true;
            }
        }
        return false;
    }
}
