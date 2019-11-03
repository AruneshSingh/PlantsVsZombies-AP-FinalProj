import javafx.animation.Timeline;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class mainYardController implements Initializable {

    private Image clickedAndDragged;
    private  ImageView shovelPane, lawnmover;
    private int peaX,peaY,iceX,iceY;

    @FXML
    public ImageView zombie, zombie2, zombie3, token, head;

    private boolean shovelActive,speakerStatus,play,peaShooterSelected,iceShooterSelected;
    private Set<String> placedPlants;
    private Map<String,ImageView> peas;
    private Map<ImageView, TranslateTransition> transitionMap;
    public mainYardController(){
        placedPlants = new HashSet<String>();
        speakerStatus = false;
        peas = new HashMap<String,ImageView>();
        transitionMap = new HashMap<ImageView, TranslateTransition>();
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
//            System.out.println("ifExitPassed");
        }
//        System.out.println("RanExit");
//        System.out.println(paneElement.toString());
    }
    public void mouseClicked(MouseEvent mouseEvent) {
        try {
            ImageView paneElement = (ImageView) mouseEvent.getSource();
            if (!placedPlants.contains(paneElement.toString()) && shovelActive != true) {
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
        }
        catch (ClassCastException e){

        }
//        System.out.println("Clicked");
    }
    public void imagePaneDrop(MouseEvent mouseEvent) {
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        ImageView shotView = new ImageView();
        if(clickedAndDragged !=null && !placedPlants.contains(paneElement.toString())) {
            paneElement.setImage(clickedAndDragged);
            clickedAndDragged = null;
            placedPlants.add(paneElement.toString());
            peas.put(paneElement.toString(),shotView);
            if(peaShooterSelected == true)
            {
                shotView.setImage(new Image("images/plants/pea.png"));
                peaX = GridPane.getRowIndex(paneElement);
                peaY = GridPane.getColumnIndex(paneElement);
//                System.out.println(peaX);
//                System.out.println(peaY);

                ((GridPane) paneElement.getParent()).add(shotView,peaY+1,peaX);
                movePea(shotView);
                peaShooterSelected = false;
            }
            if(iceShooterSelected == true) {
                iceX = GridPane.getRowIndex(paneElement);
                iceY = GridPane.getColumnIndex(paneElement);
                shotView.setImage(new Image("images/plants/snowPea.png"));

                ((GridPane) paneElement.getParent()).add(shotView,iceY+1,iceX);
                movePea(shotView);
                iceShooterSelected = false;
            }

//            System.out.println("Dropped");
        }
        else if(shovelActive==true && placedPlants.contains(paneElement.toString())) {
            paneElement.setImage(null);

            stopPea(peas.get(paneElement.toString()));

            placedPlants.remove(paneElement.getId());
            shovelPane.setEffect(null);
            shovelActive = false;
        }
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

    //786, 411
    public void moveHead(ImageView temp) {
        TranslateTransition translateTransition = new TranslateTransition();

        translateTransition.setDuration(Duration.millis(50000));
        translateTransition.setNode(temp);
        translateTransition.setToX(-(temp.getLayoutX() - 411));
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    public void movePea(ImageView temp) {
        TranslateTransition translateTransition = new TranslateTransition();
        transitionMap.put(temp,translateTransition);
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(temp);
        translateTransition.setToX(temp.getLayoutX() + 1300);
        translateTransition.setCycleCount(Timeline.INDEFINITE);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }
    public void stopPea(ImageView temp) {
        try {
            TranslateTransition translateTransition = transitionMap.get(temp);
            translateTransition.stop();
        }
        catch (NullPointerException e) {
        }
    }


    public void moveSun(ImageView temp) {
        TranslateTransition translateTransition = new TranslateTransition();

        translateTransition.setDuration(Duration.millis(10000));
        translateTransition.setNode(temp);
        translateTransition.setToY(660);
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
        moveHead(head);
    }
}