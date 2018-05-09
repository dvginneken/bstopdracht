package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
        }
        final FileChooser fileChooser = new FileChooser();
        Button inputfile = new Button("selecteer bestand");
        Text filename = new Text();
        left.getChildren().addAll(inputfile, filename);
        inputfile.setOnAction((ActionEvent event) -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                filename.setText("Geselecteerd: "+file.getName());
            }
        });

        mainPane.getChildren().addAll(titel,contentholder);



        Scene main = new Scene(mainPane, 1280,600);
        primaryStage.setScene(main);
        primaryStage.show();
    }
}
