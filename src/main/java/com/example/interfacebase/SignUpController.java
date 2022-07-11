package com.example.interfacebase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField signUoLastName;

    @FXML
    private Button signUpButton;

    @FXML
    private ChoiceBox<String> signUpGroup;

    @FXML
    private TextField signUpName;

    @FXML
    private Button exitthreeButton;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event ->{
            boolean success = signUpNewUser();
            if(!login_field.getText().equals("") && success){
                signUpButton.getScene().getWindow().hide();

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
            }
        });

        exitthreeButton.setOnAction(event -> {// для нажимания на кнопку зарегестрироваться
            exitthreeButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/hello-view.fxml"));

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

        ArrayList<String> groupNums = DatabaseHandler.getAllGroups();
        System.out.println(groupNums);

        signUpGroup.getItems().addAll(groupNums);
    }

    private boolean signUpNewUser() {
        String firstName = signUpName.getText();
        String lastName = signUoLastName.getText();
        String userName = login_field.getText();
        String password = password_field.getText();
        String group = signUpGroup.getSelectionModel().getSelectedItem();

        try {
            DatabaseHandler.signUpUser(firstName, lastName, userName,
                    password, group);
            Current.user = DatabaseHandler.getUser(userName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}