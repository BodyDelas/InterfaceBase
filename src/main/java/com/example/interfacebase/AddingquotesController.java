package com.example.interfacebase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddingquotesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backfourButton;

    @FXML
    private AnchorPane bittonExit;

    @FXML
    private TextField data;

    @FXML
    private Button pushoneQuote;

    @FXML
    private TextArea quote_area;

    @FXML
    private TextField subject;

    @FXML
    private TextField teacher;

    @FXML
    void initialize() {
        backfourButton.setOnAction(event -> {
            backfourButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/changeswithquotes.fxml"));

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

        pushoneQuote.setOnAction(event -> {

            DatabaseHandler.addQuote(
                    quote_area.getText(),
                    subject.getText(),
                    teacher.getText(),
                    data.getText(),
                    Current.user.getId()
            );

            pushoneQuote.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/changeswithquotes.fxml"));

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