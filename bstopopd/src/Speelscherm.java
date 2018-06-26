package src;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PublicKey;
import java.util.List;


public class Speelscherm extends Application {
    Instellingen instellingen;
    startScherm startscherm = new startScherm();
    Button naar_beginscherm = new Button("Stoppen");
    Button vorige = new Button("Vorige vraag");
    Button volgende = new Button("Volgende vraag");
    Button starten = new Button("Spel starten");
    Label vraag_label = new Label("");
    Label opening;
    HBox vraag_plaatje = new HBox();
    HBox buttons =  new HBox(20);
    int index = 0;
    Integer startTime;
    Integer seconds;
    private Label label1;
    Label title = new Label("Speelscherm");
    Group root = new Group();
    final ToggleGroup optiegroep = new ToggleGroup();
    Boolean[] correctbool;
    Stage primaryStage;
    String score;
    Resultaatscherm resultaatscherm;

    public static void main(String[] args) {
        launch(args);
    }

    public Speelscherm(Instellingen instellingen) {
        this.instellingen = instellingen;
        startTime = Integer.parseInt(instellingen.getSeconden().trim());
        seconds = startTime;
        this.opening = new Label("Welkom " + this.instellingen.getNaam()+"!");
        correctbool = new Boolean[this.instellingen.getHoeveelheid()];
        resultaatscherm = new Resultaatscherm(instellingen, getScore());
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        VBox mainbox = new VBox();
        StackPane stackmain = new StackPane();
        VBox startmain = new VBox();
        HBox buttonsbox = new HBox(10);
        VBox mainmain = new VBox(10);
        HBox Questiontypes = new HBox(5);
        HBox opties = new HBox(5);
        HBox buttonscontrol = new HBox(10);
        HBox countdowntimer = new HBox();

        mainPane.setCenter(mainbox);
        stackmain.getChildren().addAll(title);
        startmain.getChildren().addAll(opening, starten);
        buttonsbox.getChildren().addAll(buttons);
        buttonscontrol.getChildren().addAll(vorige, volgende, naar_beginscherm);
        mainmain.getChildren().addAll(Questiontypes , this.vraag_plaatje, opties, buttonsbox, countdowntimer, buttonscontrol);
        Questiontypes.getChildren().addAll(vraag_label);
        mainbox.getChildren().addAll(stackmain, startmain, mainmain);

        mainPane = stylemainPane(mainPane);
        styleTitle();
        mainbox = stylemainBox(mainbox);
        styleStartMain(startmain);
        stylemainmain(mainmain);
        styleButton3(starten);
        stylequestionlabel();
        mainPane.setMargin(mainbox,new Insets(50,150,100,150));
        mainmain.setMargin(Questiontypes,new Insets(10,10,0,10));
        mainbox.setMargin(buttonscontrol,new Insets(50,50,30,50));
        styleTypeboxes(Questiontypes);
        styleButton1(vorige);
        styleButton1(volgende);
        styleButton2(naar_beginscherm);
        buttonboxstyler();
        buttonsbox.setHgrow(buttons ,Priority.ALWAYS);
        buttonsbox.setHgrow(root ,Priority.ALWAYS);
        root.setAutoSizeChildren(true);
        opening.setPadding(new Insets(20,20,20,20));
        vraag_label.setWrapText(true);
        opening.setFont(Font.font("open-sans", 25));

        this.naar_beginscherm.setOnAction(event -> {
            startscherm.start(primaryStage);
        });

        this.primaryStage = primaryStage;

        List<String> sv = this.instellingen.getSoort_vragen();
        List<String> sa = this.instellingen.getSoort_antwoorden();
        vraag[] vraaglijst = new vraag[instellingen.getHoeveelheid()];

        if (instellingen.getTijd() == "Nee"){
            for (Integer i = 0; i < instellingen.getHoeveelheid(); i++) {
                vraaglijst[i] = new vraag(sv, sa);
                vraaglijst[i].generatequestions();
                this.starten.setOnAction(event -> {
                    styleStartMain2(startmain, mainmain);
                    update(vraaglijst[index]);
                });
                this.volgende.setOnAction(event -> {
                    try {
                        try {
                            RadioButton vraagtoggle = (RadioButton) optiegroep.getSelectedToggle();
                            vraagtoggle.setSelected(false);
                            String texttoggle = vraagtoggle.getText();
                            if (vraaglijst[index].getTypeanswer().equals("Structuur")){
                                if(vraaglijst[index].getAntwoord().equals(vraaglijst[index].getOpties()[Integer.parseInt(texttoggle.trim())-1])){
                                    correctbool[index] = true;
                                }
                                else {
                                    correctbool[index] = false;
                                }
                            }
                            if (vraaglijst[index].getAntwoord().equals(texttoggle.trim())) {
                                correctbool[index] = true;
                            } else {
                                correctbool[index] = false;
                            }
                        } catch (NullPointerException e) {
                            correctbool[index] = false;
                            System.out.println("No answer selected");
                        }
                        next(vraaglijst);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        score = Integer.toString(score(correctbool));
                        //resultaatscherm = new Resultaatscherm(instellingen, getScore());
                        resultaatscherm.score = getScore();
                        resultaatscherm.start(primaryStage);
                    }
                });
                this.vorige.setOnAction(event -> {
                    try {
                        previous(vraaglijst);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Kan niet naar vorige.");
                    }
                });
            }
        }else {
            //De groep bepalen
            // Een Vbox maken om de 2 labels onder elkaar te zetten
            VBox layout = new VBox(5);
            // De wigdet label met naam label1 aanmaken
            label1 = new Label();
            // label1 krijgt de witte tekstkleur
            label1.setTextFill(Color.BLACK);
            // de font van label1 wordt op 20 pixels gezet
            label1.setFont(Font.font(20));


            layout.getChildren().add(label1);
            layout.setLayoutX(40);
            root.getChildren().add(layout);
            for (Integer i = 0; i < instellingen.getHoeveelheid(); i++) {
                vraaglijst[i] = new vraag(sv, sa);
                vraaglijst[i].generatequestions();}
            this.starten.setOnAction(event -> {;
                styleStartMain2(startmain, mainmain);
                update_timer(vraaglijst[index], vraaglijst);
            });
            countdowntimer.setAlignment(Pos.CENTER);
            countdowntimer.setPadding(new Insets(10,40,10,40));
            countdowntimer.getChildren().add(root);
        }
        //eindbox.getChildren().addAll(knoppen, vraag_label, buttons);

        Scene main = new Scene(mainPane, 1000,600);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.setTitle("Optiescherm");
    }

