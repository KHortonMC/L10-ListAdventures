package edu.miracosta.cs112.spaceshuttle.models;

import edu.miracosta.cs112.spaceshuttle.models.debris.Resource;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class SpaceShuttle extends GameObject {
    double deltaX;
    double deltaY;
    double drawWidth;
    double drawHeight;
    double health;
    double maxHealth = 100;
    Image image;

    // TODO: Step 9, add ArrayLists for medicalSupplies, foodSupplies, and partsSupplies

    public SpaceShuttle() {
        super(100, 200, 25);
        image = new Image("file:./src/main/resources/images/SpaceShuttle.png");
        drawWidth = 100;
        drawHeight = 50;
        health = maxHealth;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }
    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public void update() {
        this.positionX += deltaX;
        this.positionY += deltaY;
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

    public void draw(GraphicsContext gc) {
        gc.drawImage(image,
                positionX-drawWidth/2,
                positionY-drawHeight/2,
                drawWidth,
                drawHeight);
    }
}
