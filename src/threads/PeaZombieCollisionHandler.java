package threads;

import classes.Zombies;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Map;

public class PeaZombieCollisionHandler extends AnimationTimer {
    private ArrayList<ImageView> Zombie;
    private ImageView Pea;
    private Map<ImageView,Zombies> zombieObject;
    private Map<ImageView, TranslateTransition> transitionMap;
    public PeaZombieCollisionHandler(ImageView Pea, ArrayList<ImageView> Zombie, Map<ImageView,Zombies> zombieObject, Map<ImageView, TranslateTransition> transitionMap ) {
        this.Pea = Pea;
        this.Zombie = Zombie;
        this.zombieObject = zombieObject;
        this.transitionMap = transitionMap;
    }
    @Override
    public void handle(long l) {
        System.out.println(Zombie);
        System.out.println(Zombie.size());
        if(Zombie.size()!=0&&Pea.getBoundsInParent().intersects(Zombie.get(0).getBoundsInParent())){
            Pea.setVisible(false);
            transitionMap.get(Pea).stop();
            System.out.println(zombieObject);
            System.out.println(Zombie);
            System.out.println(Pea.getId());
            zombieObject.get(Zombie.get(0)).takeDamage(Integer.parseInt(Pea.getId()));
            ((GridPane) Pea.getParent()).getChildren().remove(Pea);
            if(zombieObject.get(Zombie.get(0)).getHealth()<=0) {
                Zombie.get(0).setVisible(false);
                zombieObject.remove(Zombie.get(0));
                ((Pane)Zombie.get(0).getParent()).getChildren().remove(Zombie.get(0));
            }
        }
    }
}
