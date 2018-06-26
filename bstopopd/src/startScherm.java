package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
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
    Label title = new Label("Welkom bij de Aminozuurquiz!");

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
        mainPane.setMargin(mainbox,new Insets(100,150,100,150));
        stylemainBox(mainbox);
        stylestackMain(stackmain);
        styleTitle();
        buttonsbox.setAlignment(Pos.CENTER);
        styleButton1(Startspel);
        styleButton1(genereer);
        styleButton2(quit);

        Scene main = new Scene(mainPane, 1200,800);
        primaryStage.setScene(main);
        primaryStage.show();
    }




    private BorderPane stylemainPane(BorderPane mainPane){
        mainPane.prefHeight(800);
        mainPane.prefWidth(1100);
        mainPane.setStyle("-fx-background-color: #9ff9a5;");
        return mainPane;
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
            //System.out.println("cannot apply blur to object.");
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
            //System.out.println("cannot apply blur to object.");
        }
        return blurredbox;
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

    public void close(Stage primaryStage){
        primaryStage.close();
    }
}

