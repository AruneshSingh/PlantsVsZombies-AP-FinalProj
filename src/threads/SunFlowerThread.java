package threads;

import classes.Level;
import classes.SunToken;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SunFlowerThread extends AnimationTimer {
    int counter;
    Level lv;
    Pane pane;
    Label label;
    int x,y;

    public SunFlowerThread(Pane pane, Level lv, Label label, int x, int y){
        counter = 0;
        this.pane = pane;
        this.lv = lv;
        this.label = label;
    }

    @Override
    public void handle(long l) {

        Random rand = new Random();

        counter += 1;
        if (counter%500 == 0){
            int r = rand.nextInt(10);
            counter = 0;
            SunToken sun = new SunToken();
            sun.setSpriteXY(x+ 100, y+ 100);
            pane.getChildren().add(sun.getTokenSprite());
//            sun.moveSun();
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
