package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;



public class Speelscherm extends Application {
    Instellingen instellingen;
    Button naar_beginscherm = new Button("Stoppen");
    Button vorige = new Button("Vorige vraag");
    Button volgende = new Button("Volgende vraag");
    Button starten = new Button("Spel starten");

    public static void main(String[] args) {
        launch(args);
    }


    public Speelscherm(Instellingen instellingen) {
        this.instellingen = instellingen;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        primaryStage.setTitle("Speelscherm");


        Text opening = new Text("Hoi " + this.instellingen.getNaam());
        opening.setFont(Font.font("open-sans", 25));


        HBox knoppen = new HBox(10, this.naar_beginscherm, this.vorige, this.volgende, this.starten);
        VBox eindbox = new VBox(10, opening);


        String sv = this.instellingen.getSoort_vragen();
        String sa = this.instellingen.getSoort_antwoorden();

        vraag question = new vraag(sv, sa);
        question.generatequestions();
        this.starten.setOnAction(event -> {
            String getvraag = question.getVraag();
            Label vraag_label = new Label(getvraag);
            eindbox.getChildren().add(vraag_label);
            String getopties[] = question.getOpties();
            final ToggleGroup optiegroep = new ToggleGroup();
            for (String optie:getopties){
                RadioButton optiebutton = new RadioButton(optie + "\n");
                optiebutton.setToggleGroup(optiegroep);
                eindbox.getChildren().add(optiebutton);
            }

        });

        //Label tekst = new Label("gegevens:" + this.instellingen.getHoeveelheid() + this.instellingen.getSeconden() +
        // this.instellingen.getSoort_antwoorden() + this.instellingen.getSoort_vragen() + this.instellingen.getTijd());

        eindbox.getChildren().add(knoppen);

        pane.getChildren().add(eindbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }




}
