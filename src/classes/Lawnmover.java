package classes;

//TODO: Check how to kill all zombies in the row

class Lawnmover extends Characters {
    private void attack(Zombies z) {
        z.setHealth(0);
    }

    @Override
    void action(Plants p, Zombies z) {
        attack(z);
    }
}