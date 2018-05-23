package src;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    //Instellingen instellingen = new Instellingen();
    Optiescherm optiescherm = new Optiescherm();
    startScherm startscherm = new startScherm();
    toetsScherm toetsscherm = new toetsScherm();
    //Speelscherm speelscherm = new Speelscherm();

    public void start(Stage primaryStage) {
        startscherm.genereer.setOnAction(event -> optiescherm.start(primaryStage));
        startscherm.Startspel.setOnAction(event -> toetsscherm.start(primaryStage));
        optiescherm.naar_beginscherm.setOnAction(event -> startscherm.start(primaryStage));
//        optiescherm.naar_speelscherm.setOnAction(event -> {
//            if ((optiescherm.tekst.getText() != null && !optiescherm.tekst.getText().isEmpty())){
//                instellingen.setNaam(optiescherm.tekst.getText());
//                speelscherm.start(primaryStage);
//            }else{
//                optiescherm.label_event.setText("Je moet je naam invullen.");
//            }
//        });
        startscherm.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}