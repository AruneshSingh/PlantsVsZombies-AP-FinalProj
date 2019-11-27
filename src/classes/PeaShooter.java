package classes;

//TODO: Make shoot function. it calls the attack function and also loops the shooting till the health is 0 or the zombie's
//health is 0. it only starts when a zombie is in sight.
public class PeaShooter extends Shooter {

    private Bullet pea;

    public PeaShooter(){
        this.pea = new Bullet("Pea");
        setHealth(500);
        setWaitingTime(10);
        setSunTokensReq(100);

    }

    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth()-damage);
    }

    @Override
    public void shoot() {

    }

    @Override
    public void action(Plants p, Zombies z) {
        shoot();
    }
}
