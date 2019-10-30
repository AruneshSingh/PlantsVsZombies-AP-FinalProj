import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/loadingScreen.fxml"));
        Image newPlayerPlankImage = new Image(getClass().getResourceAsStream("images/newPlayerPlank.png"));
        Image existingPlayerPlankImage = new Image(getClass().getResourceAsStream("images/existingPlayerPlank.png"))
        Image hoverNewPlayerPlankImage = new Image(getClass().getResourceAsStream("Images/existing"))


        Button button1 = new Button();
        button1.setGraphic(new ImageView(newPlayerPlankImage));
        button1.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {

            }
        });
        button1.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {

            }
        });

        Button button2 = new Button();
        button2.setGraphic(new ImageView(existingPlayerPlankImage));
        button2.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {

            }
        });

        button2.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {

            }
        });



        primaryStage.setTitle("Plants Vs. Zombies");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
