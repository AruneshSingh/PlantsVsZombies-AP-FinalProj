import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


public class mainYardController implements Initializable {

    private Image clickedAndDragged;
    private  ImageView shovelPane, lawnmover;
    private double peaX,iceX;

    @FXML
    public ImageView zombie, zombie2, zombie3, token;

    private boolean shovelActive,speakerStatus,play,peaShooterSelected,iceShooterSelected;
    private Set<String> placedPlants;
    public mainYardController(){
        placedPlants = new HashSet<String>();
        speakerStatus = false;
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
                    peaShooterSelected = true;
                    break;
                case "snowPeaShooter":
                    clickedAndDragged = (new Image("images/plants/snowPeaPlant.gif"));
                    iceShooterSelected = true;
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
            if(peaShooterSelected == true)
            {
                peaX = paneElement.getLayoutX();
                peaShooterSelected = false;
            }
            if(iceShooterSelected == true) {
                iceX = paneElement.getLayoutX();
                iceShooterSelected = false;
            }

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

    public void moveZombies(ImageView temp) {
        TranslateTransition translateTransition = new TranslateTransition();

        translateTransition.setDuration(Duration.millis(20000));
        translateTransition.setNode(temp);
        translateTransition.setToX(-(temp.getLayoutX() - 230));
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    public void moveSun(ImageView temp) {
        TranslateTransition translateTransition = new TranslateTransition();

        translateTransition.setDuration(Duration.millis(10000));
        translateTransition.setNode(temp);
        translateTransition.setToY( 560);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    public void hoverIconIn(MouseEvent mouseEvent){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.5);
        ((ImageView)mouseEvent.getSource()).setEffect(colorAdjust);
    }
    public void hoverIconOut(MouseEvent mouseEvent){
        ((ImageView)mouseEvent.getSource()).setEffect(null);
    }
    public void clickExit(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/levelSelection.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void hoverPlayIn(MouseEvent mouseEvent) {
        ImageView Play = (ImageView) mouseEvent.getSource();
        ColorAdjust colorAdjust;
        if(Play.getEffect()==null) {
            colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.5);
        }
        else{
            colorAdjust = (ColorAdjust) Play.getEffect();
            colorAdjust.setHue(50);
        }
        Play.setEffect(colorAdjust);
    }
    public void hoverPlayOut(MouseEvent mouseEvent) {
        if(!play) {
            ImageView speaker = (ImageView) mouseEvent.getSource();
            speaker.setEffect(null);
        }
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
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(50);
            button.setEffect(colorAdjust);
            button.setImage(new Image("images/play.png"));
        }
        play = !play;
    }
    public void exitButtonClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXML/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveZombies(zombie);
        moveZombies(zombie2);
        moveZombies(zombie3);
        moveSun(token);
    }
}