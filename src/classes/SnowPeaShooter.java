package classes;

public class SnowPeaShooter extends Shooter {

    private Bullet pea;

    public SnowPeaShooter(){
        this.pea = new Bullet("SnowPea");
        setHealth(600);
        setWaitingTime(10);
        setSunTokensReq(150);

    }

    @Override
    public void shoot() {

    }

    @Override
    public void action(Plants p, Zombies z) {
        shoot();
    }
}
