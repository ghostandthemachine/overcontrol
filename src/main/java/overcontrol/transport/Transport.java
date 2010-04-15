/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.transport;

import overcontrol.core.*;
import com.sun.scenario.scenegraph.JSGPanel;
import com.sun.scenario.scenegraph.SGAbstractShape.Mode;
import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.SGShape;
import com.sun.scenario.scenegraph.fx.FXShape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import overcontrol.core.MasterTimer;

/**
 *
 * @author Jon
 */
public class Transport extends JSGPanel {

    SGGroup transportGroup = new SGGroup();
    ValueDisplay valueDisplay;
    TempoDisplay tempoDisplay;

    public Transport() {

        transportGroup.add(createPlayButton());

        SGShape shape = new SGShape();
        shape.setShape(new Rectangle(0, 0, getHeight(), getWidth()));
        shape.setMode(Mode.FILL);
        shape.setFillPaint(Color.red);
        transportGroup.add(shape);

        valueDisplay =  new ValueDisplay(100, 6, 22, 80);

        tempoDisplay = new TempoDisplay(190,6,22,76);

        transportGroup.add(tempoDisplay);
        transportGroup.add(valueDisplay);

        this.setPreferredSize(new Dimension(this.getWidth(), 30));
        this.setScene(transportGroup);

    }


    public SGGroup createPlayButton() {
        double buttonY = 6;

        SGGroup buttonGroup = new SGGroup();
        final GUIButton play = new GUIButton(4, buttonY, 22, 22, "play");
        play.addIndicator(play.createTriangle());
        play.setIdicatorColor(Color.white);
        play.setIndicatorOnColor(Color.green);
        play.addMouseListener(new TransportListener(this, play) {

            private boolean toggle = false;

            @Override
            public void mouseClicked(MouseEvent e, SGNode node) {
                if (!toggle) {
                    getButton().setOn();
                    toggle = true;
                } else {
                    getButton().setOff();
                    toggle = false;
                }
            }
        });

        buttonGroup.add(play);

        final GUIButton record = new GUIButton(64, buttonY, 22, 22, "record");
        record.setIdicatorColor(Color.white);
        record.setIndicatorOnColor(Color.red);
        record.addMouseListener(new TransportListener(this, record) {

            private boolean toggle = false;

            @Override
            public void mouseClicked(MouseEvent e, SGNode node) {
                if (!toggle) {
                    getButton().setOn();
                    toggle = true;
                } else {
                    getButton().setOff();
                    toggle = false;
                }
            }
        });


        record.addIndicator(record.createCircle());
        buttonGroup.add(record);

        final GUIButton stop = new GUIButton(34, buttonY, 22, 22, "stop");
        stop.setIdicatorColor(Color.white);
        stop.setIndicatorOnColor(Color.green);
        stop.addMouseListener(new TransportListener(this, stop) {

            private boolean toggle = false;

            @Override
            public void mousePressed(MouseEvent e, SGNode node) {
                getButton().setOn();
                play.setOff();
                record.setOff();
                valueDisplay.setText("0.0");

            }

            @Override
            public void mouseReleased(MouseEvent e, SGNode node) {
                getButton().setOff();
            }
        });

        FXShape stopShape = new FXShape();
        stopShape.setMode(Mode.FILL);
        stop.addIndicator(stop.createSquare());
        buttonGroup.add(stop);



        return buttonGroup;
    }

}