    public Integer score(Boolean[] boolist){
        Integer score = 0;
        for (Boolean bool : boolist){
            //System.out.println(bool);
            if (bool.equals(true)){
                score += 1;
            }
        }
        return score;
    }

    private VBox styleStartMain(VBox startmain){
        startmain.setAlignment(Pos.CENTER);
        startmain.setPadding(new Insets(50,50,50,50));
        return startmain;
    }

    private VBox stylemainmain(VBox mainmain){
        mainmain.setManaged(false);
        mainmain.setVisible(false);
        mainmain.setAlignment(Pos.CENTER);
        mainmain.setPadding(new Insets(0,50,50,50));
        return mainmain;
    }

    private VBox styleStartMain2(VBox startmain, VBox mainmain){
        startmain.setPadding(new Insets(0,0,0,0));
        startmain.setPrefHeight(0);
        startmain.getChildren().clear();
        startmain.setVisible(false);
        mainmain.setManaged(true);
        mainmain.setVisible(true);
        return startmain;
    }

    private void buttonboxstyler(){
        this.buttons.setAlignment(Pos.CENTER);
    }

    private BorderPane stylemainPane(BorderPane mainPane){
        mainPane.prefHeight(600);
        mainPane.prefWidth(1000);
        mainPane.setStyle("-fx-background-color: #9ff9a5;");
        return mainPane;
    }

    private void styleTitle(){
        this.title.setPadding(new Insets(50,0,0,0));
        this.title.setAlignment(Pos.CENTER);
        this.title.setContentDisplay(ContentDisplay.CENTER);
        this.title.setFont(Font.font("open-sans", 36));
    }

    private void stylequestionlabel(){
        this.vraag_label.setPadding(new Insets(40,0,20,0));
        this.vraag_label.setAlignment(Pos.CENTER);
        this.vraag_label.setContentDisplay(ContentDisplay.CENTER);
        this.vraag_label.setFont(Font.font("open-sans", 18));
    }

