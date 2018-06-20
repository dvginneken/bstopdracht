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
    startScherm startscherm = new startScherm();
    Button naar_beginscherm = new Button("Stoppen");
    Button vorige = new Button("Vorige vraag");
    Button volgende = new Button("Volgende vraag");
    Button starten = new Button("Spel starten");
    Label vraag_label = new Label();
    VBox buttons =  new VBox(10);
    int index = 0;

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

        naar_beginscherm.setOnAction(event ->{
            startscherm.start(primaryStage);
        });

        HBox knoppen = new HBox(10, this.naar_beginscherm, this.vorige, this.volgende, this.starten);
        VBox eindbox = new VBox(10, opening);


        String sv = this.instellingen.getSoort_vragen();
        String sa = this.instellingen.getSoort_antwoorden();
        vraag[] vraaglijst = new vraag[instellingen.getHoeveelheid()];

        for (Integer i = 0; i < instellingen.getHoeveelheid(); i++){
            vraaglijst[i] = new vraag(sv, sa);
            vraaglijst[i].generatequestions();
            this.starten.setOnAction(event -> {
                update(vraaglijst[index]);
            });
            this.volgende.setOnAction(event -> {
                next(vraaglijst);
            });
            this.vorige.setOnAction(event -> {
                try{
                    previous(vraaglijst);}
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Kan niet naar vorige.");
                }
            });


        }


        eindbox.getChildren().addAll(knoppen, vraag_label, buttons);

        pane.getChildren().add(eindbox);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    private void update(vraag vraag){
        vraag_label.setText("Vraag " + (index + 1) + ": " + vraag.getVraag());
        buttons.getChildren().clear();
        buttons = buttonbox(vraag);
    }
    private VBox buttonbox(vraag vraag){
        final ToggleGroup optiegroep = new ToggleGroup();
        for (String optie:vraag.getOpties()){
            RadioButton optiebutton = new RadioButton(optie + "\n");
            optiebutton.setToggleGroup(optiegroep);
            buttons.getChildren().add(optiebutton);
        }
        return buttons;
    }
    private void next(vraag[] vraaglijst){
        index += 1;
        update(vraaglijst[index]);
    }
    private void previous(vraag[] vraaglijst){
        index -= 1;
        update(vraaglijst[index]);
    }


}
