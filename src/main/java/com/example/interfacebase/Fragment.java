package com.example.interfacebase;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Fragment extends Pane {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public Text quote;

    @FXML
    public Text subject;

    @FXML
    public Text date;

    @FXML
    public Text name;

    @FXML
    void initialize() {

    }



//    public Fragment(String quote, String subject, String date, String name) {
//        this.quote.setText(quote);
//        this.subject.setText(subject);
//        this.date.setText(date);
//        this.name.setText(name);
//    }

}

