package src;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class toetsScherm extends Application {

    String[] namelist;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox mainPane = new VBox();
        mainPane.setPadding(new Insets(100,300,100,300));
        Text titel = new Text("Genereer toets");
        HBox contentholder = new HBox();
        VBox left = new VBox();
        contentholder.getChildren().addAll(left);
        Text hoeveelheid = new Text("Hoeveel vragen:\n");

        final ToggleGroup group = new ToggleGroup();
        String[] options = {"30","40","50", "60", "70"};
        left.getChildren().add(hoeveelheid);
        for (String option : options){
            RadioButton rb = new RadioButton(option+"\n");
            rb.setToggleGroup(group);
            left.getChildren().add(rb);
            if (option.equals("50")){
                rb.setSelected(true);
            }
        };
        final FileChooser fileChooser = new FileChooser();
        Button inputfile = new Button("selecteer bestand");
        Text filename = new Text();

        Button generate = new Button("genereer toets!");

        left.getChildren().addAll(inputfile, filename, generate);

        /*group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                System.out.println("Selected Radio Button - "+chk.getText());

            }
        });*/



        inputfile.setOnAction((ActionEvent event) -> {

            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                filename.setText("Geselecteerd: "+file.getName());
            }
            try{
                Scanner scanner = new Scanner(file);
                this.namelist = new String[(int) file.length()];
                Integer i = 0;
                while (scanner.hasNext()){
                    this.namelist[i] = scanner.nextLine().split(" ")[0];
                    i++;
                }
            }
            catch (FileNotFoundException e){
                System.out.println(e);
            }
            catch (NullPointerException e){
                System.out.println(e);
            }

        });

        generate.setOnAction((ActionEvent event) -> {
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

        });

        mainPane.getChildren().addAll(titel,contentholder);



        Scene main = new Scene(mainPane, 1280,600);
        primaryStage.setScene(main);
        primaryStage.show();
    }

    public void writeFile(vraag[] vraaglijst, Integer option) throws IOException{
        String pathway = "C:/Users/julian/IdeaProjects/bstopdracht3/bstopopd/src/";
        for (Integer i = 0; i < option; i++){
            vraaglijst[i] = new vraag("1l", "3l");
            for (String naam : this.namelist){
                File outfile = new File(pathway+"AAtest_"+naam+".txt");
                FileWriter fw = new FileWriter(outfile, true);
                PrintWriter pw = new PrintWriter(fw);
                vraaglijst[i].generatequestions();
                pw.println((i+1)+". "+ vraaglijst[i].getVraag() + "\n");
                String format = "";
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


                System.out.println(i.toString()+ vraaglijst[i].getVraag());
                pw.close();

            }

        }
    }
}
