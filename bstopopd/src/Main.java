package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    Optiescherm optiescherm = new Optiescherm();
    startScherm startscherm = new startScherm();

    public void start(Stage primaryStage) {
        startscherm.genereer.setOnAction(event -> optiescherm.start(primaryStage));
        optiescherm.beginscherm.setOnAction(event -> startscherm.start(primaryStage));
        startscherm.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}