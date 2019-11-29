package classes;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class SunToken implements Serializable {
    private int disappearTime;
    private boolean collected;
    private final int value;
    private final ImageView tokenSprite;

    public SunToken(){
        this.disappearTime = 2;
        this.collected = false;
        this.value = 25;
        this.tokenSprite = new ImageView("images/plants/sun.gif");
    }

    public boolean isExpired(){
        if(getDisappearTime() == 0 && !isCollected())
            return true;
        else {
            setDisappearTime(getDisappearTime() - 1);
            return false;
        }
    }

    public void moveSun() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(10000));
        translateTransition.setNode(tokenSprite);
        translateTransition.setToY(660);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();
    }

    public int getDisappearTime() {
        return disappearTime;
    }

    public void setDisappearTime(int disappearTime) {
        this.disappearTime = disappearTime;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public ImageView getTokenSprite() {
        return tokenSprite;
    }

    public int getValue() {
        return value;
    }

    public void setSpriteXY(int x, int y) {
        tokenSprite.setX(x);
        tokenSprite.setY(y);
    }
}
