package edu.miracosta.cs112.spaceshuttle.controllers;

import edu.miracosta.cs112.spaceshuttle.models.GameLoop;
import edu.miracosta.cs112.spaceshuttle.models.GameObject;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class ShuttleResupplyController {
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
    @FXML
    Pane gameResults;
    @FXML
    ImageView shuttleView;
    @FXML
    Pane gamePane;

    public ImageView getShuttleView() { return shuttleView; }

    /**
     * Adds an image view
     * @param gameObject a valid gameObject with an imageView
     */
    public void addObject(GameObject gameObject) {
        ImageView view = gameObject.getImageView();
        if (view != null) {
            gamePane.getChildren().add(view);
        }
    }

    /**
     * Removes an imageView from JavaFX layout
     * @param gameObject
     */
    public void removeObject(GameObject gameObject) {
        ImageView view = gameObject.getImageView();
        if (view != null) {
            gamePane.getChildren().remove(view);
        }
    }

    /**
     * Main Game Loop. Runs the logic of the game
     */
    GameLoop gameLoop;

    public void initialize() {
        gameLoop = new GameLoop(this);

        // Even though both #handleKeyPressed
        // and #handleKeyReleased are defined
        // in the shuttleResupply-view.fxml
        // These two lines of code are vital for the canvas
        // to be able to gather 'focus' and receive key pressed
        // and released events.
        shuttleView.setFocusTraversable(true);
        shuttleView.requestFocus();
    }

    public void handleKeyPressed(KeyEvent event) {
        gameLoop.handleKeyPressed(event);
    }

    public void handleKeyReleased(KeyEvent event) {
        gameLoop.handleKeyReleased(event);
    }

    public void handleUIUpdate() {
        // TODO: Step 7, update the UI elements with data from the shuttle class
        // healthProgress
        // medicalLabel
        // foodLabel
        // partsLabel
        // distanceProgress
    }

    public void handleUIResults() {
        // TODO: Step 8, set our results label to visible and fill it with data from the shuttle
        gameResults.setVisible(true);
        // resultsLabel
        // HINT: you need to add the children!
    }
}