module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.json;

    opens com.example to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.example;
}