/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.rhythm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import overcontrol.core.MasterTimer;

/**
 *
 * @author Jon
 */
public class AdvancedStepSequencerListener implements ActionListener {

    AdvancedStepSequencer sequencer;
    MasterTimer masterTimer;
    int count = 0;

    public AdvancedStepSequencerListener(AdvancedStepSequencer seq, MasterTimer timer) {
        sequencer = seq;
        masterTimer = timer;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        count = (int) (masterTimer.getSubCount() % sequencer.getStepCount());
        sequencer.setCount(count);
    }
}
