package src;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.plaf.LabelUI;
import javax.swing.plaf.PanelUI;
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
    Button naar_beginscherm = new Button("Stoppen");
    Label filename = new Label("Geselecteerd: ");
    Label filename2 = new Label("Geselecteerd: ");
    Label previewLabel = new Label("");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        VBox mainPane = new VBox();
        mainPane.setPadding(new Insets(100,300,100,300));
        Text titel = new Text("Genereer toets");
        HBox contentholder = new HBox();
        VBox left = new VBox();
        contentholder.getChildren().addAll(left, buildright());
        Text hoeveelheid = new Text("Hoeveel vragen:\n");

        ToggleGroup group = new ToggleGroup();
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
        FileChooser fileChooser = new FileChooser();
        Button inputfile = new Button("selecteer bestand");

        Button generate = new Button("genereer toets!");

        left.getChildren().addAll(inputfile, filename, generate, naar_beginscherm);

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
                filename2.setText("Geselecteerd: "+file.getName());
                String previewString = "Preview:\n";
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

        generate.setOnAction((ActionEvent event) -> {
            Text create_text = new Text("Creating files in progress..");
            left.getChildren().add(create_text);
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
            create_text.setText("Finished creating files!");
        });

        mainPane.getChildren().addAll(titel,contentholder);



        Scene main = new Scene(mainPane, 1280,600);
        primaryStage.setScene(main);
        primaryStage.show();
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

    /**
     * Empties the input file.
     * @param file
     */
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

    private VBox buildright(){
        VBox right = new VBox();
        HBox filenamebox = new HBox(1);
        filenamebox.getChildren().add(filename2);
        HBox contentbox = new HBox();
        contentbox.getChildren().add(previewLabel);
        right.getChildren().addAll(filenamebox, contentbox);
    return right;
    }
}
