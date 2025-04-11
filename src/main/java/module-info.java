module edu.miracosta.cs112.ListAdventures {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.miracosta.cs112.ListAdventures to javafx.fxml;
    exports edu.miracosta.cs112.ListAdventures;
    exports edu.miracosta.cs112.ListAdventures.controllers;
    opens edu.miracosta.cs112.ListAdventures.controllers to javafx.fxml;
    exports edu.miracosta.cs112.ListAdventures.models;
    opens edu.miracosta.cs112.ListAdventures.models to javafx.fxml;
}