package edu.miracosta.cs112.spaceshuttle.models;

import edu.miracosta.cs112.spaceshuttle.controllers.ShuttleResupplyController;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.Random;

public class GameLoop {
    Random random = new Random();
    ShuttleResupplyController controller;

    final double respawnReset = 100;
    double respawnTimer = respawnReset;

    List<GameObject> gameObjects = new ArrayList<>();

    public GameLoop(ShuttleResupplyController controller, SpaceShuttle shuttle) {
        this.controller = controller;

        // todo: 1) add the shuttle to our update list

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleObjectUpdate();   // move all the objects
                handleCollision();      // deal with collisions
                handleDebrisSpawn();    // make new debris
                controller.handleUIUpdate();
                // the below shuttle is a reference to our argument
                // and it gets preserved by the anonymous class!
                if (shuttle.isGameOver()) {
                    // the following 'this' refers the to timer
                    // because 'this' is an anonymous inner class
                    // technically we're inheriting/extending timer!
                    this.stop();
                    controller.handleUIResults();
                }
            }
        };
        timer.start();
    }

    public void handleObjectUpdate() {
        // todo: 2) iterate through our update list and call update!
        // tip: if you did this correctly, you'll be able to pilot the shuttle
    }

    public void handleDebrisSpawn() {
        respawnTimer -= 0.1;
        if (respawnTimer <= 0) {
            respawnTimer = respawnReset;

            int i = random.nextInt(SpaceDebris.Type.values().length);
            SpaceDebris object = new SpaceDebris(SpaceDebris.Type.values()[i]);

            // todo: 3) add the object to our gameObjects for updating
            // todo: 4) add the object's Node to our controller (for free drawing)
            // tip: GameObject has a method to return the Node our controller wants!
        }
    }

    public void handleDebrisDespawn(GameObject gameObject) {
        // todo: 5) we need to remove the gameobject from our update list
        // todo: 6) and we need to remove the object's Node from our controller
    }

    public void handleCollision() {
        // todo: 7) check for collisions between all the objects
        // asteroids trigger shuttle.takeDamage
        // resources trigger shuttle.transferCargo
    }
}
