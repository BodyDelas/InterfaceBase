package com.example.interfacebase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane bittonExit;

    @FXML
    private Button changingDataBatton;

    @FXML
    private Button exitButton;

    @FXML
    private Button quoteEditorButton;

    @FXML
    private Button tableBatton;

    @FXML
    void initialize() {
        quoteEditorButton.setOnAction(event -> {// для нажимания на кнопку зарегестрироваться
            quoteEditorButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/quotes.fxml"));

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

        tableBatton.setOnAction(event -> {// для нажимания на кнопку зарегестрироваться
            tableBatton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/table.fxml"));

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

        exitButton.setOnAction(event -> {// для нажимания на кнопку зарегестрироваться
            exitButton.getScene().getWindow().hide();
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

        changingDataBatton.setOnAction(event -> {// для нажимания на кнопку зарегестрироваться
            changingDataBatton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/rename.fxml"));

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