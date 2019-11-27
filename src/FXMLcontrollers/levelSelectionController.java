package FXMLcontrollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
            Parent root = FXMLLoader.load(getClass().getResource("FXML/mainYard.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    public void exitButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
