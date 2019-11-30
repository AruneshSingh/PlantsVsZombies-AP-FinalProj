package threads;

import classes.Characters;
import classes.Plants;
import classes.Zombies;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

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
        public DamagePlant(ImageView zombieImage){
            this.zombieImage = zombieImage;
            transitionMap.get(zombieImage).pause();
            counter = 0;
        }
        @Override
        public void handle(long l) {
            if(counter%600==0&&zombieImage.getImage()!=null) {
                counter = 0;
                newPlant.takeDamage(Zombie.get(zombieImage).getDamage());
                if(newPlant.getHealth()<=0) {
                    Plant.setImage(null);
                    placedPlants.remove(Plant.getId());
                    transitionMap.get(zombieImage).play();
                    alive = false;
                    stop();
                    return;
                }
            }
            if(zombieImage==null){
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
            if(Plant.getBoundsInParent().intersects(zombieInRow.get(i).getBoundsInParent())) {
                System.out.println("ZOMBIEPLANTCOLLISION");
                transitionMap.get(zombieInRow.get(i)).pause();
                DamagePlant damaging = new DamagePlant(zombieInRow.get(i));
                damaging.start();
            }
        }
        if(!alive){
            stop();
        }
    }
}
