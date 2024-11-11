package edu.miracosta.cs112.spaceshuttle.models;

import edu.miracosta.cs112.spaceshuttle.controllers.ShuttleResupplyController;
import edu.miracosta.cs112.spaceshuttle.models.debris.Asteroid;
import edu.miracosta.cs112.spaceshuttle.models.debris.Resource;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.Random;

import static edu.miracosta.cs112.spaceshuttle.models.debris.Resource.Type.*;

public class GameLoop {
    // Timers
    double respawnTimerReset = 10;
    double respawnTimer = 10;
    double distanceTraveled = 0;
    double maxDistance = 2000;
    ShuttleResupplyController controller;
    GraphicsContext gc;

    Random random = new Random();

    // our main vehicle
    SpaceShuttle shuttle;
    public SpaceShuttle getShuttle() { return shuttle; }

    // TODO: Step 1, convert this debris object into an ArrayList!
    GameObject debris;

    public GameLoop(Canvas canvas, ShuttleResupplyController controller) {
        shuttle = new SpaceShuttle();
        this.controller = controller;
        gc = canvas.getGraphicsContext2D();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // clear and set a background color
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.CORNFLOWERBLUE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                handleObjectUpdate();
                handleCollision();
                handleRespawn();

                controller.handleUIUpdate();

                distanceTraveled += 1;
                if (distanceTraveled > maxDistance
                    || shuttle.getHealthPercent() <= 0) {
                    stopGame();
                }
            }

            public void stopGame()  {
                this.stop();
                controller.handleUIResults();
            }
        };
        timer.start();
    }

    public void handleObjectUpdate() {
        shuttle.update();
        shuttle.draw(gc);

        GameObject offscreen = null;
        // TODO: Step 2, add a for loop to update & draw every piece of debris
        if (this.debris != null) {
            this.debris.update();
            this.debris.draw(gc);
            if (this.debris.getPositionX() < -50) {
                offscreen = this.debris;
            }
        }
        if (offscreen != null) {
            // TODO: Step 3, refactor to remove the object from the list
            this.debris = null;
        }
    }

    public void handleCollision() {
        // TODO: Step 4, ensure the ArrayList is the kind GameObject is looking for
        GameObject collision = shuttle.isColliding(this.debris);
        if (collision instanceof SpaceDebris<?> spaceDebris) {
            if (spaceDebris.getDebris() instanceof Asteroid) {
                shuttle.takeDamage(1);
            } else if (spaceDebris.getDebris() instanceof Resource) {
                shuttle.transferCargo((Resource) spaceDebris.getDebris());
                // TODO: Step 5, refactor to remove the object from the list
                this.debris = null;
            }
        }
    }

    public void handleRespawn() {
        respawnTimer -= 0.1;
        if (respawnTimer <= 0) {
            respawnTimerReset -= 0.1;
            respawnTimer = respawnTimerReset;

            // TODO: Step 6, add the newly spawned object to the new array
            if (this.debris == null) {
                this.debris = spawnGameObject();
            }
        }
    }

    public GameObject spawnGameObject() {
        int next = random.nextInt(Resource.Type.values().length + 3);
        return switch (next) {
            case 0 -> new SpaceDebris<>(new Resource(MEDICAL));
            case 1 -> new SpaceDebris<>(new Resource(FOOD));
            case 2 -> new SpaceDebris<>(new Resource(PARTS));
            default -> new SpaceDebris<>(new Asteroid());
        };
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP: shuttle.setDeltaY(-1.25); break;
            case DOWN: shuttle.setDeltaY(1.25); break;
            case LEFT: shuttle.setDeltaX(-1.25); break;
            case RIGHT: shuttle.setDeltaX(1.25); break;
            default: break;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP, DOWN: shuttle.setDeltaY(0); break;
            case LEFT, RIGHT: shuttle.setDeltaX(0); break;
            default: break;
        }
    }
}
