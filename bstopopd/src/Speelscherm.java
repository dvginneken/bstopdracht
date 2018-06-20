package src;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Duration;


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

    //private Integer aantal;
    private String vraag;
    private String[] opties;
    private String antwoord;
    private amminozuur[] aminozuren = lijst_aminozuur();
    private Integer hoeveelaminozuur;
    private String typeanswer = "r";
    private String typequestion = "r";

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

    private void doTime(){
        // Timeline, is een animatie van keyframes, wordt aangeroepen  (de frames worden stapgewijs bijgehouden)
        // https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html
        Timeline time = new Timeline();
        // de setCycleCount zorgt voor het aantal cycli. INDEFINITE zorgt ervoor dat het altijd doorgaat
        time.setCycleCount(Timeline.INDEFINITE);
        // Hier wordt gecheckt of de aantal niet gelijk is aan null, als dat het geval is , dan stopt de animatie
        if (time != null){
            time.stop();
        }
        // De frame wordt hier gedefinieerd. Met een 1 seconde frame.
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            // De handle methode wordt overschreven
            @Override
            public void handle(ActionEvent event){
                newmethod(time);
            }


        });
        // haalt de keyframe van de animatie op en voegt deze toe
        time.getKeyFrames().add(frame);
        // Speelt de animatie vanuit de initiele positie. Moet gebruikt worden, anders wordt niets getoond
        time.playFromStart();

    }

    private void newmethod(Timeline time){
        // 1 van het aantal seconden afhalen
        seconds--;
        // Wijzig de text van label1
        label1.setText("Countdown: "+seconds.toString());
        System.out.println(seconds+" "+Math.floor(seconds/60.0));
        // als de seconden <= 0
        if (seconds <= 0) {
            // de frameanimatie wordt gestopt
            time.stop();
            // een pop-up wordt aangeroepen
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            // De tekst van de pop-up
            alert.setHeaderText("Count down reset to 0!");
            // De pop-up wordt getoond
            alert.show();
            // Wijzig de text van label1

            label1.setText("Countdown is op 0 nu");
            label2.setText("Gewijzigd");
            label2.setFont(Font.font(200));



        }

    }

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
