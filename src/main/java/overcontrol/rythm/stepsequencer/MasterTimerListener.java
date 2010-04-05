/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package overcontrol.rythm.stepsequencer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import overcontrol.core.MasterTimer;

/**
 *
 * @author Jon
 */
public class MasterTimerListener implements ActionListener {

    StepSequencer sequencer;
    MasterTimer masterTimer;

    public MasterTimerListener(StepSequencer seq, MasterTimer master) {
        sequencer = seq;
        masterTimer = master;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sequencer.increaseCount(masterTimer.getNow());
    }


}
