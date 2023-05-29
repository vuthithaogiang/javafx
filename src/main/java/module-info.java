module java2.asignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens java2.asignment to javafx.fxml;
    exports java2.asignment;
}