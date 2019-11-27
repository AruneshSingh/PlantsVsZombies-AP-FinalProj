package classes;

import java.io.Serializable;

abstract public class Plants extends Characters implements Serializable {
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