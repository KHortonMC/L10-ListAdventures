module edu.miracosta.cs112.spaceshuttle {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.miracosta.cs112.spaceshuttle to javafx.fxml;
    exports edu.miracosta.cs112.spaceshuttle;
    exports edu.miracosta.cs112.spaceshuttle.controllers;
    opens edu.miracosta.cs112.spaceshuttle.controllers to javafx.fxml;
    exports edu.miracosta.cs112.spaceshuttle.models;
    opens edu.miracosta.cs112.spaceshuttle.models to javafx.fxml;
}