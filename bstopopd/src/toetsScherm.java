package src;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.plaf.LabelUI;
import javax.swing.plaf.PanelUI;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class toetsScherm extends Application {

    List<String> namelist = new ArrayList<String>();
    Button naar_beginscherm = new Button("Naar beginscherm");
    Label filename = new Label("Geselecteerd bestand: ");
    Label previewLabel = new Label("Geen bestand geselecteerd.");
    Label title = new Label("Genereer toetsen");
    Label Hoeveel = new Label("Hoeveel vragen moeten er gegenereerd worden?");
    Button inputfile = new Button("selecteer bestand");
    Button generate = new Button("genereer toetsbestanden");
    Label progress = new Label("");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        // Building blocks
        BorderPane mainPane = new BorderPane();
        VBox mainbox = new VBox();
        StackPane stackmain = new StackPane();
        HBox buttonsbox = new HBox(10);
        SplitPane splitmain = new SplitPane();
        VBox left = new VBox(0);
        VBox right = new VBox();
        Pane selectedfile = new Pane();
        Pane hoeveelvraag = new Pane();

        mainPane.setCenter(mainbox);
        stackmain.getChildren().add(this.title);
        hoeveelvraag.getChildren().add(this.Hoeveel);
        left.getChildren().add(hoeveelvraag);
        ToggleGroup group = new ToggleGroup();
        String[] options = {"30","40","50", "60", "70"};
        for (String option : options){
            RadioButton rb = new RadioButton(option+"\n");
            rb.setToggleGroup(group);
            rb.setPadding(new Insets(4,10,4,10));
            left.getChildren().add(rb);
            if (option.equals("50")){
                rb.setSelected(true);
            }
        };
        left.getChildren().add(this.progress);

        //Styling
        selectedfile.getChildren().add(this.filename);
        right.getChildren().addAll(selectedfile, this.previewLabel);
        splitmain.getItems().addAll(left, right);
        buttonsbox.getChildren().addAll(this.inputfile, this.generate, this.naar_beginscherm);
        mainbox.getChildren().addAll(stackmain, splitmain, buttonsbox);

        mainPane = stylemainPane(mainPane);
        mainPane.setMargin(mainbox,new Insets(100,150,100,150));
        styleTitle();
        mainbox = stylemainBox(mainbox);
        mainbox.setMargin(splitmain,new Insets(0,50,20,50));
        mainbox.setMargin(buttonsbox,new Insets(0,50,20,50));
        styleButton1(this.generate);
        styleButton1(this.inputfile);
        styleButton2(this.naar_beginscherm);
        styleSplitpane(splitmain);
        stylePane(selectedfile);
        stylePane(hoeveelvraag);
        applyLabelPadding(this.previewLabel);
        applyLabelPadding(this.Hoeveel);
        applyLabelPadding(this.filename);
        applyLabelPadding(this.progress);

        //Functional
        FileChooser fileChooser = new FileChooser();
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                System.out.println("Selected Radio Button - "+chk.getText());

            }
        });
        this.inputfile.setOnAction((ActionEvent event) -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                this.filename.setText("Geselecteerd: "+file.getName());
                String previewString = "";
                try{
                    Scanner scanner = new Scanner(file);
                    Integer i = 0;
                    while (scanner.hasNext()){
                        String line = scanner.nextLine();
                        this.namelist.add(line.split(" ")[0]);
                        System.out.println(this.namelist.get(i));
                        if (i <= 9){
                            previewString = previewString + line+ "\n";
                        }
                        i++;
                    }
                }
                catch (FileNotFoundException e){
                    System.out.println(e);
                }
                this.previewLabel.setText(previewString);
            }

        });
        this.generate.setOnAction((ActionEvent event) -> {
            this.progress.setText("Bestanden aanmaken...");
            RadioButton chk = (RadioButton)group.getSelectedToggle();
            if (!chk.getText().trim().isEmpty()){
                System.out.println((chk.getText().trim()));
                Integer optie = Integer.parseInt(chk.getText().trim());
                vraag[] vraaglijst = new vraag[optie];
                try{
                    writeFile(vraaglijst, optie);
                }
                catch (IOException e){
                    System.out.println(e);
                }
            }
            this.progress.setText("Bestanden aangemaakt!");

        });

        Scene main = new Scene(mainPane, 1000,600);
        primaryStage.setScene(main);
        primaryStage.show();
    }

    private BorderPane stylemainPane(BorderPane mainPane){
        mainPane.prefHeight(600);
        mainPane.prefWidth(1000);
        mainPane.setStyle("-fx-background-color: #9ff9a5;");
        return mainPane;
    }

    private void styleTitle(){
        this.title.setPadding(new Insets(0,0,30,0));
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

    private SplitPane styleSplitpane(SplitPane splitPane){
        splitPane.setDividerPosition(0,0.6);
        splitPane.setPrefHeight(220);
        return splitPane;
    }

    private Pane stylePane(Pane pane){
        this.filename.setFont(Font.font("open-sans", 14));
        this.Hoeveel.setFont(Font.font("open-sans", 14));
        pane.setStyle("-fx-background-color: #dedede");
        return pane;
    }

    private void applyLabelPadding(Label label){
        label.setPadding(new Insets(4,10,4,10));
    }

    public void writeFile(vraag[] vraaglijst, Integer option) throws IOException{
        String pathway = "C:/Users/julian/IdeaProjects/bstopdracht3/bstopopd/src/";
        File file = new File("").getAbsoluteFile();
        if (System.getProperty("os.name").split("")[0] == "Windows" ){
            pathway = (file+"\\bstopopd\\src\\");
        }
        else{
            pathway = (file+"/bstopopd/src/");
        };
        if (this.namelist != null){
            //Integer amount = option * this.namelist.length;
            for (String naam : this.namelist){
                File outfile = new File(pathway+"AAtest_"+naam+".txt");
                File outfile2 = new File(pathway+"AANT_"+naam+".txt");
                String format = "";
                clearfile(outfile);
                clearfile(outfile2);
                for (Integer i = 0; i < option; i++) {
                    vraaglijst[i] = new vraag();
                    FileWriter fw = new FileWriter(outfile, true);
                    PrintWriter pw = new PrintWriter(fw);
                    vraaglijst[i].generatequestions();
                    pw.println((i + 1) + ". " + vraaglijst[i].getVraag() + "\n");
                    switch (vraaglijst[i].getOpties().length){
                        case 3:
                            format = String.format("A) %s\r\nB) %s\r\nC) %s\r\n", vraaglijst[i].getOpties()[0], vraaglijst[i].getOpties()[1], vraaglijst[i].getOpties()[2]);
                            pw.println(format);
                            break;
                        case 4:
                            format = String.format("A) %s\r\nB) %s\r\nC) %s\r\nD) %s\r\n", vraaglijst[i].getOpties()[0], vraaglijst[i].getOpties()[1], vraaglijst[i].getOpties()[2], vraaglijst[i].getOpties()[3]);
                            pw.println(format);
                            break;
                    }
                    //System.out.println(i.toString()+ vraaglijst[i].getVraag());
                    pw.close();
                    FileWriter fw2 = new FileWriter(outfile2, true);
                    PrintWriter pw2 = new PrintWriter(fw2);
                    pw2.println((i + 1) + ". " + vraaglijst[i].getVraag() + "\r\n"+ vraaglijst[i].getAntwoord()+ "\r\n");
                    pw2.close();
                }
            }
        }
        else {
            System.out.println("this.namelist = null line 167 toetsscherm.java");
        }
    }

    public void clearfile(File file){
        try {
            FileWriter clear = new FileWriter(file, false);
            String format = "";
            clear.append(format);
            clear.close();
        }
        catch (IOException e){
            System.out.println(e + "clearfile() Toetscherm.java");
        }
    }

}
