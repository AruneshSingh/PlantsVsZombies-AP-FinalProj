package threads;

import classes.Zombies;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Map;

public class Lawnmower extends AnimationTimer {
    private ArrayList<ImageView> Zombie;
    private ImageView L;
    private Map<ImageView, Zombies> zombieObject;
    private boolean stationary;
    public Lawnmower(ArrayList<ImageView> rowZombies, ImageView L,Map<ImageView, Zombies> zombieObject) {
        this.L = L;
        this.Zombie = rowZombies;
        stationary = true;
        this.zombieObject = zombieObject;
    }
    @Override
    public void handle(long l) {
        for(int i=0;i<Zombie.size();i++) {
            if(L.getBoundsInParent().intersects(Zombie.get(i).getBoundsInParent())) {
                if(stationary) {
                    stationary = false;
                    TranslateTransition translateTransition = new TranslateTransition();
                    translateTransition.setDuration(Duration.millis(1000));
                    translateTransition.setNode(L);
                    translateTransition.setByX(1500);
                    translateTransition.setCycleCount(1);
                    translateTransition.setAutoReverse(false);
                    translateTransition.play();
                }
                Zombie.get(i).setImage(null);
                Zombie.get(i).setVisible(false);
                zombieObject.remove(Zombie.get(0));
                ((Pane) Zombie.get(i).getParent()).getChildren().remove(Zombie.get(i));
                Zombie.remove(i);
                if(L.getTranslateX()>1500){
                    L.setImage(null);
                    L.setVisible(false);
                    stop();
                    return;
                }
            }
        }
    }
}
