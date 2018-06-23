package src;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Optiescherm extends Application {
    Button naar_beginscherm = new Button("Naar beginscherm");
    Button afsluiten = new Button("Afsluiten");
    Button naar_speelscherm = new Button("Naar speelscherm");
    Instellingen inst = new Instellingen();
    Speelscherm speelscherm = new Speelscherm(inst);
    Label title = new Label("Instellingen");
    TextField tekst = new TextField();
    Label label_event = new Label("");

    @Override
    public void start(Stage primaryStage) {

        BorderPane mainPane = new BorderPane();
        VBox mainbox = new VBox();
        StackPane stackmain = new StackPane();
        HBox buttonsbox = new HBox(10);
        HBox questionbox = new HBox(10);
        VBox Questiontypes = new VBox(5);
        VBox Answertypes = new VBox(5);
        HBox time1 = new HBox(10);
        HBox time2 = new HBox(10);
        HBox buttonscontrol = new HBox(10);
        Label label_naam = new Label("Naam: ");

        HBox hboxnaam = new HBox(10);
        Label hoeveellabel = new Label("Hoeveel vragen: ");
        Label vraaglabel = new Label("Soort vragen: ");
        Label antwoordlabel = new Label("Soort antwoorden: ");
        Label secondenlabel = new Label("Hoeveel seconden: ");
        Label tijdlabel = new Label("Wil je met tijd werken?");

        mainPane.setCenter(mainbox);
        questionbox.getChildren().addAll(Questiontypes, Answertypes);
        stackmain.getChildren().addAll(this.title);
        hboxnaam.getChildren().addAll(label_naam, this.tekst, label_event);
        buttonsbox.getChildren().addAll(hoeveellabel);
        Questiontypes.getChildren().addAll(vraaglabel);
        Answertypes.getChildren().addAll(antwoordlabel);
        time1.getChildren().addAll(tijdlabel);
        time2.getChildren().addAll(secondenlabel);
        buttonscontrol.getChildren().addAll(naar_speelscherm, naar_beginscherm, afsluiten);
        mainbox.getChildren().addAll(stackmain, hboxnaam, buttonsbox, questionbox, time1, time2, buttonscontrol);

        mainPane = stylemainPane(mainPane);
        mainPane.setMargin(mainbox,new Insets(50,150,100,150));
        styleTitle();
        mainbox = stylemainBox(mainbox);
        mainbox.setMargin(hboxnaam,new Insets(20,50,0,50));
        mainbox.setMargin(buttonsbox,new Insets(10,50,0,50));
        mainbox.setMargin(questionbox,new Insets(10,50,0,50));
        mainbox.setMargin(time1,new Insets(10,50,0,50));
        mainbox.setMargin(time2,new Insets(10,50,10,50));
        mainbox.setMargin(buttonscontrol,new Insets(10,50,30,50));
        styleTypeboxes(Questiontypes);
        styleTypeboxes(Answertypes);
        styleButton1(naar_speelscherm);
        styleButton1(naar_beginscherm);
        styleButton2(afsluiten);
        tekst.setFocusTraversable(false);

        //Dit stukje maakt een invoerbalk voor je naam (tekst) en een error label (label_event) mocht je je naam niet
        // invullen
        tekst.setPromptText("Geef je naam: ");
        ToggleGroup group = new ToggleGroup();
        String[] vraaglijst = {"30","40","50", "60", "70"};
        for (String option : vraaglijst){
            RadioButton rb = new RadioButton(option+"\n");
            rb.setToggleGroup(group);
            buttonsbox.getChildren().add(rb);
            if (option.equals("50")){
                rb.setSelected(true);
            }
        }
        //Dit stukje maakt radiobuttons waar je de hoeveelheid vragen kan aanklikken, de default staat op 50
        String[] opties = {"Volledige_naam","1-lettercode","3-lettercode","Hydrofobiciteit","Lading","Grootte",
                "3D-voorkeur","Structuur"};

        CheckBox[] cb1 = new CheckBox[opties.length];
        for (int i = 0; i < opties.length; i++) {
            cb1[i] = new CheckBox(opties[i]);
            Questiontypes.getChildren().add(cb1[i]);
        }
        /**Dit deel maakt radiobuttons waarin je voor het soort antwoorden kan kiezen en een errorlabel (errorlabel_a)
         * voor als je niks aanklikt */
        CheckBox[] cb2 = new CheckBox[opties.length];
        for (int i = 0; i < opties.length; i++) {
            cb2[i] = new CheckBox(opties[i]);
            Answertypes.getChildren().add(cb2[i]);
        }
        /*ToggleGroup soortantwoorden = new ToggleGroup();
        for (String optie3 : opties){
            RadioButton rb3 = new RadioButton(optie3+"\n");
            rb3.setToggleGroup(soortantwoorden);
            Answertypes.getChildren().add(rb3);
        }*/
        /**Dit stukje maakt radiobuttons om de hoeveelheid seconden te kiezen. De zichtbaarheid staat op false omdat hij
         // pas zichtbaar wordt als bij tijd "ja"is aangeklikt */
        String[] tijden = {"5", "10", "15", "20"};
        ToggleGroup tijdgroep = new ToggleGroup();
        for (String tijd : tijden){
            RadioButton rb4 = new RadioButton(tijd+"\n");
            rb4.setToggleGroup(tijdgroep);
            time2.getChildren().add(rb4);
            if (tijd.equals("10")){
                rb4.setSelected(true);
            }
        }
        time2.setVisible(false);
        /**Dit deel maakt radiobutton voor wel of niet met tijd werken en een errorlabel (errorlabel_tijd)*/
        ToggleGroup toggletijd = new ToggleGroup();
        RadioButton ja = new RadioButton("Ja");
        RadioButton nee = new RadioButton("Nee");
        ja.setToggleGroup(toggletijd);
        nee.setToggleGroup(toggletijd);
        ja.setOnAction(event -> {
            time2.setVisible(true);

        });
        nee.setOnAction(event -> time2.setVisible(false));
        time1.getChildren().addAll(ja,nee);

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
         - wordt gecheckt of er verschillende dingen zijn gekozen bij soort vraag en soort antwoord.
         //  - wordt gecheckt of er iets is aangeklikt bij de tijd(chk_tijd)
         //      en deze keuze(tijdkeuze) wordt in instellingen gezet
         //  - wordt gecheckt of er iets is ingevuld bij seconden (chk_sec)
         //      en het aantal seconden (sec_keuze) wordt in instellingen gezet
         //  - als dit allemaal goed was ingevuld wordt het speelscherm aangeroepen
         //  - hier komen nog wat else statements waarbij de errorlabels zichtbaar gemaakt worden als er niks is ingevuld
         */
        this.naar_speelscherm.setOnAction(event -> {
            if ((tekst.getText() != null && ! tekst.getText().isEmpty())){
                inst.setNaam(tekst.getText());
                RadioButton chk_h = (RadioButton)group.getSelectedToggle();
                String hoeveelheidkeuze = chk_h.getText();
                inst.setHoeveelheid(hoeveelheidkeuze);
                //RadioButton chk_sv = (RadioButton)soortvragen.getSelectedToggle();
                List<String> chk_sv = new ArrayList<>();
                for (Integer i = 0; i < cb1.length; i++){
                    if(cb1[i].isSelected()){
                        chk_sv.add(opties[i]);
                    }
                }
                if ((chk_sv != null)){
                    inst.setSoort_vragen(chk_sv);
                    List<String> chk_sa = new ArrayList<>();
                    for (Integer i = 0; i < cb2.length; i++){
                        if(cb2[i].isSelected()){
                            chk_sa.add(opties[i]);
                        }
                    }
                    if ((chk_sa != null)){
                        inst.setSoort_antwoorden(chk_sa);
                        RadioButton chk_tijd = (RadioButton)toggletijd.getSelectedToggle();
                        String[] list = new String[]{"Hydrofobiciteit", "Lading", "Grootte", "3D-voorkeur", "Structuur"};
                        if ((chk_sa.size() == 1 && chk_sv.size() == 1 && chk_sa.equals(chk_sv)) ||
                                (chk_sa.size() == 1 && chk_sv.size() == 1 && inlist(new String[]{chk_sa.get(0), chk_sv.get(0)} ,list))){
                            setLabelevent("Je antwoord en vraag kunnen niet van dezelfde soort zijn.");
                        }else
                            if ((chk_tijd != null)){
                                String tijdkeuze = chk_tijd.getText();
                                inst.setTijd(tijdkeuze);
                                RadioButton chk_sec = (RadioButton)tijdgroep.getSelectedToggle();
                                String sec_keuze = chk_sec.getText();
                                inst.setSeconden(sec_keuze);
                                this.speelscherm.start(primaryStage);
                            }else{
                                setLabelevent("Je moet kiezen of je wel of niet met tijd wilt spelen.");
                            }
                        }else{
                            setLabelevent("Je moet een soort antwoord selecteren.");
                        }
                    }else{
                        setLabelevent("Je moet een soort vraag selecteren.");
                    }
                }else{
                    setLabelevent("Je moet je naam invullen.");
            }
        });

        Scene main = new Scene(mainPane, 1000,600);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.setTitle("Optiescherm");
    }

    private void setLabelevent(String text){
        this.label_event.setText(text);
    }

    private BorderPane stylemainPane(BorderPane mainPane){
        mainPane.prefHeight(600);
        mainPane.prefWidth(1000);
        mainPane.setStyle("-fx-background-color: #9ff9a5;");
        return mainPane;
    }

    private void styleTitle(){
        this.title.setPadding(new Insets(40,0,20,0));
        this.title.setAlignment(Pos.CENTER);
        this.title.setContentDisplay(ContentDisplay.CENTER);
        this.title.setFont(Font.font("open-sans", 36));
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

    private VBox styleTypeboxes(VBox box){
        box.setPrefWidth(250);
        return box;
    };

    public void close(Stage primaryStage){
        primaryStage.close();
    }

    private Boolean inlist(String[] string, String[] stringlist) {
        Boolean value = false;
        Integer match = 0;
        for (String item : stringlist) {
            for (Integer i = 0; i < string.length; i++) {
                if (item.equals(string[i])) {
                    match += 1;
                }
            }
        }
        if (match >= 2) {
            value = true;
        }
        return value;
    }

};







        //Dit stukje maakt een invoerbalk voor je naam (tekst) en een error label (label_event) mocht je je naam niet
        // invullen
/*        final TextField tekst = new TextField();
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
        String[] opties = {"Volledige_naam","1-lettercode","3-lettercode","Hydrofobiciteit","Lading","Grootte",
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
/*        Label errorlabel_a = new Label();
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
        HBox hboxsoorten = new HBox(5, vboxvragen, vboxantwoorden);

        /**Dit stukje maakt radiobuttons om de hoeveelheid seconden te kiezen. De zichtbaarheid staat op false omdat hij
        // pas zichtbaar wordt als bij tijd "ja"is aangeklikt */
 /*       String[] tijden = {"5", "10", "15", "20"};
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
   /*     Label errorlabel_tijd = new Label();
        VBox vboxtijd = new VBox(0);
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
  //      HBox hboxknoppen = new HBox(this.naar_beginscherm, this.afsluiten, this.naar_speelscherm);

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
            - wordt gecheckt of er verschillende dingen zijn gekozen bij soort vraag en soort antwoord.
        //  - wordt gecheckt of er iets is aangeklikt bij de tijd(chk_tijd)
        //      en deze keuze(tijdkeuze) wordt in instellingen gezet
        //  - wordt gecheckt of er iets is ingevuld bij seconden (chk_sec)
        //      en het aantal seconden (sec_keuze) wordt in instellingen gezet
        //  - als dit allemaal goed was ingevuld wordt het speelscherm aangeroepen
        //  - hier komen nog wat else statements waarbij de errorlabels zichtbaar gemaakt worden als er niks is ingevuld
         */
 /*       VBox eindbox = new VBox(10, hboxnaam, vboxhoeveelheid, hboxsoorten, vboxtijd, hboxknoppen);
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
*/
