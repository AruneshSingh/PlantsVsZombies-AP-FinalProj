import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class playerSelectionController {

    public void hoverOverNewButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/newPlayerPlankSelected.png"));
    }
    public void hoverOutNewButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/newPlayerPlank.png"));
    }
    public void hoverOverExistingButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/existingPlayerPlankSelected.png"));
    }
    public void hoverOutExistingButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/existingPlayerPlank.png"));
    }
    public void newButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/newPlayerMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
