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
import java.util.Scanner;

public class toetsScherm extends Application {

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
        left.getChildren().addAll(inputfile, filename);

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
                while (scanner.hasNext()){
                    System.out.println(scanner.nextLine());
                }
            }
            catch (FileNotFoundException e){
                System.out.println(e);
            }
            catch (NullPointerException e){
                System.out.println(e);
            }

        });

        mainPane.getChildren().addAll(titel,contentholder);



        Scene main = new Scene(mainPane, 1280,600);
        primaryStage.setScene(main);
        primaryStage.show();
    }
}
