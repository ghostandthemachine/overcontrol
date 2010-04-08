package overcontrol.rythm.stepsequencer;

import com.sun.scenario.scenegraph.event.SGMouseAdapter;

public class SelectionButtonListener extends SGMouseAdapter {

    StepSequencer sequencer;

    public SelectionButtonListener(StepSequencer seq) {
        sequencer = seq;
    }

    public StepSequencer getParentSequencer(){
        return sequencer;
    }
}
