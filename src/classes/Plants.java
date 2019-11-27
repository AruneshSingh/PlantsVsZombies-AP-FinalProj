package classes;

import java.io.Serializable;

abstract class Plants extends Characters implements Serializable {
    private int waitingTime;
    private int sunTokensReq;

    public void takeDamage(int damage){
        setHealth(getHealth()-damage);
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getSunTokensReq() {
        return sunTokensReq;
    }

    public void setSunTokensReq(int sunTokensReq) {
        this.sunTokensReq = sunTokensReq;
    }
}

abstract class Shooter extends Plants {
    protected class Bullet {
        private int damage;
        private String type;

        Bullet(String t){
            this.type = t;
            if(type.equals("Pea"))
                this.damage = 10;
            else
                this.damage = 20;
        }

        public void attack(Zombies z) {
            z.setHealth(z.getHealth()-damage);
        }
    }

    abstract void shoot();
}

abstract class Barrier extends Plants {
}

abstract class TokenGiver extends Plants {
    abstract void tokenDrop();
}


abstract class Bomb extends Plants {
}

//TODO: Make shoot function. it calls the attack function and also loops the shooting till the health is 0 or the zombie's
//health is 0. it only starts when a zombie is in sight.
class PeaShooter extends Shooter {

    private Bullet pea;

    PeaShooter(){
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
    public void action() {
        shoot();
    }
}

class SnowPeaShooter extends Shooter {

    private Bullet pea;

    SnowPeaShooter(){
        this.pea = new Bullet("SnowPea");
        setHealth(600);
        setWaitingTime(10);
        setSunTokensReq(150);

    }

    @Override
    public void shoot() {

    }

    @Override
    public void action() {
        shoot();
    }
}

//TODO: Action method for wallnut
class Wallnut extends Barrier {

    Wallnut(){
        setHealth(3000);
        setWaitingTime(15);
        setSunTokensReq(50);
    }

    @Override
    public void action() {

    }
}

class Sunflower extends TokenGiver {

    private SunToken token;

    Sunflower(){
        this.token = new SunToken();
        setHealth(500);
        setWaitingTime(10);
        setSunTokensReq(50);
    }

    @Override
    void tokenDrop() {

    }

    @Override
    void action() {

    }
}


//TODO: Make the blast function, should make the health of the zombie z 0.
class CherryBomb extends Bomb {

    CherryBomb(){
        setWaitingTime(20);
        setSunTokensReq(150);
    }

    public void blast(){

    }

    @Override
    void action() {
        blast();
    }

}