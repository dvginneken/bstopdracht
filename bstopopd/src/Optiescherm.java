package src;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Optiescherm extends Application {
    Button naar_beginscherm = new Button("Naar beginscherm");
    Button afsluiten = new Button("Afsluiten");
    Button naar_speelscherm = new Button("Naar speelscherm");
    Instellingen inst = new Instellingen();
    Speelscherm speelscherm = new Speelscherm(inst);

    //TODO: afsluiten knop

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1300, 800);
        primaryStage.setTitle("Optiescherm");

        //Dit stukje maakt een invoerbalk voor je naam (tekst) en een error label (label_event) mocht je je naam niet
        // invullen
        final TextField tekst = new TextField();
        final Label label_event = new Label();
        tekst.setPromptText("Geef je naam: ");
        HBox hboxnaam = new HBox(10);
        hboxnaam.getChildren().addAll(tekst, label_event);

        //Dit stukje maakt radiobuttons waar je de hoeveelheid vragen kan aanklikken, de default staat op 50
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




        //Dit deel maakt radiobuttons waarin je voor het soort vragen kan kiezen en een errorlabel (errorlabel_v) voor
        // als je niks aanklikt
        Label errorlabel_v = new Label();
        String[] opties = {"Volledige naam","1-lettercode","3-lettercode","Hydrofobiciteit","Lading","Grootte",
                "3D-voorkeur","Structuur"};
        Label vraaglabel = new Label("Soort vragen: ");
        VBox vboxvragen = new VBox(10);
        final ToggleGroup soortvragen = new ToggleGroup();
        vboxvragen.getChildren().add(vraaglabel);
        for (String optie2 : opties){
            RadioButton rb2 = new RadioButton(optie2+"\n");
            rb2.setToggleGroup(soortvragen);
            vboxvragen.getChildren().add(rb2);
        }
        vboxvragen.getChildren().add(errorlabel_v);

        /**Dit deel maakt radiobuttons waarin je voor het soort antwoorden kan kiezen en een errorlabel (errorlabel_a)
        // voor als je niks aanklikt */
        Label errorlabel_a = new Label();
        Label antwoordlabel = new Label("Soort antwoorden: ");
        VBox vboxantwoorden = new VBox(10);
        final ToggleGroup soortantwoorden = new ToggleGroup();
        vboxantwoorden.getChildren().add(antwoordlabel);
        for (String optie3 : opties){
            RadioButton rb3 = new RadioButton(optie3+"\n");
            rb3.setToggleGroup(soortantwoorden);
            vboxantwoorden.getChildren().add(rb3);
        }
        vboxantwoorden.getChildren().add(errorlabel_a);
        HBox hboxsoorten = new HBox(20, vboxvragen, vboxantwoorden);

        /**Dit stukje maakt radiobuttons om de hoeveelheid seconden te kiezen. De zichtbaarheid staat op false omdat hij
        // pas zichtbaar wordt als bij tijd "ja"is aangeklikt */
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

        /**Dit deel maakt radiobutton voor wel of niet met tijd werken en een errorlabel (errorlabel_tijd)*/
        Label errorlabel_tijd = new Label();
        VBox vboxtijd = new VBox(10);
        Label tijdlabel = new Label("Wil je met tijd werken?");
        final ToggleGroup toggletijd = new ToggleGroup();
        RadioButton ja = new RadioButton("Ja");
        RadioButton nee = new RadioButton("nee");
        ja.setToggleGroup(toggletijd);
        nee.setToggleGroup(toggletijd);
        ja.setOnAction(event -> {
            vboxseconden.setVisible(true);

        });
        nee.setOnAction(event -> vboxseconden.setVisible(false));
        vboxtijd.getChildren().addAll(tijdlabel, ja, nee, vboxseconden, errorlabel_tijd);



        /**hier worden de onderste knoppen in een box gezet */
        HBox hboxknoppen = new HBox(this.naar_beginscherm, this.afsluiten, this.naar_speelscherm);

        /**
        //Alle boxen worden nu samengevoegd
        //Als de speelscherm knop wordt aangeklikt (naar_speelscherm):
        //  - wordt gecheckt of er iets is ingevuld bij naam (tekst.getText())
        //      en de naam wordt in het instellingen (inst) scherm gezet
        //  - wordt gecheck of er iets is aangeklikt bij de hoeveelheid (chk_h)
        //      en de hoeveelheid (hoeveelheidkeuze) wordt in het instellingen scherm gezet
        //  - wordt gecheck of er iets is aangeklikt bij de soort vragen (chk_sv)
        //      en de soort vraag (vraagkeuze) wordt in het instellingen scherm gezet
        //  - wordt gecheck of er iets is aangeklikt bij de soort antwoorden (chk_sa)
        //      en de soort antwoorden (antwoordkeuze) wordt in het instellingen scherm
        //  - wordt gecheckt of er iets is aangeklikt bij de tijd(chk_tijd)
        //      en deze keuze(tijdkeuze) wordt in instellingen gezet
        //  - wordt gecheckt of er iets is ingevuld bij seconden (chk_sec)
        //      en het aantal seconden (sec_keuze) wordt in instellingen gezet
        //  - als dit allemaal goed was ingevuld wordt het speelscherm aangeroepen
        //  - hier komen nog wat else statements waarbij de errorlabels zichtbaar gemaakt worden als er niks is ingevuld
         */
        VBox eindbox = new VBox(20, hboxnaam, vboxhoeveelheid, hboxsoorten, vboxtijd, hboxknoppen);
        pane.getChildren().addAll(eindbox);
        primaryStage.setScene(scene);
        this.naar_speelscherm.setOnAction(event -> {
            if ((tekst.getText() != null && ! tekst.getText().isEmpty())){
                inst.setNaam(tekst.getText());
                RadioButton chk_h = (RadioButton)group.getSelectedToggle();
                String hoeveelheidkeuze = chk_h.getText();
                inst.setHoeveelheid(hoeveelheidkeuze);
                RadioButton chk_sv = (RadioButton)soortvragen.getSelectedToggle();
                String vraagkeuze = chk_sv.getText();
                if ((chk_sv != null)){
                    inst.setSoort_vragen(vraagkeuze);
                    RadioButton chk_sa = (RadioButton)soortantwoorden.getSelectedToggle();
                    String antwoordkeuze = chk_sa.getText();
                    if ((chk_sa != null)){
                        inst.setSoort_antwoorden(antwoordkeuze);
                        RadioButton chk_tijd = (RadioButton)toggletijd.getSelectedToggle();
                        String tijdkeuze = chk_tijd.getText();
                        if((vraagkeuze.equals(antwoordkeuze))){
                            errorlabel_v.setText("Je antwoord en vraag kunnen niet van dezelfde soort zijn.");
                        }else
                            if ((chk_tijd != null)){
                                inst.setTijd(tijdkeuze);
                                RadioButton chk_sec = (RadioButton)tijdgroep.getSelectedToggle();
                                String sec_keuze = chk_sec.getText();
                                inst.setSeconden(sec_keuze);
                                this.speelscherm.start(primaryStage);
                            }else{
                                errorlabel_tijd.setText("Je moet kiezen of je wel of niet met tijd wilt spelen.");
                            }
                        }else{
                            errorlabel_a.setText("Je moet een soort antwoord selecteren.");
                        }
                    }else{
                        errorlabel_v.setText("Je moet een soort vraag selecteren.");
                    }
                }else{
                    label_event.setText("Je moet je naam invullen.");
            }
        });
        primaryStage.show();
    }
}

