package com.example.principal;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaLogin extends Application {

    @FXML
    private AnchorPane apPrincipal;

    @FXML
    private Button btn;

    @FXML
    void clickBtn(ActionEvent event) {

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(TelaLogin.class.getResource("telalogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
    }

    public static void main(String[] args){
        launch();
    }
}