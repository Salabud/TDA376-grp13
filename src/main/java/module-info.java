module com.example.antsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.antsimulator to javafx.fxml;
    exports com.example.antsimulator;
    exports Model;
    opens Model to javafx.fxml;
    exports View;
    opens View to javafx.fxml;
    exports Controller;
    opens Controller to javafx.fxml;
}