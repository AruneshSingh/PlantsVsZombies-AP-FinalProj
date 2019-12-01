package threads;

import classes.Characters;
import classes.Plants;
import classes.Zombies;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ZombiePlantCollisionHandler extends AnimationTimer {
    Map<ImageView,Zombies> Zombie;
    ArrayList<ImageView> zombieInRow;
    Map<ImageView, TranslateTransition> transitionMap;
    ImageView Plant;
    int counter ;
    boolean stoppedZombie;
    Plants newPlant;
    Set<String> placedPlants;
    boolean alive;
    class DamagePlant extends  AnimationTimer{
        ImageView zombieImage;
        ZombiePlantCollisionHandler Z;
        public DamagePlant(ImageView zombieImage,ZombiePlantCollisionHandler Z){
            this.zombieImage = zombieImage;
            transitionMap.get(zombieImage).pause();
            this.Z = Z;
            counter = 0;
        }
        @Override
        public void handle(long l) {
            if(counter%60==0&&zombieImage.getImage()!=null) {
                counter = 0;
                newPlant.takeDamage(Zombie.get(zombieImage).getDamage());
                if(newPlant.getHealth()<=0) {
                    Plant.setImage(null);
                    placedPlants.remove(Plant.toString());
                    transitionMap.get(zombieImage).play();
                    alive = false;
                    Z.start();
                    stop();
                    return;
                }
            }
            if(zombieImage.getImage()==null){
                stop();
                return;
            }
            counter+=1;
        }

    }
    public ZombiePlantCollisionHandler(ImageView Plant, ArrayList<ImageView> zombieInRow, Map<ImageView, TranslateTransition> transitionMap, Plants newPlant, Map<ImageView,Zombies> Zombie, Set<String> placedPlants){
        this.zombieInRow = zombieInRow;
        this.Plant = Plant;
        this.transitionMap = transitionMap;
        stoppedZombie = false;
        this.newPlant = newPlant;
        this.Zombie = Zombie;
        this.placedPlants = placedPlants;
        this.alive = true;
    }
    @Override
    public void handle(long l) {

        for(int i=0;i<zombieInRow.size();i++){
            if(zombieInRow.get(i).getBoundsInParent().intersects(Plant.getBoundsInParent())) {
                System.out.println("ZOMBIEPLANTCOLLISION");
                transitionMap.get(zombieInRow.get(i)).pause();
                if(Plant.getId()=="cherrybomb") {
                    zombieInRow.get(i).setImage(null);
                    zombieInRow.get(i).setVisible(false);
                    Zombie.remove(zombieInRow.get(i));
                    ((Pane) zombieInRow.get(i).getParent()).getChildren().remove(Zombie.get(i));
                    zombieInRow.remove(i);
                    Plant.setImage(null);
                    placedPlants.remove(Plant.getId());
                    alive = false;
                }
                else {
                    DamagePlant damaging = new DamagePlant(zombieInRow.get(i),this);
                    damaging.start();
                    stop();
                }
            }
        }
        if(!alive){
            stop();
        }
    }
}
