import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class existingPlayerMenuController {
    private boolean selectedButton = false;
    private Pane selectedPane;
    public void playerHoverIn(MouseEvent mouseEvent){
        if(!selectedButton) {
            Pane button = (Pane) mouseEvent.getSource();
            ColorAdjust colorAdjust = new ColorAdjust();
            Color color = new Color(1,1,1,0.5);
            colorAdjust.setBrightness(0.5);
            button.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
    public void playerHoverOut(MouseEvent mouseEvent){
        if(!selectedButton) {
            Pane button = (Pane) mouseEvent.getSource();
            button.setBackground(null);
        }
    }
    public void playerSelection(MouseEvent mouseEvent) throws IOException {
        selectedButton = true;
        Pane button = (Pane) mouseEvent.getSource();
        if(selectedPane!=null) {
            selectedPane.setEffect(null);
        }
        selectedPane = button;
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.5);
        button.setEffect(colorAdjust);
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
