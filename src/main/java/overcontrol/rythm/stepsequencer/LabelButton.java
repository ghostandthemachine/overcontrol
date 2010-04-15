/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.rythm.stepsequencer;

import com.sun.scenario.scenegraph.SGText;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import overcontrol.core.GUIButton;

/**
 *
 * @author Jon
 */
public class LabelButton extends GUIButton {

    String label;
    SGText labelNode = new SGText();

    public LabelButton(double tx, double ty, double tw, double th, String l) {
        super(tx, ty, tw, th, l);
        label = l;

        Color presetBaseColor = new Color(175, 175, 175);
        setBaseColor(presetBaseColor);
        labelNode.setText(label);
        labelNode.setFont(new Font("helvetica", Font.BOLD, 12));
        labelNode.setDrawPaint(Color.white);

        double tbx = getX() + 3;
        double tby = getY() + 3;
        labelNode.setLocation(new Point2D.Double(tbx, tby));

        this.add(labelNode);
    }

    @Override
    public void setOn() {
        this.setBaseColor(this.getOnColor());
        labelNode.setDrawPaint(Color.orange);
        this.setIsOn(true);
    }


    @Override
    public void setOff() {
        this.setBaseColor(this.getBaseColor());
        labelNode.setDrawPaint(Color.white);
        this.setIsOn(false);
    }
}
