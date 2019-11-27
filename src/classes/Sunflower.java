package classes;

public class Sunflower extends TokenGiver {

    private SunToken token;

    public Sunflower(){
        this.token = new SunToken();
        setHealth(500);
        setWaitingTime(10);
        setSunTokensReq(50);
    }

    @Override
    void tokenDrop() {

    }

    @Override
    void action(Plants p, Zombies z) {

    }
}
