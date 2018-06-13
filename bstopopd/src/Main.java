package src;

import javafx.application.Application;
import javafx.stage.Stage;

//De main functie die wat schermen aanroept, te beginnen bij het startscherm.

public class Main extends Application {
    Instellingen instellingen = new Instellingen();
    Optiescherm optiescherm = new Optiescherm();
    startScherm startscherm = new startScherm();
    toetsScherm toetsscherm = new toetsScherm();
    //Speelscherm speelscherm = new Speelscherm();



    public void start(Stage primaryStage) {
        startscherm.genereer.setOnAction(event -> optiescherm.start(primaryStage));
        startscherm.Startspel.setOnAction(event -> toetsscherm.start(primaryStage));
        optiescherm.naar_beginscherm.setOnAction(event -> startscherm.start(primaryStage));
        startscherm.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}