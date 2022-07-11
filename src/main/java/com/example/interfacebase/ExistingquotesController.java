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

public class ExistingquotesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backsevenButton;

    @FXML
    private AnchorPane bittonExit;

    @FXML
    private Button changedataQuote;

    @FXML
    private TextField data;

    @FXML
    private TextArea quote_area;

    @FXML
    private TextField subject;

    @FXML
    private TextField teacher;

    @FXML
    void initialize() {
        backsevenButton.setOnAction(event -> {
            backsevenButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/changeswithquotes.fxml"));

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

        quote_area.setText(Current.quote.getQuote());
        subject.setText(Current.quote.getSubject());
        teacher.setText(Current.quote.getTeacher());
        data.setText(Current.quote.getDate());

        changedataQuote.setOnAction(event -> {
            System.out.println(quote_area.getText());
            DatabaseHandler.updateQuote(
                    Current.quote.getId(),
                    quote_area.getText(),
                    subject.getText(),
                    teacher.getText(),
                    data.getText()
            );

            changedataQuote.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/changeswithquotes.fxml"));

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

}