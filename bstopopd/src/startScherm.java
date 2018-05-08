package bstopdracht;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class startScherm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Rectangle vierkant = new Rectangle(800,400);
        vierkant.setX(200);
        vierkant.setY(200);
        vierkant.setFill(Color.WHITE);
        Rectangle vierkant2 = new Rectangle(1280,600);
        vierkant2.setFill(Color.AQUA);

        Text title = new Text("AminozuurQuizzz!");
        title.setFont(Font.font("open-sans", 36));
        BorderPane mainPane = new BorderPane();
        mainPane.getChildren().add(vierkant2);

        StackPane titlebox = new StackPane();
        titlebox.getChildren().add(title);

        StackPane mainbox = new StackPane();
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        Button Startspel = new Button("Start quiz");
        Button genereer = new Button("genereer quizzes");
        Button quit = new Button("Afsluiten");
        mainbox.getChildren().addAll(vierkant, buttons);
        buttons.getChildren().addAll(Startspel, genereer, quit);

        Startspel.bind()
        mainPane.setTop(titlebox);
        mainPane.setCenter(mainbox);



        Scene main = new Scene(mainPane, 1280,600);
        primaryStage.setScene(main);
        primaryStage.show();

    }

}
