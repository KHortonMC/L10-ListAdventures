package edu.miracosta.cs112.spaceshuttle.models;

import javafx.scene.image.ImageView;

public class SpaceShuttle extends GameObject {
    double deltaX;
    double deltaY;
    double health;
    double maxHealth = 2000;
    double distanceTraveled = 0;
    double maxDistance = 2000;

    // todo: 8) add ArrayLists for medical, food, and parts

    public SpaceShuttle(ImageView shuttleImageView) {
        super(100, 200);
        this.imageView = shuttleImageView;
        health = maxHealth;
    }

    public void takeDamage(double amount) {
        this.health -= amount;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }
    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public double getHealthPercent() {
        return health / maxHealth;
    }
    public double getDistancePercent() { return distanceTraveled / maxDistance; }

    @Override
    public void update() {
        this.positionX += deltaX;
        this.positionY += deltaY;
        distanceTraveled += 1;
        super.update();
    }

    public boolean isGameOver() {
        return distanceTraveled > maxDistance || health <= 0;
    }

    public void transferCargo(String cargo) {
        // todo: 9) insert the cargo into the correct array
    }

    // todo: 10) return the correct cargo counts
    public int getMedicalCount() { return 0; }
    public int getFoodCount() { return 0; }
    public int getPartsCount() { return 0; }

    public String getCargoManifest() {
        // todo: 11) nicely format your cargo into a single string for the UI
        return "Space Shuttle Results are the last step!";
    }
}
