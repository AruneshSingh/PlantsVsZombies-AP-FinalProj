import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.MouseEvent;

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
}
