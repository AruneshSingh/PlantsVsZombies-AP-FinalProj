package threads;

import classes.Zombies;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pea extends AnimationTimer {
    int X,Y,damage,counter;
    Image image;
    GridPane pane;
    ArrayList<ImageView> zombiesInRow;
    private Map<ImageView, TranslateTransition> transitionMap;
    Map<ImageView,Zombies> zombieObject;
    ImageView plantImageView;
    public Pea(int X, int Y, int damage, GridPane pane, Image image, ArrayList<ImageView> zombiesInRow,Map<ImageView, TranslateTransition> transitionMap,Map<ImageView,Zombies> zombieObject, ImageView plantImageView){
        this.X = X;
        this.Y = Y;
        this.damage = damage;
        this.pane = pane;
        this.image = image;
        this.zombiesInRow = zombiesInRow;
        this.transitionMap = transitionMap;
        this.zombieObject = zombieObject;
        this.plantImageView = plantImageView;
        counter = 0;
    }

    @Override
    public void handle(long l) {
        if(plantImageView.getImage()==null){
            stop();
            return;
        }
        if(zombiesInRow.size()!=0&&counter%70==0) {
            counter = 0;
            ImageView view = new ImageView();
            view.setImage(image);
            view.setId(Integer.toString(damage));
            pane.add(view, X + 1, Y);
            TranslateTransition translateTransition = new TranslateTransition();
            transitionMap.put(view,translateTransition);
            translateTransition.setDuration(Duration.millis(2000));
            translateTransition.setNode(view);
            translateTransition.setToX(view.getLayoutX() + 1300);
            translateTransition.setCycleCount(1);
            translateTransition.setAutoReverse(false);
            translateTransition.play();
            new PeaZombieCollisionHandler(view,zombiesInRow,zombieObject,transitionMap).start();
        }
        counter+=1;
    }
}
