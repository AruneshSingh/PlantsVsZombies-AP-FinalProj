package FXMLcontrollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class modeSelectionController {
    String modeSelected = "";
    Image image;
    public void normalModeHoverIn(MouseEvent mouseEvent){
        image = new Image("images/normalZombieColour.png");
        ((ImageView)mouseEvent.getSource()).setImage(image);
    }
    public void normalModeHoverOut(MouseEvent mouseEvent){
        image = new Image("images/normalZombieBW.png");
        ((ImageView)mouseEvent.getSource()).setImage(image);
    }
    public void rabidModeHoverIn(MouseEvent mouseEvent){
        image = new Image("images/rabidZombieColour.png");
        ((ImageView)mouseEvent.getSource()).setImage(image);
    }
    public void rabidModeHoverOut(MouseEvent mouseEvent){
        image = new Image("images/rabidZombieBW.png");
        ((ImageView)mouseEvent.getSource()).setImage(image);
    }
    public void selectRabidMode(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/levelSelection.fxml"));
        modeSelected = "Rabid";
        saveMode();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void selectNormalMode(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/levelSelection.fxml"));
        modeSelected = "Normal";
        saveMode();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void saveMode() throws IOException {
        File f = new File("src/gameFiles/currentUser.txt");
        f.createNewFile();
        List<String> lines = Files.readAllLines(Paths.get("src/gameFiles/currentUser.txt"), StandardCharsets.UTF_8);
        if(lines.size()==1) {
            lines.add(modeSelected);
        }
        else {
            lines.set(1,modeSelected);
        }
        System.out.println(lines);
        Files.write(Paths.get("src/gameFiles/currentUser.txt"), lines, StandardCharsets.UTF_8);
    }
    public void exitButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
