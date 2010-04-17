/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.core;

import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.SGShape;
import com.sun.scenario.scenegraph.SGText;
import com.sun.scenario.scenegraph.event.SGMouseListener;
import com.sun.scenario.scenegraph.fx.FXShape;
import java.awt.Color;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Jon
 */
public class GUIButton extends GUIComponent {

    private FXShape fxIndicator = new FXShape();
    private SGText labelIndicator = new SGText();
    private SGGroup groupIndicator = new SGGroup();
    private FXShape buttonShape = new FXShape();
    public static String FXSHAPE = "fxShape";
    public static String GROUP = "group";
    public static String LABEL = "label";
    private String type;
    private Color fillColor = new Color(200, 200, 200);
    private Color onColor = new Color(150, 150, 150);
    private String id;
    private boolean isOn = false;
    private Color indicatorOnColor = Color.white;
    private Color indicatorColor = Color.white;

    public GUIButton(double tx, double ty, double w, double h, String s) {
        super(tx, ty, w, h);
        id = s;
        buttonShape.setShape(new RoundRectangle2D.Double(tx, ty, w, h, 6, 6));
        buttonShape.setFillPaint(fillColor);
        buttonShape.setMode(SGShape.Mode.FILL);
        buttonShape.setAntialiasingHint(RenderingHints.VALUE_ANTIALIAS_ON);
        add(buttonShape);

        type = s;
    }

    public GUIButton(String s) {
        super();
        id = s;
        buttonShape.setFillPaint(fillColor);
        buttonShape.setMode(SGShape.Mode.FILL);
        buttonShape.setAntialiasingHint(RenderingHints.VALUE_ANTIALIAS_ON);
        add(buttonShape);

    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn() {
        buttonShape.setFillPaint(onColor);
        isOn = true;
        if (type.equals(GUIButton.FXSHAPE)) {
            fxIndicator.setFillPaint(indicatorOnColor);
        } else if (type.equals(GUIButton.LABEL)) {
            labelIndicator.setDrawPaint(indicatorOnColor);
        }
    }

    public void setOff() {
        buttonShape.setFillPaint(fillColor);
        isOn = false;
        if (type.equals(GUIButton.FXSHAPE)) {
            fxIndicator.setFillPaint(indicatorColor);
        } else if (type.equals(GUIButton.LABEL)) {
            labelIndicator.setDrawPaint(indicatorColor);
        }
    }

    @Override
    public void addMouseListener(SGMouseListener a) {
        buttonShape.addMouseListener(a);
    }

    public FXShape getButtonShape() {
        return buttonShape;
    }

    public void setButtonShape(FXShape buttonShape) {
        this.buttonShape = buttonShape;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getOnColor() {
        return onColor;
    }

    public void setOnColor(Color onColor) {
        this.onColor = onColor;
    }

    public void addIndicator(FXShape node) {
        fxIndicator = (FXShape) node;
        fxIndicator.setFillPaint(indicatorColor);
        fxIndicator.setMode(SGShape.Mode.STROKE_FILL);
        fxIndicator.setAntialiasingHint(RenderingHints.VALUE_ANTIALIAS_ON);

        this.addComponent(fxIndicator);
        type = GUIButton.FXSHAPE;
    }

    public void removeFXIndicator() {
        this.remove(fxIndicator);
    }

    public void addIndicator(SGText node) {
        labelIndicator = node;
        labelIndicator.setFillPaint(indicatorColor);
        labelIndicator.setMode(SGShape.Mode.STROKE_FILL);

        this.addComponent(labelIndicator);
        type = GUIButton.LABEL;
    }

    public void setIndicator(String string) {
        labelIndicator.setText(string);
    }

    public void removeLabelIndicator() {
        this.remove(labelIndicator);
    }

    public void addIndicator(SGGroup node) {
        groupIndicator = node;

        this.addComponent(groupIndicator);
        type = GUIButton.GROUP;
    }

    public void removeGroupIndicator() {
        this.remove(groupIndicator);
    }

    public void setIndicatorOnColor(Color color) {
        indicatorOnColor = color;
    }

    public void setIdicatorColor(Color color) {
        indicatorColor = color;
    }

    public void setIsOn(boolean b) {
        isOn = b;
    }

    public FXShape createTriangle() {
        int pad = (int) (this.getHeight() / 4);
        FXShape triangle = new FXShape();
        Point p1 = new Point((int) (pad + this.getX()), (int) (pad + this.getY()));
        Point p2 = new Point((int) (this.getWidth() - pad + this.getX()), (int) (this.getY() + this.getHeight() / 2));
        Point p3 = new Point((int) (pad + this.getX()), (int) (this.getY() + this.getHeight() - pad));

        Triangle tri = new Triangle(p1, p2, p3);

        triangle.setShape(tri);
        return triangle;

    }

    public FXShape createSquare() {
        FXShape shape = new FXShape();
        double pad = this.getWidth() / 4;
        shape.setShape(new RoundRectangle2D.Double(this.getX() + pad, this.getY() + pad, this.getWidth() / 2, this.getWidth() / 2, 5, 5));
        return shape;
    }

    public FXShape createCircle() {
        FXShape shape = new FXShape();
        double pad = this.getWidth() / 4;
        shape.setShape(new Ellipse2D.Double(this.getX() + pad, this.getY() + pad, this.getWidth() / 2, this.getWidth() / 2));
        return shape;
    }
}


