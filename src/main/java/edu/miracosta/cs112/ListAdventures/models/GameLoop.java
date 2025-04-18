package edu.miracosta.cs112.ListAdventures.models;

import edu.miracosta.cs112.ListAdventures.controllers.ShuttleResupplyController;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoop {
    Random random = new Random();
    ShuttleResupplyController controller;

    final double respawnReset = 10;
    double respawnTimer = respawnReset;

    List<GameObject> updateList = new ArrayList<>();

    public GameLoop(ShuttleResupplyController controller, SpaceShuttle shuttle) {
        this.controller = controller;

        // todo: 1) add the shuttle to our updateList
        // (if the shuttle never gets updated, our keyboard inputs will never be realized)

        // this is an example of an Anonymous InnerClass
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleObjectUpdate();   // move all the objects
                handleCollision();      // deal with collisions
                handleDebrisSpawn();    // make new debris
                controller.handleUIUpdate();
                // the below shuttle is a reference to our argument,
                // it gets preserved by the anonymous class!
                if (shuttle.isGameOver()) {
                    // the following 'this' refers to the timer
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
            // todo: 4) add the object's Node to our controller (so we can see it!)
            // (we could skip this step for the space shuttle because it was already added)
            // tip: GameObject.getNode returns a Node...
            // we can add that node to controller.getObservableList()
        }
    }

    public void handleDebrisDespawn(GameObject gameObject) {
        // todo: 5) we need to remove the gameObject from our update list
        // todo: 6) and we need to remove the object's Node from our controller
    }

    public void handleCollision() {
        // todo: 7) check for collisions between all of the objects
        // there's a lot that needs to happen to collide all the objects
        // here's an outline to get you started:
        // You need to check all the objects against all the other objects
        // This will require nested for loops -- outer loop + inner loop
        // use gameObject.isColliding to check for collisions
        // if you do have a collision, you need to determine what the objects are
        // hint: instanceof is making a return! And (type casting)
        // Asteroids should trigger the SpaceShuttle's takeDamage
        // Resources should trigger the SpaceShuttle's transferCargo
        // be sure to despawn the Debris when you're done with it
    }
}
