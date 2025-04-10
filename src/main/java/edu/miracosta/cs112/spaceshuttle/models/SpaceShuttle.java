package edu.miracosta.cs112.spaceshuttle.models;

import edu.miracosta.cs112.spaceshuttle.models.debris.Resource;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class SpaceShuttle extends GameObject {
    double deltaX;
    double deltaY;
    double health;
    double maxHealth = 100;
    ImageView shuttleView;

    // TODO: Step 9, add ArrayLists for medicalSupplies, foodSupplies, and partsSupplies

    public SpaceShuttle(ImageView image) {
        super(100, 200, 25);
        this.shuttleView = image;
        health = maxHealth;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }
    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    @Override
    public ImageView getImageView() { return this.shuttleView; }

    public void update() {
        this.positionX += deltaX;
        this.positionY += deltaY;
        this.shuttleView.setX(this.positionX);
        this.shuttleView.setY(this.positionY);
    }

    public void takeDamage(double amount) {
        this.health -= amount;
    }
    public double getHealthPercent() {
        return health / maxHealth;
    }

    public void transferCargo(Resource cargo) {
        // TODO: Step 10, add cargo to the correct list
        switch (cargo.getType()) {
            case MEDICAL:
                break;
            case FOOD:
                break;
            case PARTS:
                break;
            default:
                break;
        }
    }

    // TODO: Step 11, report the size of the cargo lists
    public int getMedicalCount() { return 0; }
    public int getFoodCount() { return 0; }
    public int getPartsCount() { return 0; }

    public String getResults() {
        String retvalue = "Space Shuttle Results TODO Step 12";
        // TODO: Step 12, parse medicalSupplies, foodSupplies, and partsSupplies to build an output statement
        return retvalue;
    }
}
