package threads;

import classes.Level;
import classes.SunToken;
import classes.Zombies;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Sun extends AnimationTimer {
    int counter;
    Level lv;
    Pane pane;
    Label label;

    public Sun(Pane pane, Level lv, Label label){
        counter = 0;
        this.pane = pane;
        this.lv = lv;
        this.label = label;
    }

    @Override
    public void handle(long l) {

        Random rand = new Random();

        counter += 1;
        if (counter%720 == 0){
            int r = rand.nextInt(10);
            counter = 0;
            SunToken sun = new SunToken();
            sun.setSpriteXY(300 + (r*60), 0);
            pane.getChildren().add(sun.getTokenSprite());
            sun.moveSun();
            if(sun.getTokenSprite().isVisible()){
                if(sun.isExpired()){
                    sun.getTokenSprite().setVisible(false);
                }
                else {
                    sun.getTokenSprite().setOnMouseClicked(ev -> {
                        sun.getTokenSprite().setVisible(false);
                        sun.setCollected(true);
                        lv.setTokenCounter(lv.getTokenCounter() + sun.getValue());
                        label.setText(Integer.toString(lv.getTokenCounter()));
                    });
                }
            }

        }
    }
}
