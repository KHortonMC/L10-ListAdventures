package edu.miracosta.cs112.spaceshuttle.controllers;

import edu.miracosta.cs112.spaceshuttle.models.GameObject;
import edu.miracosta.cs112.spaceshuttle.models.SpaceDebris;
import edu.miracosta.cs112.spaceshuttle.models.SpaceShuttle;
import edu.miracosta.cs112.spaceshuttle.models.debris.Asteroid;
import edu.miracosta.cs112.spaceshuttle.models.debris.Resource;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static edu.miracosta.cs112.spaceshuttle.models.debris.Resource.Type.*;

public class ShuttleResupplyController {
    @FXML
    private Canvas canvas;
    GraphicsContext gc;

    @FXML
    Label medicalLabel;
    @FXML
    Label foodLabel;
    @FXML
    Label partsLabel;
    @FXML
    ProgressBar healthProgress;
    @FXML
    ProgressBar distanceProgress;
    @FXML
    Label resultsLabel;

    Random random = new Random();

    SpaceShuttle shuttle;
    ArrayList<GameObject> debrisList  = new ArrayList<>();

    double respawnTimerReset = 10;
    double respawnTimer = 10;
    double distanceTraveled = 0;
    double maxDistance = 2000;

    public void initialize() {
        canvas.setFocusTraversable(true);
        canvas.requestFocus(); // required to get the event
        canvas.setOnKeyPressed(event -> handleKeyPressed(event));

        gc = canvas.getGraphicsContext2D();
        shuttle = new SpaceShuttle();

        debrisList.add(new SpaceDebris<>(new Asteroid()));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // clear and set a background color
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.CORNFLOWERBLUE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                handleObjectUpdate();
                handleUIUpdate();
                handleCollision();
                handleRespawn();

                distanceTraveled += 1;
                if (distanceTraveled > maxDistance) {
                    stopGame();
                }
            }

            public void stopGame()  {
                this.stop();
                resultsLabel.setVisible(true);
                resultsLabel.setText(shuttle.getResults());
            }
        };
        timer.start();
    }

    public void handleObjectUpdate() {
        shuttle.update();
        shuttle.draw(gc);

        GameObject offscreen = null;
        for (GameObject debris : debrisList) {
            debris.update();
            debris.draw(gc);
            if (debris.getPositionX() < -50) {
                offscreen = debris;
            }
        }
        if (offscreen != null) {
            debrisList.remove(offscreen);
        }
    }

    public void handleUIUpdate() {
        healthProgress.setProgress(shuttle.getHealthPercent());
        medicalLabel.setText(""+shuttle.getMedicalCount());
        foodLabel.setText(""+shuttle.getFoodCount());
        partsLabel.setText(""+shuttle.getPartsCount());
        distanceProgress.setProgress(distanceTraveled/maxDistance);
    }

    public void handleCollision() {
        GameObject debris = shuttle.isColliding(debrisList);
        if (debris instanceof SpaceDebris) {
            SpaceDebris<?> spaceDebris = (SpaceDebris<?>) debris;
            if (spaceDebris.getDebris() instanceof Asteroid) {
                shuttle.takeDamage(1);
            } else if (spaceDebris.getDebris() instanceof Resource) {
                shuttle.transferCargo((Resource) spaceDebris.getDebris());
                debrisList.remove(debris);
            }
        }
    }

    public void handleRespawn() {
        respawnTimer -= 0.1;
        if (respawnTimer <= 0) {
            respawnTimerReset -= 0.1;
            respawnTimer = respawnTimerReset;
            int next = random.nextInt(Resource.Type.values().length + 3);
            switch (next) {
                case 0:
                    debrisList.add(new SpaceDebris<>(new Resource(MEDICAL)));
                    break;
                case 1:
                    debrisList.add(new SpaceDebris<>(new Resource(FOOD)));
                    break;
                case 2:
                    debrisList.add(new SpaceDebris<>(new Resource(PARTS)));
                    break;
                default:
                    debrisList.add(new SpaceDebris<>(new Asteroid()));
                    break;
            }
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP: shuttle.setDeltaY(-1.25); break;
            case DOWN: shuttle.setDeltaY(1.25); break;
            case LEFT: shuttle.setDeltaX(-1.25); break;
            case RIGHT: shuttle.setDeltaX(1.25); break;
            default: break;
        }
    }

    @FXML
    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP: shuttle.setDeltaY(0); break;
            case DOWN: shuttle.setDeltaY(0); break;
            case LEFT: shuttle.setDeltaX(0); break;
            case RIGHT: shuttle.setDeltaX(0); break;
            default: break;
        }
    }
}