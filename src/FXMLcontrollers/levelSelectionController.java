package FXMLcontrollers;

import java.io.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class levelSelectionController {
    private boolean selectedButton = false;
    private ImageView selectedPane;
    public void levelHoverIn(MouseEvent mouseEvent){
        if(!selectedButton) {
            ImageView button = (ImageView) mouseEvent.getSource();
            button.setImage(new Image("images/lv" + button.getId() + "Colour.png"));
        }
    }
    public void levelHoverOut(MouseEvent mouseEvent){
        if(!selectedButton) {
            ImageView button = (ImageView) mouseEvent.getSource();
            button.setImage(new Image("images/lv" + button.getId() + "BW.png"));
        }
    }
    public void levelSelection(MouseEvent mouseEvent) throws IOException {
        selectedButton = true;
        ImageView button = (ImageView) mouseEvent.getSource();
        if(selectedPane!=null) {
            selectedPane.setImage(new Image("images/lv"+selectedPane.getId()+"BW.png"));
        }
        selectedPane = button;
        button.setImage(new Image("images/lv"+button.getId()+"Colour.png"));
    }
    public void startHoverIn(MouseEvent mouseEvent){
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/startPlankSelected.png"));
    }
    public void startHoverOut(MouseEvent mouseEvent){
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/startPlank.png"));
    }
    public void startClicked(MouseEvent mouseEvent) throws  IOException {
        if(selectedButton) {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            File f = new File("src/gameFiles/currentUser.txt");
            String name;
            f.createNewFile();
            List<String> lines = Files.readAllLines(Paths.get("src/gameFiles/currentUser.txt"), StandardCharsets.UTF_8);
            System.out.println(selectedPane.getId());
            name = lines.get(0);
            if(lines.size()==2) {
                lines.add(selectedPane.getId());
            }
            else {
                lines.set(2, selectedPane.getId());
            }
            System.out.println(lines);
            Files.write(Paths.get("src/gameFiles/currentUser.txt"), lines, StandardCharsets.UTF_8);
            Files.write(Paths.get("src/savedFiles/"+ name +".txt"), lines, StandardCharsets.UTF_8);
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/mainYard.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    public void exitButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
