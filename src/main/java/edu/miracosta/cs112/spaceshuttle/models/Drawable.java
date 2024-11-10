package edu.miracosta.cs112.spaceshuttle.models;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
    public void draw(GraphicsContext gc, double x, double y, double width, double height);
}
