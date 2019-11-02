import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.util.HashSet;
import java.util.Set;


public class mainYardController {
    private Image clickedAndDragged;
    private  ImageView shovelPane;
    private boolean shovelActive;
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
        if(!placedPlants.contains(paneElement.toString())&& shovelActive != true) {
            switch (paneElement.getId()) {
                case "sunflower":
                    clickedAndDragged = (new Image("images/plants/sunflower.gif"));
                    break;
                case "peashooter":
                    clickedAndDragged = (new Image("images/plants/peashooterPlant.gif"));
                    break;
                case "snowPeaShooter":
                    clickedAndDragged = (new Image("images/plants/snowPeaPlant.gif"));
                    break;
                case "walnut":
                    clickedAndDragged = (new Image("images/plants/walnutFullLife.gif"));
                    break;
                case "cherrybomb":
                    clickedAndDragged = (new Image("images/plants/cherryBombPlant.gif"));
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
        else if(shovelActive==true && placedPlants.contains(paneElement.toString())) {
            paneElement.setImage(null);
            placedPlants.remove(paneElement.getId());
            shovelPane.setEffect(null);
            shovelActive = false;
//            System.out.println("Shoveled");
        }
//        System.out.println("Ran "+ placedPlants.contains(paneElement.getId()));
    }

    public void shovelAction(MouseEvent mouseEvent) {
        shovelPane = (ImageView) mouseEvent.getSource();
        if(shovelActive){
            shovelPane.setEffect(null);
            shovelActive = false;
        }
        else {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.5);
            shovelPane.setEffect(colorAdjust);
            shovelActive = true;
        }

//        System.out.println(shovelActive);
    }
}