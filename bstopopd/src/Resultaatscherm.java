package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Resultaatscherm extends Application {
    Instellingen instellingen;

    public static void main(String[] args) {
        launch(args);
    }

    public Resultaatscherm(Instellingen instellingen){this.instellingen = instellingen;}

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Resultaatscherm");
        Text opening = new Text("Hoi " + this.instellingen.getNaam());
        opening.setFont(Font.font("open-sans", 25));

        Pane pane = new Pane();
        Scene scene = new Scene(pane,300,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
