package overcontrol.core.transport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import overcontrol.core.MasterTimer;

public class DisplayUpdateListener implements ActionListener {

    ValueDisplay display;
    MasterTimer masterTimer;

    public DisplayUpdateListener(ValueDisplay disp, MasterTimer timer) {
        display = disp;
        masterTimer = timer;

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
            display.setText(masterTimer.getCountString());
    }
}
