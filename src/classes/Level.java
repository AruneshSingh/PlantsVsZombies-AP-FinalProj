package classes;

import java.util.ArrayList;

public class Level {
    private int level;
    private int tokenCounter;
    private boolean finished;
    private String mode;
    private ArrayList<ArrayList<Characters>> grid;

    Level(int lv, String mode){
        this.level = lv;
        this.tokenCounter = 0;
        this.finished = false;
        this.mode = mode;
        this.grid = new ArrayList<ArrayList<Characters>>(5);

        ArrayList<Characters> temp = new ArrayList<Characters>(10);
        temp.add(new Lawnmover());
        for (int i = 0; i < 5 ; i++) {
            this.grid.add(temp);
        }
    }

    private ArrayList<Plants> temp = new ArrayList<Plants>(9);

    //TODO: Generate suntokens continuously
    public void generateSunToken(){

    }






}
