package classes;

import java.io.Serializable;

public class SunToken implements Serializable {
    private int disappearTime;
    private boolean collected;

    SunToken(){
        this.disappearTime = 10;
        this.collected = false;
    }

    //TODO: Run this function every second.
    public boolean isExpired(){
        if(getDisappearTime() == 0 && !isCollected())
            return true;
        else {
            setDisappearTime(getDisappearTime() - 1);
            return false;
        }
    }

    public int getDisappearTime() {
        return disappearTime;
    }

    public void setDisappearTime(int disappearTime) {
        this.disappearTime = disappearTime;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
