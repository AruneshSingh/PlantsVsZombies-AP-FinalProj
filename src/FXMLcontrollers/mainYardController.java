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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import threads.*;
import classes.Zombies;
import threads.Sun;
import threads.SunFlowerThread;
import threads.ZombiePlantCollisionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.*;


public class mainYardController implements Initializable, Serializable {
    private int level;
    private String name;
    private String mode;
    private Level current;
    private ArrayList<ArrayList<Characters>> grid;
    private String type;
    private int[][] zombieArr;


    private Image clickedAndDragged;
    private ImageView shovelPane, lawnmover;
    private int peaX,peaY,iceX,iceY;


    @FXML
    public ImageView head;
    public Pane mainYardPane;
    public Label tokenCounterLabel, levelLabel, playerLabel;
    public ImageView L1,L2,L3,L4,L5;

    private Map<ImageView,Zombies> Zombie = new HashMap<ImageView,Zombies>();
    private ArrayList<ArrayList<ImageView>> zombiesInRow = new ArrayList<ArrayList<ImageView>>();


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
        zombieArr = new int[][]{{10,8,2,0},{10,5,4,1},{4,8,4,4},{4,6,5,5},{0,6,10,4}};

        zombiesInRow = new ArrayList<ArrayList<ImageView>>();
        for(int i=0;i<5;i++){
            zombiesInRow.add(new ArrayList<ImageView>());
        }

    }

    public void generateZombies(int level)
    {
        int[] yPos = new int[] {80,190,310,430,540};
        int[] seedPos = new int[] {100,500,1000,200,800};
        Random rand = new Random();
        ImageView temp;
        Zombies tempZombie;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                int random = rand.nextInt(20);
                int random2 = rand.nextInt(5);
                temp = new ImageView();
                temp.setX(seedPos[random2] + 1200 + j*200);
                temp.setY(yPos[i]);
                temp.setScaleX(0.8);
                temp.setScaleY(0.8);
                temp.setScaleZ(0.8);

                if(random < zombieArr[level-1][0]){
                    temp.setImage(new Image("images/zomies/normalZombie.gif"));
                    tempZombie = new Skinny();
                }
                else if (zombieArr[level-1][0] < random && random < zombieArr[level-1][0] + zombieArr[level-1][1]){
                    temp.setImage(new Image("images/zomies/coneZombie.png"));
                    tempZombie = new Conehead();
                }
                else if (zombieArr[level-1][0] + zombieArr[level-1][1] < random && random < zombieArr[level-1][0] + zombieArr[level-1][1] + zombieArr[level-1][2]){
                    temp.setImage(new Image("images/zomies/footballZombie.gif"));
                    tempZombie = new Football();
                }
                //TODO:Change this to a new zombie type. rn it's normalZombie.gif
                else {
                    temp.setImage(new Image("images/zomies/footballZombie.gif"));
                    tempZombie = new Skinny2();
                }
                if(mode.equals("Rabid")) {
                    tempZombie.setRabid(true);
                }
                else {
                    tempZombie.setRabid(false);
                }
                zombiesInRow.get(i).add(temp);
                mainYardPane.getChildren().add(temp);
                Zombie.put(temp,tempZombie);
                moveZombies(temp, tempZombie);
            }
        }

    }

    public void moveZombies(ImageView temp, Zombies tempZombie) {
        TranslateTransition translateTransition = new TranslateTransition();
        transitionMap.put(temp,translateTransition);
        translateTransition.setDuration(Duration.millis(1000 * ((temp.getX()-200)/tempZombie.getSpeed())));
        translateTransition.setNode(temp);
        translateTransition.setToX(200-temp.getX());
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    public void readFile() {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("src/gameFiles/currentUser.txt"));

            String temp;
            temp = in.readLine();
            this.name = temp;
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
                        if(current.getTokenCounter()-50 < 0)
                            clickedAndDragged = null;
                        else {
                            clickedAndDragged = (new Image("images/plants/sunflower.gif"));
                            current.setTokenCounter(current.getTokenCounter() - 50);
                            tokenCounterLabel.setText(Integer.toString(current.getTokenCounter()));
                        }
                        break;
                    case "peashooter":
                        if(current.getTokenCounter()-100 < 0)
                            clickedAndDragged = null;
                        else {
                            clickedAndDragged = (new Image("images/plants/peashooterPlant.gif"));
                            current.setTokenCounter(current.getTokenCounter() - 100);
                            tokenCounterLabel.setText(Integer.toString(current.getTokenCounter()));
                            peaShooterSelected = true;
                        }
                        break;
                    case "snowPeaShooter":
                        if(current.getTokenCounter()-150 < 0)
                            clickedAndDragged = null;
                        else {
                            clickedAndDragged = (new Image("images/plants/snowPeaPlant.gif"));
                            current.setTokenCounter(current.getTokenCounter() - 150);
                            tokenCounterLabel.setText(Integer.toString(current.getTokenCounter()));
                            iceShooterSelected = true;
                        }
                        break;
                    case "walnut":
                        if(current.getTokenCounter()-50 < 0)
                            clickedAndDragged = null;
                        else {
                            clickedAndDragged = (new Image("images/plants/walnutFullLife.gif"));
                            current.setTokenCounter(current.getTokenCounter() - 50);
                            tokenCounterLabel.setText(Integer.toString(current.getTokenCounter()));
                        }
                        break;
                    case "cherrybomb":
                        if(current.getTokenCounter()-150 < 0)
                            clickedAndDragged = null;
                        else {
                            clickedAndDragged = (new Image("images/plants/cherryBombPlant.gif"));
                            current.setTokenCounter(current.getTokenCounter() - 150);
                            tokenCounterLabel.setText(Integer.toString(current.getTokenCounter()));
                        }
                        break;
                }
            }
        }
        catch (ClassCastException e) {
        }
    }
    public void imagePaneDrop(MouseEvent mouseEvent) {
        ImageView paneElement = (ImageView) mouseEvent.getSource();
        ImageView shotView = new ImageView();
        Plants newPlant = new Sunflower();
        if(clickedAndDragged !=null && !placedPlants.contains(paneElement.toString())) {
            paneElement.setImage(clickedAndDragged);
            int col = ((GridPane)paneElement.getParent()).getColumnIndex(paneElement);
            int row = ((GridPane)paneElement.getParent()).getRowIndex(paneElement);
            int xVal = (int)paneElement.getLayoutX();
            int yVal = (int)paneElement.getLayoutY();
            switch (type) {
                case "sunflower":
                    newPlant = new Sunflower();
                    current.addToGrid(grid, newPlant ,row-1, col);

                    AnimationTimer sunflower = new SunFlowerThread(mainYardPane, current, tokenCounterLabel, xVal, yVal);
                    sunflower.start();
//                    System.out.println(grid.get(row-1).get(col).getClass() + " " + (row-1) + col);
                    break;
                case "peashooter":
                    newPlant =  new PeaShooter();
                    paneElement.setId("Peashooter");
                    current.addToGrid(grid, newPlant,row-1, col);
                    break;
                case "snowPeaShooter":
                    newPlant = new SnowPeaShooter();
                    paneElement.setId("Peashooter");
                    current.addToGrid(grid, newPlant,row-1, col);
                    break;
                case "walnut":
                    newPlant = new Wallnut();
                    current.addToGrid(grid, newPlant,row-1, col);
                    break;
                case "cherrybomb":
                    newPlant = new CherryBomb();
                    paneElement.setId("cherrybomb");
                    current.addToGrid(grid, newPlant,row-1, col);
                    break;
            }

            clickedAndDragged = null;
            placedPlants.add(paneElement.toString());
            AnimationTimer collisionCheck = new ZombiePlantCollisionHandler(paneElement,zombiesInRow.get(row-1),transitionMap,newPlant,Zombie,placedPlants);
            collisionCheck.start();
            peas.put(paneElement.toString(),shotView);


            System.out.println(zombiesInRow);

            if(peaShooterSelected == true)
            {
                peaX = GridPane.getColumnIndex(paneElement);
                peaY = GridPane.getRowIndex(paneElement);
                Image image = new Image("images/plants/pea.png");
                AnimationTimer PeaThread = new Pea(peaX,peaY,10,(GridPane) paneElement.getParent(),image,zombiesInRow.get(peaY-1),transitionMap,Zombie,paneElement,placedPlants);
                PeaThread.start();
                peaShooterSelected = false;
            }
            if(iceShooterSelected == true) {
                iceX = GridPane.getColumnIndex(paneElement);
                iceY = GridPane.getRowIndex(paneElement);
//                shotView.setId("20");
                Image image = new Image("images/plants/snowPea.png");
                AnimationTimer PeaThread = new Pea(iceX,iceY,20,(GridPane) paneElement.getParent(),image,zombiesInRow.get(iceY-1),transitionMap,Zombie,paneElement,placedPlants);
                PeaThread.start();
                iceShooterSelected = false;
            }

//            System.out.println("Dropped");
        }
        else if(shovelActive==true && placedPlants.contains(paneElement.toString())) {
            paneElement.setImage(null);

            stopPea(peas.get(paneElement.toString()));

            placedPlants.remove(paneElement.toString());
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

    public void restartGame(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/mainYard.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
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

    //786, 411
    public void moveHead(ImageView temp) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(700000));
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
        moveHead(head);
        new Lawnmower(zombiesInRow.get(0),L1,Zombie).start();
        new Lawnmower(zombiesInRow.get(1),L2,Zombie).start();
        new Lawnmower(zombiesInRow.get(2),L3,Zombie).start();
        new Lawnmower(zombiesInRow.get(3),L4,Zombie).start();
        new Lawnmower(zombiesInRow.get(4),L5,Zombie).start();

        levelLabel.setText("LV: " + level);
        System.out.println(level);
        System.out.println(name);
        playerLabel.setText(name);


        AnimationTimer sun = new Sun(mainYardPane, current, tokenCounterLabel);
        sun.start();

        generateZombies(level);

    }
}