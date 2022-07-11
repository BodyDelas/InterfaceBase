package com.example.interfacebase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuestTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backeightButton;

    @FXML
    private AnchorPane bittonExit;

    @FXML
    private TableView<Quote> table;

    @FXML
    private TableColumn<Quote, Integer> id;

    @FXML
    private TableColumn<Quote, String> subject;

    @FXML
    private TableColumn<Quote, String> teacher;

    @FXML
    private TableColumn<Quote, String> quote;

    @FXML
    private TableColumn<Quote, String> date;

    @FXML
    private TableColumn<Quote, Integer> user_id;

    ObservableList<Quote> list = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        list.addAll(DatabaseHandler.getAllQuotes());

        id.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("id"));
        subject.setCellValueFactory(new PropertyValueFactory<Quote, String>("subject"));
        teacher.setCellValueFactory(new PropertyValueFactory<Quote, String>("teacher"));
        quote.setCellValueFactory(new PropertyValueFactory<Quote, String>("quote"));
        date.setCellValueFactory(new PropertyValueFactory<Quote, String>("date"));
        user_id.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("user_id"));
        table.setItems(list);

        backeightButton.setOnAction(event -> {
            backeightButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/interfacebase/hello-view.fxml"));

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