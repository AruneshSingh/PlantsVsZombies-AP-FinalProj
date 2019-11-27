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

    public void setRabid(String mode) {
        this.rabid = mode.equals("Rabid"); //Returns true if rabid, else false
    }
}

class Skinny extends Zombies {

    Skinny(){
        setHealth(100);
        setDamage(10);
        setSpeed(5);
    }
}

class Conehead extends Zombies {

    Conehead(){
        setHealth(130);
        setDamage(15);
        setSpeed(5);
    }
}

class Football extends Zombies {

    Football(){
        setHealth(150);
        setDamage(15);
        setSpeed(7);
    }
}

class Skinny2 extends Zombies {

    Skinny2(){
        setHealth(100);
        setDamage(10);
        setSpeed(5);
    }
}