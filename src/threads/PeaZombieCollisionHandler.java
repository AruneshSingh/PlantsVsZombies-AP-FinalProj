package threads;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class PeaZombieCollisionHandler extends AnimationTimer {
    private ImageView Pea,Zombie;
    public PeaZombieCollisionHandler(ImageView Pea,ImageView Zombie) {
        this.Pea = Pea;
        this.Zombie = Zombie;
    }
    @Override
    public void handle(long l) {
        if(Pea.getBoundsInParent().intersects(Zombie.getBoundsInParent())){

        }
    }
}
