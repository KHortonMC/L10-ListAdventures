package edu.miracosta.cs112.ListAdventures.models;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class GameObject {
    protected double positionX;
    protected double positionY;
    protected ImageView imageView;

    public Node getNode() { return imageView; }

    void updateImagePosition() {
        if (imageView != null) {
            imageView.setX(positionX);
            imageView.setY(positionY);
        }
    }

    protected GameObject(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public boolean isColliding(GameObject other) {
        if (this == other) { return false; } // no self collision
        return this.imageView.getBoundsInParent().intersects(other.imageView.getBoundsInParent());
    }

    public void update() {
        updateImagePosition();
    }
}
