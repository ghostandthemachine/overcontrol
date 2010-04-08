package overcontrol.core.transport;

import overcontrol.core.MasterTimer;


public class TempoDisplay extends ValueDisplay {

    private double tempo = 120;

    MasterTimer master;

    public TempoDisplay(double tx, double ty, double tw, double th, MasterTimer timer) {
        super(tx,ty,tw,th);

        master = timer;

        this.setText("120.0");

    }

    @Override
    public void dragged() {
        tempo -= this.getYVelocity() / 4;
        setTempo(tempo);

    }

    private void setTempo(double tempo) {

        this.setText(Double.toString((float) tempo));
        master.setBpm((float) tempo);
    }

}