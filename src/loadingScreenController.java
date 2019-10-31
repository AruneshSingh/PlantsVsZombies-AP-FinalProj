import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.MouseEvent;

public class loadingScreenController {

    public void hoverOverNewButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/newPlayerPlankSelected.png"));
    }
    public void hoverOutNewButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/playerSelection.png"));
    }
    public void hoverOverExistingButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/existingPlayerPlankSelected.png"));
    }
    public void hoverOutExistingButton(MouseEvent mouseEvent) {
        ImageView button = (ImageView) mouseEvent.getSource();
        button.setImage(new Image("images/existingPlayerPlank.png"));
    }
}

