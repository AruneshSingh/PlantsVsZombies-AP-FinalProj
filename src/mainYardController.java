import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;


public class mainYardController {
    private Image clickedAndDragged;
    private Set<String> placedPlants;
    public mainYardController(){
        placedPlants = new HashSet<String>();
    }
    public void dragEnteredPane(MouseEvent mouseEvent) {
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        if(paneElement.getImage() == null && clickedAndDragged != null){
            paneElement.setImage(clickedAndDragged);
//            System.out.println("ifEnteredPassed");
        }
//        System.out.println("RanEntered");
    }

    public void dragExitPane(MouseEvent mouseEvent) {
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        if(clickedAndDragged != null && !placedPlants.contains(paneElement.toString())){
            paneElement.setImage(null);
            System.out.println("ifExitPassed");
        }
//        System.out.println("RanExit");
        System.out.println(paneElement.toString());
    }
    public void mouseClicked(MouseEvent mouseEvent) {
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        if(!placedPlants.contains(paneElement.toString())) {
            switch (paneElement.getId()) {
                case "sunflower":
                    clickedAndDragged = (new Image("images/plants/sunflowerPlant.png"));
                    break;
                case "peashooter":
                    clickedAndDragged = (new Image("images/plants/peashooterPlant.png"));
                    break;
                case "snowPeaShooter":
                    clickedAndDragged = (new Image("images/plants/snowPeaPlant.png"));
                    break;
                case "walnut":
                    clickedAndDragged = (new Image("images/plants/walnutPlant.png"));
                    break;
                case "cherrybomb":
                    clickedAndDragged = (new Image("images/plants/cherryBombPlant.png"));
                    break;

            }
        }
//        System.out.println("Clicked");
    }
    public void imagePaneDrop(MouseEvent mouseEvent) {
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        if(clickedAndDragged !=null && !placedPlants.contains(paneElement.toString())) {
            paneElement.setImage(clickedAndDragged);
            clickedAndDragged = null;
            placedPlants.add(paneElement.toString());
//            System.out.println("Dropped");
        }
//        System.out.println("Ran");
    }
//    public void
}