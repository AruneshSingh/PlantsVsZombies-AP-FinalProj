package classes;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import threads.SunFlowerThread;

public class Sunflower extends TokenGiver {


    public Sunflower(){
        setHealth(500);
        setWaitingTime(10);
        setSunTokensReq(50);
    }

    @Override
    void tokenDrop() {

    }

    @Override
    void action(Plants p, Zombies z) {

    }
}
