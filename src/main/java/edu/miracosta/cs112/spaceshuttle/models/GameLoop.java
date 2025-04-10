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

    Random random = new Random();

    // our main vehicle
    SpaceShuttle shuttle;
    public SpaceShuttle getShuttle() { return shuttle; }

    // TODO: Step 1, convert this debris object into an ArrayList!
    // HINT: Once this is converted to an ArrayList, you're going to have
    // a lot of compiler errors. Instead of just deleting debris,
    // create a strongly typed ArrayList and follow the TODO steps.
    // When you've reached the last step in this file, delete GameObject debris
    // and cleanup any compiler errors!
    // SUGGESTION: Naming your new list, debrisList will let both the old
    // code and the new code live side by side.
    GameObject debris;

    public void handleRespawn() {
        respawnTimer -= 0.1;
        if (respawnTimer <= 0) {
            respawnTimerReset -= 0.1;
            respawnTimer = respawnTimerReset;

            if (this.debris == null) {
                this.debris = spawnGameObject();
                this.controller.addObject(this.debris);
            }

            // TODO: Step 2, add our newly spawned object to our ArrayList!
            // HINT: Add...
        }
    }

    public void handleDespawn(GameObject object) {
        // TODO: Step 3, refactor to remove this object from our ArrayList!
        // REMINDER: You have to clear class references or the object won't get cleaned up.
        // HINT: We want to be clearing the object out of the ArrayList...
        if (object == this.debris) {
            this.controller.removeObject(this.debris);
            this.debris = null;
        }
    }

    public GameLoop(ShuttleResupplyController controller) {
        shuttle = new SpaceShuttle(controller.getShuttleView());
        this.controller = controller;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
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

        GameObject offscreen = null;
        // TODO: Step 4, add a for loop to update & draw every piece of debris
        // HINT: The for loop can be almost the same as this one reference...
        // if you make a local reference for each item in the list!
        if (this.debris != null) {
            this.debris.update();
            if (this.debris.getPositionX() < -50) {
                offscreen = this.debris;
            }
        }
        if (offscreen != null) {
            handleDespawn(offscreen);
        }
    }

    public void handleCollision() {
        // TODO: Step 5, ensure the ArrayList is the kind GameObject is looking for
        GameObject collision = shuttle.isColliding(this.debris);
        if (collision instanceof SpaceDebris<?> spaceDebris) {
            if (spaceDebris.getDebris() instanceof Asteroid) {
                shuttle.takeDamage(1);
            } else if (spaceDebris.getDebris() instanceof Resource) {
                shuttle.transferCargo((Resource) spaceDebris.getDebris());
                handleDespawn(this.debris);
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
