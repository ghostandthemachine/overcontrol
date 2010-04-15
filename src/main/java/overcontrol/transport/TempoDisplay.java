package overcontrol.transport;


public class TempoDisplay extends ValueDisplay {

    private double tempo = 120;

    public TempoDisplay(double tx, double ty, double tw, double th) {
        super(tx,ty,tw,th);
        this.setText("120.0");
    }

    @Override
    public void dragged() {
        tempo -= this.getYVelocity() / 4;
        setTempo(tempo);
    }

    private void setTempo(double tempo) {
        this.setText(Double.toString((float) tempo));
    }

}