package classes;

abstract public class Characters {

    protected int Health;

    abstract void action(Plants p, Zombies z);

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }
}
