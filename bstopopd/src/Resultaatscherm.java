package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Resultaatscherm extends Application {
    Instellingen instellingen;
    startScherm startscherm = new startScherm();
    Button naar_beginscherm = new Button("Naar beginscherm");
    Button afsluiten = new Button("Afsluiten");

    public static void main(String[] args) {
        launch(args);
    }

    public Resultaatscherm(Instellingen instellingen){this.instellingen = instellingen;}

    @Override
    public void start(Stage primaryStage) {
        VBox eindbox = new VBox(10);
        Text opening = new Text("Hoi " + this.instellingen.getNaam());
        opening.setFont(Font.font("open-sans", 25));

        Text resultaat = new Text("aantal goede" + "/" + this.instellingen.getHoeveelheid());
        resultaat.setFont(Font.font("open-sans", 32));

        this.naar_beginscherm.setOnAction(event -> {
            startscherm.start(primaryStage);
        });

        this.afsluiten.setOnAction(event -> primaryStage.close());


        eindbox.getChildren().addAll(opening, resultaat, naar_beginscherm, afsluiten);
        Pane pane = new Pane();
        pane.getChildren().add(eindbox);
        Scene scene = new Scene(pane,1300,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
