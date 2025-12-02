module com.example.antsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;
    requires org.json;
    exports app;
    exports model.tasks;
    exports model;
    opens model to javafx.fxml;
    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
    exports model.ants;
    opens model.ants to javafx.fxml;
    exports model.colony;
    opens model.colony to javafx.fxml;
    exports model.colony.antnest;
    opens model.colony.antnest to javafx.fxml;
    exports model.ants.behavior;
    opens model.ants.behavior to javafx.fxml;
    exports model.world;
    opens model.world to javafx.fxml;
    exports model.ants.state;
    opens model.ants.state to javafx.fxml;
    exports model.ants.movement;
    opens model.ants.movement to javafx.fxml;
    exports model.ants.status;
    opens model.ants.status to javafx.fxml;
    exports model.datastructures;
    opens model.datastructures to javafx.fxml;
}