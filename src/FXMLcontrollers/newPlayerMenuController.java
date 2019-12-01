package FXMLcontrollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class newPlayerMenuController {
    String nameField;
    public void textEntered(KeyEvent keyEvent)  {
        TextField field = (TextField) keyEvent.getSource();
        nameField = field.getText();
    }
    public void hoverOverStartButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/startPlankSelected.png"));
    }
    public void hoverOutStartButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/startPlank.png"));
    }
    public void startButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/modeSelection.fxml"));
        File user = new File("src/gameFiles/currentUser.txt");
        user.delete();
        user.createNewFile();
        List<String> lines = Files.readAllLines(Paths.get("src/gameFiles/currentUser.txt"), StandardCharsets.UTF_8);
        if(lines.size()==0) {
            lines.add(nameField);
        }
        else {
            lines.set(0,nameField);
        }
        System.out.println(lines);
        Files.write(Paths.get("src/gameFiles/currentUser.txt"), lines, StandardCharsets.UTF_8);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void exitButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