    private VBox stylemainBox(VBox mainbox){
        mainbox.prefHeight(200);
        mainbox.prefWidth(100);
        mainbox.setAlignment(Pos.CENTER);
        mainbox.setStyle("-fx-background-color: #ffffff;");
        blur(mainbox);
        return mainbox;
    }

    private VBox blur(VBox blurredbox){
        try {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10.0);
            dropShadow.setOffsetX(0);
            dropShadow.setOffsetY(0);
            blurredbox.setEffect(dropShadow);
        }
        catch (Exception e){
            System.out.println("cannot apply blur to object.");
        }
        return blurredbox;
    }

    private Button blur(Button blurredbox){
        try {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(1.0);
            dropShadow.setOffsetX(0);
            dropShadow.setOffsetY(0);
            blurredbox.setEffect(dropShadow);
        }
        catch (Exception e){
            System.out.println("cannot apply blur to object.");
        }
        return blurredbox;
    }

    private StackPane stylestackMain(StackPane stackmain){
        stackmain.prefHeight(100);
        stackmain.prefWidth(Region.USE_COMPUTED_SIZE);
        return stackmain;
    }

    private Button styleButton1(Button button){
        button.setPadding(new Insets(8,16,8,16));
        button.setStyle("-fx-background-color: #66bec1; ");
        button.setTextFill(Color.WHITE);
        blur(button);
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #7ce6ea;");
            button.setCursor(Cursor.HAND);
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #66bec1; ");
            button.setCursor(Cursor.DEFAULT);
        });
        return button;
    }

    public RadioButton stylerb(RadioButton radio){
        radio.setFont(Font.font("open-sans", 15));
        return radio;
    }

    private Button styleButton2(Button button){
        button.setPadding(new Insets(8,16,8,16));
        button.setStyle("-fx-background-color: #d34a78; ");
        button.setTextFill(Color.WHITE);
        blur(button);
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #f45a8e;");
            button.setCursor(Cursor.HAND);
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #d34a78; ");
            button.setCursor(Cursor.DEFAULT);
        });
        return button;
    }

    private Button styleButton3(Button button){
        button.setPadding(new Insets(8,20,8,20));
        button.setFont(Font.font("open-sans", 25));
        button.setStyle("-fx-background-color: #66bec1; ");
        button.setTextFill(Color.WHITE);
        blur(button);
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #7ce6ea;");
            button.setCursor(Cursor.HAND);
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #66bec1; ");
            button.setCursor(Cursor.DEFAULT);
        });
        return button;
    }

    private HBox styleTypeboxes(HBox box){
        box.setPrefWidth(250);
        box.setAlignment(Pos.CENTER);
        return box;
    };

    public void close(Stage primaryStage){
        primaryStage.close();
    }

    private void doTime(vraag vraag, vraag[] vraaglijst){
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
                newmethod(time, vraag, vraaglijst);
            }


        });
        // haalt de keyframe van de animatie op en voegt deze toe
        time.getKeyFrames().add(frame);
        // Speelt de animatie vanuit de initiele positie. Moet gebruikt worden, anders wordt niets getoond
        time.playFromStart();

    }

    private void newmethod(Timeline time, vraag vraag, vraag[] vraaglijst){
        // 1 van het aantal seconden afhalen
        // Wijzig de text van label1
        label1.setText("Countdown: "+seconds.toString());
        seconds--;
        System.out.println("iets");
        // als de seconden <= 0
        if (seconds < 0) {
            // de frameanimatie wordt gestopt
            seconds = Integer.parseInt(instellingen.getSeconden().trim());
            time.stop();
            System.out.println(index+2);
            System.out.println(instellingen.getHoeveelheid());
            try{
                System.out.println("dit was niet de laatste vraag");
                try {
                    RadioButton vraagtoggle = (RadioButton) optiegroep.getSelectedToggle();
                    vraagtoggle.setSelected(false);
                    String texttoggle = vraagtoggle.getText();
                    if (vraaglijst[index].getTypeanswer().equals("Structuur")){
                        if(vraaglijst[index].getAntwoord().equals(vraaglijst[index].getOpties()[Integer.parseInt(texttoggle.trim())-1])){
                            correctbool[index] = true;
                        }
                        else {
                            correctbool[index] = false;
                        }
                    }
                    if (vraaglijst[index].getAntwoord().equals(texttoggle.trim())) {
                        correctbool[index] = true;
                    } else {
                        correctbool[index] = false;
                    }
                } catch (NullPointerException e) {
                    correctbool[index] = false;
                    //System.out.println("No answer selected");
                }
                next_timer(vraaglijst);
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println(correctbool[29]);
                System.out.println(score(correctbool));
                System.out.println(this.primaryStage);
                resultaatscherm.score = Integer.toString(score(correctbool));
                resultaatscherm.start(this.primaryStage);
            }
        }

    }

    private void update(vraag vraag){
        try {
            System.out.println("naar structuur_vraag functie");
            this.vraag_plaatje = structuur_vraag(vraag);
        }catch (IOException e){System.out.println("niet naar structuur_vraag functie");}
        vraag_label.setText("Vraag " + (index + 1) + ": " + vraag.getVraag());
        buttons.getChildren().clear();
        try {
            buttons = buttonbox(vraag);
        }catch (IOException e){

        }
    }

    private void update_timer(vraag vraag, vraag[] vraaglijst){
        try {
            this.vraag_plaatje = structuur_vraag(vraag);
        }catch (IOException e){}
        vraag_label.setText("Vraag " + (index + 1) + ": " + vraag.getVraag());
        buttons.getChildren().clear();
        try {
            buttons = buttonbox(vraag);
        }catch (IOException e){

        }
        doTime(vraag, vraaglijst);
    }

    private HBox structuur_vraag(vraag vraag) throws IOException{
        this.vraag_plaatje.getChildren().clear();
        if (vraag.getTypequestion().equals("Structuur")){
            try{
                String pathway = "C:/Users/julian/IdeaProjects/bstopdracht3/bstopopd/src/";
                File file = new File("").getAbsoluteFile();
                if (System.getProperty("os.name").split("")[0] == "Windows" ){
                    pathway = (file+"\\bstopopd\\src\\pictures");
                }
                else{
                    pathway = (file+"/bstopopd/src/pictures");
                };
                FileInputStream inputstream = new FileInputStream(pathway +"/"+ vraag.getVolledige_naam() +".png");
                Image image = new Image(inputstream);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(150);
                this.vraag_plaatje.getChildren().add(imageView);

            }catch (IOException e){
                System.out.println("IOEXEPTION");
            }
        }else {
        }
        return this.vraag_plaatje;
    }

    private HBox buttonbox(vraag vraag) throws IOException {
        Integer ind = 0;
        for (String optie:vraag.getOpties()){
            if (vraag.getTypeanswer().equals("Structuur")){
                try{
                    String pathway = "C:/Users/julian/IdeaProjects/bstopdracht3/bstopopd/src/";
                    File file = new File("").getAbsoluteFile();
                    if (System.getProperty("os.name").split("")[0] == "Windows" ){
                        pathway = (file+"\\bstopopd\\src\\pictures");
                    }
                    else{
                        pathway = (file+"/bstopopd/src/pictures");
                    };
                    FileInputStream inputstream = new FileInputStream(pathway +"/"+ optie +".png");
                    Image image = new Image(inputstream);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(150);
                    Group root = new Group(imageView);
                    RadioButton optiebutton = new RadioButton((Integer.toString(ind+1)));
                    optiebutton.setToggleGroup(optiegroep);
                    HBox buttoncombi = new HBox(10);
                    stylerb(optiebutton);
                    //System.out.println(optiegroep);
                    buttoncombi.getChildren().addAll(optiebutton, root);
                    buttons.getChildren().add(buttoncombi);
                    ind++;
                }catch (IOException e){
                    System.out.println("IOEXEPTION");
                }
            }else {
                RadioButton optiebutton = new RadioButton(optie + "\n");
                stylerb(optiebutton);
                optiebutton.setToggleGroup(optiegroep);
                buttons.getChildren().add(optiebutton);
            }
        }
        center_HBox(buttons);
        return buttons;
    }
    private void next(vraag[] vraaglijst){
        this.index += 1;
        update(vraaglijst[this.index]);
    }

    private HBox center_HBox(HBox hbox){
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    public String getScore(){return score;}

    private void next_timer(vraag[] vraaglijst){
        this.index += 1;
        update_timer(vraaglijst[this.index], vraaglijst);
    }

    private void previous(vraag[] vraaglijst){
        if (index == 0){

        }
        else {
            index -= 1;
            update(vraaglijst[index]);
        }
    }

    public Resultaatscherm returnresultaatscherm(){
        return resultaatscherm;
    }
}
