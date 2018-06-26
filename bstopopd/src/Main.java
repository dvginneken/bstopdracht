package src;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//De main functie die wat schermen aanroept, te beginnen bij het startscherm.

public class Main extends Application {
    Instellingen instellingen = new Instellingen();
    //Speelscherm speelscherm;
    Optiescherm optiescherm = new Optiescherm(instellingen);
    startScherm startscherm = new startScherm();
    toetsScherm toetsscherm = new toetsScherm();


    public void start(Stage primaryStage) {
        startscherm.genereer.setOnAction(event -> optiescherm.start(primaryStage));
        startscherm.Startspel.setOnAction(event -> toetsscherm.start(primaryStage));
        startscherm.quit.setOnAction(event -> {startscherm.close(primaryStage);});
        optiescherm.naar_beginscherm.setOnAction(event -> startscherm.start(primaryStage));
        optiescherm.afsluiten.setOnAction(event -> optiescherm.close(primaryStage));
        optiescherm.naar_speelscherm.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {
                        try{
                            Speelscherm speelscherm = new Speelscherm(optiescherm.returinst());
                            speelscherm.start(primaryStage);
                            speelscherm.naar_beginscherm.setOnAction(event -> {
                                startscherm.start(primaryStage);
                            });
                            Resultaatscherm resultscreen = speelscherm.returnresultaatscherm();
                            resultscreen.naar_beginscherm.setOnAction(event -> startscherm.start(primaryStage));
                        }catch (NullPointerException f){
                            System.out.println("error");
                        }
                    }
                });
        toetsscherm.naar_beginscherm.setOnAction(event -> {
            startscherm.start(primaryStage);
            toetsscherm.resetwindow();
        });
        toetsscherm.afsluiten.setOnAction(event -> toetsscherm.close(primaryStage));
        startscherm.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}