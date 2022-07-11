module com.example.interfacebase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.interfacebase to javafx.fxml;
    exports com.example.interfacebase;
}