module com.example.antsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;
    exports app;
    exports Model.Tasks;
    exports Model;
    opens Model to javafx.fxml;
    exports View;
    opens View to javafx.fxml;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Model.Ants;
    opens Model.Ants to javafx.fxml;
    exports Model.Colony;
    opens Model.Colony to javafx.fxml;
    exports Model.Colony.AntNest;
    opens Model.Colony.AntNest to javafx.fxml;
    exports Model.Ants.Behavior;
    opens Model.Ants.Behavior to javafx.fxml;
    exports Model.World;
    opens Model.World to javafx.fxml;
    exports Model.Ants.State;
    opens Model.Ants.State to javafx.fxml;
    exports Model.Ants.Movement;
    opens Model.Ants.Movement to javafx.fxml;
    exports Model.Ants.Status;
    opens Model.Ants.Status to javafx.fxml;
    exports Model.Datastructures;
    opens Model.Datastructures to javafx.fxml;
}