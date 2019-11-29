package FXMLcontrollers;

import classes.*;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import threads.Pea;
import classes.Zombies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class mainYardController implements Initializable  {
    private int level;
    private String mode;
    private Image clickedAndDragged;
    private ImageView shovelPane, lawnmover;
    private int peaX,peaY,iceX,iceY;


    private Level current;
    private ArrayList<ArrayList<Characters>> grid;
    private String type;
    private ArrayList<Plants> plantListTemp;  //plant list and zombie list will have plants and zombies from the row to pass onto lawnmover and cherrybombs
    private ArrayList<Zombies> zombieListTemp;

    @FXML
    public ImageView zombie1, zombie2, zombie3, token, head;

    public Map<ImageView,Zombies> Zombie = new HashMap<ImageView,Zombies>();
    public ArrayList<ArrayList<ImageView>> zombiesInRow = new ArrayList<ArrayList<ImageView>>();

    @FXML
    public Label tokenCounterLabel;

    private boolean shovelActive,speakerStatus,play,peaShooterSelected,iceShooterSelected;
    private Set<String> placedPlants;
    private Map<String,ImageView> peas;
    private Map<ImageView, TranslateTransition> transitionMap;

    public mainYardController(){
        readFile();
        current = new Level(level, mode);
        grid = current.getGrid();
        placedPlants = new HashSet<String>();
        speakerStatus = false;
        peas = new HashMap<String,ImageView>();
        transitionMap = new HashMap<ImageView, TranslateTransition>();

        zombiesInRow = new ArrayList<ArrayList<ImageView>>();
        for(int i=0;i<6;i++){
            zombiesInRow.add(new ArrayList<ImageView>());
        }

        generateZombies(level);

    }

    public void generateZombies(int level)
    {
        Zombies zombie1 = new Skinny();
        zombiesInRow.get(0).add(this.zombie1);
        Zombies zombie2 = new Conehead();
        zombiesInRow.get(1).add(this.zombie2);
        Zombies zombie3 = new Skinny();
        zombiesInRow.get(2).add(this.zombie3);
        Zombie.put(this.zombie1,zombie1);
        Zombie.put(this.zombie2,zombie2);
        Zombie.put(this.zombie3,zombie3);
//        switch (level){
//
//        }
    }
    public void readFile() {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("currentUser.txt"));

            String temp = in.readLine();
            temp = in.readLine();
            this.mode = temp;
            temp = in.readLine();
            this.level = Integer.parseInt(temp);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }





    //Functions Needed by SCENE BUILDER
    // --------------------------------------------------------


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
                type = paneElement.getId();
                switch (type) {
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
            int col = ((GridPane)paneElement.getParent()).getColumnIndex(paneElement);
            int row = ((GridPane)paneElement.getParent()).getRowIndex(paneElement);
            switch (type) {
                case "sunflower":
                    current.addToGrid(grid, new Sunflower() ,row-1, col);
//                    System.out.println(grid.get(row-1).get(col).getClass() + " " + (row-1) + col);
                    break;
                case "peashooter":
                    current.addToGrid(grid, new PeaShooter() ,row-1, col);
                    break;
                case "snowPeaShooter":
                    current.addToGrid(grid, new SnowPeaShooter() ,row-1, col);
                    break;
                case "walnut":
                    current.addToGrid(grid, new Wallnut() ,row-1, col);
                    break;
                case "cherrybomb":
                    current.addToGrid(grid, new CherryBomb() ,row-1, col);
                    break;
            }

            clickedAndDragged = null;
            placedPlants.add(paneElement.toString());
            peas.put(paneElement.toString(),shotView);







            if(peaShooterSelected == true)
            {
                peaX = GridPane.getColumnIndex(paneElement);
                peaY = GridPane.getRowIndex(paneElement);
                Image image = new Image("images/plants/pea.png");
                AnimationTimer PeaThread = new Pea(peaX,peaY,10,(GridPane) paneElement.getParent(),image,zombiesInRow.get(peaY-1),transitionMap);
                PeaThread.start();
                peaShooterSelected = false;
            }
            if(iceShooterSelected == true) {
                iceX = GridPane.getColumnIndex(paneElement);
                iceY = GridPane.getRowIndex(paneElement);
//                shotView.setId("20");
                Image image = new Image("images/plants/snowPea.png");
                AnimationTimer PeaThread = new Pea(iceX,iceY,20,(GridPane) paneElement.getParent(),image,zombiesInRow.get(iceY-1),transitionMap);
                PeaThread.start();
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

    public void tokenCount(MouseEvent mouseEvent) {
        tokenCounterLabel.setText("25");
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
        transitionMap.put(temp,translateTransition);
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
            temp.setImage(null);
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
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/levelSelection.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/mainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveZombies(zombie1);
        moveZombies(zombie2);
        moveZombies(zombie3);
        moveSun(token);
        moveHead(head);
    }
}