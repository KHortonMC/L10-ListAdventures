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

    List<Resource> medicalSupplies = new ArrayList<>();
    List<Resource> foodSupplies = new ArrayList<>();
    List<Resource> partsSupplies = new ArrayList<>();

    Image image;

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
        switch (cargo.getType()) {
            case MEDICAL:
                medicalSupplies.add(cargo);
                break;
                case FOOD:
                    foodSupplies.add(cargo);
                    break;
                    case PARTS:
                        partsSupplies.add(cargo);
                        break;
                        default:
                            break;
        }
    }

    public int getMedicalCount() { return medicalSupplies.size(); }
    public int getFoodCount() { return foodSupplies.size(); }
    public int getPartsCount() { return partsSupplies.size(); }

    public String getResults() {
        String retvalue = "Total Medical Supplies: " + medicalSupplies.size() + "\n";

        for (Resource resource : medicalSupplies) {
            retvalue += resource.getContents() + " ";
        }
        retvalue += "\nTotal Food Supplies: " + foodSupplies.size() + "\n";
        for (Resource resource : foodSupplies) {
            retvalue += resource.getContents() + " ";
        }
        retvalue += "\nTotal Part Supplies: " + partsSupplies.size() + "\n";
        for (Resource resource : partsSupplies) {
            retvalue += resource.getContents() + " ";
        }
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
