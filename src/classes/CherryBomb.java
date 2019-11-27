package classes;

//TODO: Make the blast function, should make the health of the zombie z 0.
public class CherryBomb extends Bomb {

    public CherryBomb(){
        setWaitingTime(20);
        setSunTokensReq(150);
    }

    public void blast(){

    }

    @Override
    void action(Plants p, Zombies z) {
        blast();
    }

}
