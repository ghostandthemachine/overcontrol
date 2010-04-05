package overcontrol.core;

import javax.swing.Timer;

public class MasterTimer extends Timer {

    int delay = 500;
    float bpm = 120;

    public MasterTimer() {
        super(500, null);
        this.start();
    }

    public void setBpm(float i) {
        delay = (int) ((1 / i) * 60000);
        bpm = i;
        this.setDelay(delay);
    }

    //////////////////temp
    public long getNow() {
        return System.nanoTime();
    }
}
