import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class mainYardController {

    private Image clickedAndDragged;
    private  ImageView shovelPane, lawnmover, zombie;
    private boolean shovelActive,speakerStatus,play;
    private Set<String> placedPlants;
    public mainYardController(){
        placedPlants = new HashSet<String>();
        speakerStatus = true;
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

    public void moveLawnmover(MouseEvent mouseEvent) {
        lawnmover = (ImageView) mouseEvent.getSource();
        TranslateTransition translateTransition = new TranslateTransition();

        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(lawnmover);
        translateTransition.setByX(1500);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    public void moveZombies(MouseEvent mouseEvent) {
        zombie = (ImageView) mouseEvent.getSource();
        TranslateTransition translateTransition = new TranslateTransition();

        translateTransition.setDuration(Duration.millis(20000));
        translateTransition.setNode(zombie);
        translateTransition.setToX(-(zombie.getLayoutX() - 230));
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }
    public void hoverExitIn(MouseEvent mouseEvent){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.5);
        ((ImageView)mouseEvent.getSource()).setEffect(colorAdjust);
    }
    public void hoverExitOut(MouseEvent mouseEvent){
        ((ImageView)mouseEvent.getSource()).setEffect(null);
    }
    public void clickExit(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/levelSelection.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void hoverSpeakerIn(MouseEvent mouseEvent) {
        ImageView speaker = (ImageView) mouseEvent.getSource();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.5);
        speaker.setEffect(colorAdjust);
    }
    public void hoverSpeakerOut(MouseEvent mouseEvent) {
        ImageView speaker = (ImageView) mouseEvent.getSource();
        speaker.setEffect(null);
    }
    public void pressSpeaker(MouseEvent mouseEvent) {
        ImageView speaker = (ImageView) mouseEvent.getSource();

        if(speakerStatus) {
            speaker.setImage(new Image("images/soundOff.png"));
        }
        else {
            speaker.setImage(new Image("images/soundOn.png"));
        }
        speakerStatus = !speakerStatus;

    }
    public void playPause(MouseEvent mouseEvent){
        ImageView button = (ImageView) mouseEvent.getSource();

        if(play) {
            button.setImage(new Image("images/pause.png"));
        }
        else {
            button.setImage(new Image("images/play.png"));
        }
        play = !play;
    }


}