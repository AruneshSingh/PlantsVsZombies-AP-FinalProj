package classes;

//TODO: Action method for wallnut
public class Wallnut extends Barrier {

   public Wallnut(){
        setHealth(3000);
        setWaitingTime(15);
        setSunTokensReq(50);
    }

    @Override
    public void action(Plants p, Zombies z) {

    }
}