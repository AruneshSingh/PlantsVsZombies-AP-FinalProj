abstract class Characters {

    protected int Health;

    abstract void action();

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }
}
