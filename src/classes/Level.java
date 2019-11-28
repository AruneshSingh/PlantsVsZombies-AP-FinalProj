package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
    private int level;
    private int tokenCounter;
    private boolean finished;
    private String mode;
    private ArrayList<ArrayList<Characters>> grid;
    private ArrayList<ArrayList<Zombies>> zombieGrid; // to make it for every level

    public Level(int lv, String mode){
        this.level = lv;
        this.tokenCounter = 0;
        this.finished = false;
        this.mode = mode;
        this.grid = new ArrayList<ArrayList<Characters>>(5);

        for (int i = 0; i < 5 ; i++) {
            ArrayList<Characters> temp = new ArrayList<Characters>(10);
            temp.add(new Lawnmover());
            for (int j = 0; j < 9; j++) {
                temp.add(null);
            }
            this.grid.add(temp);
        }
    }

    public ArrayList<ArrayList<Characters>> getGrid() {
        return grid;
    }

    public void addToGrid(ArrayList<ArrayList<Characters>> grid, Characters c, int row, int column) {
        grid.get(row).set(column, c);
    }

    //TODO: Generate suntokens continuously
    public void generateSunToken(){

    }

}
