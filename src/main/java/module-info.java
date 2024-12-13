module de.lubowiecki.fxfirststeps {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.lubowiecki.fxfirststeps to javafx.fxml;
    exports de.lubowiecki.fxfirststeps;
}