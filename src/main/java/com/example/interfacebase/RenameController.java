package com.example.interfacebase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RenameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changeButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    private TextField new_login;

    @FXML
    private PasswordField new_password;

    @FXML
    private TextField old_login;

    @FXML
    private PasswordField old_password;

    @FXML
    void initialize() {
        mainMenuButton.setOnAction(event -> {// для нажимания на кнопку зарегестрироваться
            mainMenuButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/app.fxml"));

            try{
                loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        changeButton.setOnAction(event -> {

            DatabaseHandler.updateUserData(
                    old_login.getText(),
                    old_password.getText(),
                    new_login.getText(),
                    new_password.getText()
            );

            changeButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/app.fxml"));

            try{
                loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

}
