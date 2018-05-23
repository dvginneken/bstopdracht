package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class Speelscherm extends Application {
    Instellingen instellingen = new Instellingen();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        primaryStage.setTitle("Speelscherm");

        Label hoi = new Label("Hoi" + instellingen.getNaam());


        //pane.getChildren().addAll(eindbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
