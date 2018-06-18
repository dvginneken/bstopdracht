package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class startScherm extends Application {
    Button Startspel = new Button("Naar toetsscherm");
    Button genereer = new Button("Naar optiescherm");
    Button quit = new Button("Afsluiten");
    //Text title = new Text("AminozuurQuiz!");
    Label title = new Label("Aminozuurquiz!");

    public void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        VBox mainbox = new VBox();
        StackPane stackmain = new StackPane();
        HBox buttonsbox = new HBox(10);

        mainPane.setCenter(mainbox);
        stackmain.getChildren().add(title);
        mainbox.getChildren().addAll(stackmain, buttonsbox);
        buttonsbox.getChildren().addAll(Startspel,genereer,quit);

        mainPane = stylemainPane(mainPane);
        mainPane.setMargin(mainbox,new Insets(100,200,100,200));
        stylemainBox(mainbox);
        stylestackMain(stackmain);
        styleTitle();
        buttonsbox.setAlignment(Pos.CENTER);
        styleButton1(Startspel);
        styleButton1(genereer);
        styleButton1(quit);

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

    private VBox stylemainBox(VBox mainbox){
        mainbox.prefHeight(200);
        mainbox.prefWidth(100);
        mainbox.setAlignment(Pos.CENTER);
        mainbox.setStyle("-fx-background-color: #ffffff;");
        return mainbox;
    }

    private StackPane stylestackMain(StackPane stackmain){
        stackmain.prefHeight(100);
        stackmain.prefWidth(Region.USE_COMPUTED_SIZE);
        return stackmain;
    }

    private void styleTitle(){
        this.title.setPadding(new Insets(0,0,50,0));
        this.title.setAlignment(Pos.CENTER);
        this.title.setContentDisplay(ContentDisplay.CENTER);
        this.title.setFont(Font.font("open-sans", 36));
    }

    private Button styleButton1(Button button){
        button.setPadding(new Insets(8,16,8,16));
        button.setStyle("-fx-background-color: #66bec1; ");
        button.setTextFill(Color.WHITE);
        return button;
    }

}

