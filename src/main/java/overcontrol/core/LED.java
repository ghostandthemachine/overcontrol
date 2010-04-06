package overcontrol.core;

import com.sun.scenario.scenegraph.SGAbstractShape.Mode;
import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.fx.FXShape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class LED extends SGGroup {

    private double x;
    private double y;
    private double radius;
    private Color ledOffColor = new Color(255, 0, 0, 100);
    private Color ledOnColor = Color.red;
    private Color borderColor = Color.lightGray;
    private FXShape led = new FXShape();
    private float strokeSize = 1.0f;

    public LED(double tx, double ty, double r) {
        x = tx;
        y = ty;
        radius = r;

        createLed();

    }

    private void createLed() {
        led.setShape(new Ellipse2D.Double(x, y, radius, radius));
        led.setAntialiasingHint(RenderingHints.VALUE_ANTIALIAS_ON);
        led.setDrawStroke(new BasicStroke(strokeSize));
        led.setFillPaint(ledOffColor);
        led.setMode(Mode.STROKE_FILL);
        this.add(led);
    }

    public void on() {
        led.setFillPaint(ledOnColor);
    }

    public void off() {
        led.setFillPaint(ledOffColor);
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getLedOffColor() {
        return ledOffColor;
    }

    public void setLedOffColor(Color ledOffColor) {
        this.ledOffColor = ledOffColor;
    }

    public Color getLedOnColor() {
        return ledOnColor;
    }

    public void setLedOnColor(Color ledOnColor) {
        this.ledOnColor = ledOnColor;
    }

    public float getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(float strokeSize) {
        this.strokeSize = strokeSize;
        led.setDrawStroke(new BasicStroke(strokeSize));
    }



}
