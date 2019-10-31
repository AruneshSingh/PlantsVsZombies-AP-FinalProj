import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/playerSelection.fxml"));
//        Image newPlayerPlankImage = new Image(getClass().getResourceAsStream("images/newPlayerPlank.png"));
//        Image existingPlayerPlankImage = new Image(getClass().getResourceAsStream("images/existingPlayerPlank.png"));
//        Image hoverNewPlayerPlankImage = new Image(getClass().getResourceAsStream("images/newPlayerPlankSelected.png"));
//        Image hoverExistingPlayerPlankImage = new Image(getClass().getResourceAsStream("images/existingPlayerPlankSelected.png"));
//        Image backgroundImage = new Image(getClass().getResourceAsStream("images/playerSelection.png"));
//
//        System.out.println(newPlayerPlankImage.toString());
//        Button button1 = new Button();
//        button1.setGraphic(new ImageView(newPlayerPlankImage));
//        button1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                button1.setGraphic(new ImageView(hoverNewPlayerPlankImage));
//            }
//        });
//
//        button1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                button1.setGraphic(new ImageView(newPlayerPlankImage));
//            }
//        });
//
//        button1.setLayoutX(0);
//        button1.setLayoutY(0);
//
//        Button button2 = new Button("",new ImageView(existingPlayerPlankImage));
////        button2.setGraphic();
//        button2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                button2.setGraphic(new ImageView(hoverExistingPlayerPlankImage));
//            }
//        });
//        button2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                button2.setGraphic(new ImageView(existingPlayerPlankImage));
//            }
//        });
////        button2.setOnDragEntered(new EventHandler<DragEvent>() {
////            @Override
////            public void handle(DragEvent dragEvent) {
////            }
////        });
////        button2.setOnDragExited(new EventHandler<DragEvent>() {
////            @Override
////            public void handle(DragEvent dragEvent) {
////                button2.setGraphic(new ImageView(existingPlayerPlankImage));
////            }
////        });
//        button2.setTranslateX(800);
//        button2.setTranslateY(360);
//        VBox layout1 = new VBox();
//        layout1.getChildren().addAll(button1,button2);
//        layout1.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT)));
//        scene1 = new Scene(layout1,1280,720);
        primaryStage.setTitle("Plants Vs. Zombies");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
