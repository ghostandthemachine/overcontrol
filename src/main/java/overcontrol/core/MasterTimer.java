package overcontrol.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import overcontrol.rhythm.AdvancedStepSequencerListener;

public class MasterTimer extends Timer {

    int delay = 500;
    float bpm = 120;
    long count = 0;
    long lastCount;
    int measureSize = 4;
    Timer subTimer;
    long subCount = 0;

    public MasterTimer() {
        super(500, null);

        //subTimer is used for thing like stepsequencer that have a higher resolution of events required
        subTimer = new Timer(this.getDelay() * 4, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                subCount++;
            }
        });


        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                lastCount = count;
                count++;
            }
        });
    }

    public void setBpm(float i) {
        delay = (int) ((1 / i) * 60000);
        bpm = i;
        this.setDelay(delay);
        subTimer.setDelay(delay * 4);
    }

    //////////////////temp
    public long getNow() {
        return System.nanoTime();
    }

    public void setToZero() {
        count = 0;
        subCount = 0;
    }

    public String getCountString() {
        String countString = Long.toString(count / measureSize) + "." + Long.toString((count % measureSize) + 1);
        return countString;
    }

    public long getLastCount() {
        return lastCount;
    }

    public long getCount() {
        return count;
    }

    public long getSubCount() {
        return subCount;
    }

    public int getStepCount() {
        return (int) (count % measureSize);
    }

    @Override
    public void start() {
        super.start();
        subTimer.start();
    }

    @Override
    public void stop() {
        super.stop();
        subTimer.stop();
    }

    public void addSubTimerActionListener(AdvancedStepSequencerListener advancedStepSequencerListener) {
        subTimer.addActionListener(advancedStepSequencerListener);
    }

}
