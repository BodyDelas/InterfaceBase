package com.example.interfacebase;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import Animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private Button guestButton;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {

        authSignButton.setOnAction(event ->{
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else
                System.out.println("Login and password is empty");
        });

        loginSignUpButton.setOnAction(event -> {
            loginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/signUp.fxml"));

            try{
                loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
                });

        guestButton.setOnAction(event -> {
            guestButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/guestTable.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        System.out.println(">> "+loginText+", "+loginPassword);
        ResultSet result = DatabaseHandler.getUser(user);

        boolean check = false;
        try {
            result.next();
            Current.user = new User(
                    result.getInt("idusers"),
                    result.getString("firstname"),
                    result.getString("lastname"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getInt("group"),
                    result.getInt("type")
            );
            check = true;
        } catch (Exception e) {}

        if(check)
            System.out.println("Success");
        else {
            Shake userLoginAnim = new Shake(login_field);
            Shake userPassAnim = new Shake(password_field);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }

        if(check){
            loginSignUpButton.getScene().getWindow().hide();

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
            //stage.showAndWait();
            stage.show();
        }

    }

}
