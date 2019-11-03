import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class modeSelectionController {
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
        Parent root = FXMLLoader.load(getClass().getResource("FXML/levelSelection.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void selectNormalMode(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/levelSelection.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void exitButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
