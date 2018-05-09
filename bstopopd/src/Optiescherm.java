package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Optiescherm extends Application {
    Button beginscherm = new Button("Naar beginscherm");
    Button afsluiten = new Button("Afsluiten");
    Button speelscherm = new Button("Naar speelscherm");

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        primaryStage.setTitle("Optiescherm");

        //naam
        Label naam = new Label("Naam: ");
        TextField tekst = new TextField();
        HBox hboxnaam = new HBox(10);
        hboxnaam.getChildren().addAll(naam, tekst);

        //hoeveelheid vragen
        Label hoeveellabel = new Label("Hoeveel vragen: ");
        VBox vboxhoeveelheid = new VBox(10);
        final ToggleGroup group = new ToggleGroup();
        String[] vraaglijst = {"30","40","50", "60", "70"};
        vboxhoeveelheid.getChildren().add(hoeveellabel);
        for (String option : vraaglijst){
            RadioButton rb = new RadioButton(option+"\n");
            rb.setToggleGroup(group);
            vboxhoeveelheid.getChildren().add(rb);
            if (option.equals("50")){
                rb.setSelected(true);
            }
        }

        //soorten
        String[] opties = {"Volledige naam","1-lettercode","3-lettercode","Hydrofobiciteit","Lading","Grootte","3D-voorkeur","Structuur"};
        Label vraaglabel = new Label("Soort vragen: ");
        VBox vboxvragen = new VBox(10);
        final ToggleGroup soortvragen = new ToggleGroup();
        vboxvragen.getChildren().add(vraaglabel);
        for (String optie2 : opties){
            RadioButton rb2 = new RadioButton(optie2+"\n");
            rb2.setToggleGroup(soortvragen);
            vboxvragen.getChildren().add(rb2);
        }

        Label antwoordlabel = new Label("Soort antwoorden: ");
        VBox vboxantwoorden = new VBox(10);
        final ToggleGroup soortantwoorden = new ToggleGroup();
        vboxantwoorden.getChildren().add(antwoordlabel);
        for (String optie3 : opties){
            RadioButton rb3 = new RadioButton(optie3+"\n");
            rb3.setToggleGroup(soortantwoorden);
            vboxantwoorden.getChildren().add(rb3);
        }

        HBox hboxsoorten = new HBox(20, vboxvragen, vboxantwoorden);

        //tijd
        String[] tijden = {"5", "10", "15", "20"};
        VBox vboxseconden = new VBox(10);
        Label secondenlabel = new Label("Hoeveel seconden: ");
        final ToggleGroup tijdgroep = new ToggleGroup();
        vboxseconden.getChildren().add(secondenlabel);
        for (String tijd : tijden){
            RadioButton rb3 = new RadioButton(tijd+"\n");
            rb3.setToggleGroup(tijdgroep);
            vboxseconden.getChildren().add(rb3);
            if (tijd.equals("10")){
                rb3.setSelected(true);
            }
        }
        vboxseconden.setVisible(false);

        VBox vboxtijd = new VBox(10);
        Label tijdlabel = new Label("Wil je met tijd werken?");
        final ToggleGroup toggletijd = new ToggleGroup();
        RadioButton ja = new RadioButton("Ja");
        RadioButton nee = new RadioButton("nee");
        ja.setToggleGroup(toggletijd);
        nee.setToggleGroup(toggletijd);
        ja.setOnAction(event -> vboxseconden.setVisible(true));
        nee.setOnAction(event -> vboxseconden.setVisible(false));
        vboxtijd.getChildren().addAll(tijdlabel, ja, nee, vboxseconden);

        //knoppen
        HBox hboxknoppen = new HBox(this.beginscherm, this.afsluiten, this.speelscherm);

        //eindbox
        VBox eindbox = new VBox(20, hboxnaam, vboxhoeveelheid, hboxsoorten, vboxtijd, hboxknoppen);

        pane.getChildren().addAll(eindbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
