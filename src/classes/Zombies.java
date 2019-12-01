package classes;

import java.io.Serializable;

abstract public class Zombies extends Characters implements Serializable {
    private int damage;
    private int speed;
    private boolean rabid;

    @Override
    public void action(Plants p, Zombies z) {
        p.setHealth(p.getHealth()-getDamage());
    }

    public void takeDamage(int damage){
        setHealth(getHealth()-damage);
    }

    public void attack(Plants p){
        p.setHealth(p.getHealth()-damage);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isRabid() {
        return rabid;
    }

    public void setRabid(Boolean mode) {
        this.rabid = mode; //Returns true if rabid, else false
    }
}