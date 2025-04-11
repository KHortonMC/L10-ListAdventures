package edu.miracosta.cs112.ListAdventures.controllers;

import edu.miracosta.cs112.ListAdventures.models.GameLoop;
import edu.miracosta.cs112.ListAdventures.models.SpaceShuttle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    SpaceShuttle spaceShuttle;
    GameLoop gameLoop;

    public ObservableList<Node>  getObservableList() { return gamePane.getChildren(); }

    public void initialize() {
        // Even though both #handleKeyPressed
        // and #handleKeyReleased are defined
        // in the ListAdventures.fxml
        // These two lines of code are vital for the shuttleView
        // to be able to gather 'focus' and receive key pressed
        // and released events.
        shuttleView.setFocusTraversable(true);
        shuttleView.requestFocus();
        spaceShuttle = new SpaceShuttle(shuttleView);
        gameLoop = new GameLoop(this, spaceShuttle);
    }

    public void handleUIUpdate() {
        healthProgress.setProgress(spaceShuttle.getHealthPercent());
        medicalLabel.setText("" + spaceShuttle.getMedicalCount());
        foodLabel.setText("" + spaceShuttle.getFoodCount());
        partsLabel.setText("" + spaceShuttle.getPartsCount());
        distanceProgress.setProgress(spaceShuttle.getDistancePercent());
    }

    public void handleUIResults() {
        String results = spaceShuttle.getCargoManifest();
        gameResults.setVisible(true);
        resultsLabel.setText(results);
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP: spaceShuttle.setDeltaY(-1.25); break;
            case DOWN: spaceShuttle.setDeltaY(1.25); break;
            case LEFT: spaceShuttle.setDeltaX(-1.25); break;
            case RIGHT: spaceShuttle.setDeltaX(1.25); break;
            default: break;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case UP, DOWN: spaceShuttle.setDeltaY(0); break;
            case LEFT, RIGHT: spaceShuttle.setDeltaX(0); break;
            default: break;
        }
    }
}