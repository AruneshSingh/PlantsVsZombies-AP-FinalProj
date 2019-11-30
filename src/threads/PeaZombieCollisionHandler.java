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
//        System.out.println(Zombie);
//        System.out.println(Zombie.size());
        for(int i=0;i<Zombie.size();i++) {
//            System.out.println(Pea.getTranslateX()+" "+Zombie.get(i).getTranslateX());
            if (Zombie.size() != 0 && Pea.getBoundsInParent().intersects(Zombie.get(i).getBoundsInParent())) {
                Pea.setImage(null);
                transitionMap.get(Pea).stop();
                Pea.setVisible(false);
                //            System.out.println(zombieObject);
                //            System.out.println(Zombie);
                //            System.out.println(Pea.getId());
                zombieObject.get(Zombie.get(i)).takeDamage(Integer.parseInt(Pea.getId()));
                ((GridPane) Pea.getParent()).getChildren().remove(Pea);
                if (zombieObject.get(Zombie.get(0)).getHealth() <= 0) {
                    Zombie.get(i).setImage(null);
                    Zombie.get(i).setVisible(false);
                    zombieObject.remove(Zombie.get(0));
                    ((Pane) Zombie.get(i).getParent()).getChildren().remove(Zombie.get(i));
                    Zombie.remove(i);
                }
                stop();
                return;
            }
//            else if(Pea.getTranslateX()>=750){
//                Pea.setImage(null);
//                transitionMap.get(Pea).stop();
//                return;
//            }
        }
    }
}
