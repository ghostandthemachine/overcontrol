package overcontrol.rythm.stepsequencer;

import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.SGText;
import com.sun.scenario.scenegraph.fx.FXShape;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import overcontrol.core.GUIButton;
import overcontrol.core.GUIButtonClickListener;
import overcontrol.core.LED;
import overcontrol.core.MasterTimer;
import overcontrol.core.Tools;

public class AdvancedStepSequencer extends StepSequencer {

    private int numPresetButtons = 8;
    private FXShape buttonShape = new FXShape();
    private GUIButton[] trackSelectionButtons;
    private GUIButton[][] presetButtons;
    private Color presetButtonColor = Color.ORANGE;
    private int nTracks;
    private int nSteps;
    private GUIButton trigger;
    private LED[] leds;
    private MasterTimer masterTimer;

    public AdvancedStepSequencer(float tx, float ty, int tsteps, int ttracks, MasterTimer timer) {
        super(tx, ty, 250, 100, tsteps, ttracks, 50, 0, 90, 10);
        nTracks = ttracks;
        nSteps = tsteps;
        masterTimer = timer;
        

        this.addComponent(createTrackSelectorInterface());

        ResolutionDial resoultionDial = new ResolutionDial(this.getX() + this.getWidth() - 28, this.getY() + 6, 16, this);
        resoultionDial.setBaseShapeOpacity(0.3f);
        addResolutionLabel();

        addComponent(resoultionDial);

        createButtons();

        createLeds();


        masterTimer.addSubTimerActionListener(new AdvancedStepSequencerListener(this, masterTimer));

    }


    @Override
    public void increaseCount() {
        super.increaseCount();

        leds[this.getLastCount()].off();
        leds[this.getCurrentCount()].on();
    }

    void createButtons() {
        double tx = this.getX() + 3;
        double ty = this.getY() + 3;


        trigger = new GUIButton(tx, ty, 10, 10, "trigger") {

            private boolean toggle = false;

            @Override
            public void clicked() {
                if (toggle) {
                    stop();
                    toggle = false;
                    trigger.setOff();
                } else {
                    start();
                    toggle = true;
                    trigger.setOn();
                }
            }
        };

        trigger.addIndicator(trigger.createTriangle());
        trigger.setBaseColor(Color.lightGray);
        trigger.setOnColor(Color.white);

        trigger.setIndicatorOnColor(Color.green);
        trigger.setIdicatorColor(Color.white);

        this.add(trigger);
    }

    public SGGroup createPresetInterface() {
        SGGroup group = new SGGroup();
        presetButtons = new GUIButton[numPresetButtons / 2][numPresetButtons / 2];

        double bw = 10;
        double bh = 10;

        for (int i = 0; i < (numPresetButtons / 2) - 1; i++) {
            for (int j = 0; j < (numPresetButtons / 2) - 1; j++) {
                double tx = 3 + (i * (bw + 3));
                double ty = 3 + (j * (bh + 3));
                presetButtons[i][j] = new GUIButton(tx, ty, bw, bh, Integer.toString(0));
                presetButtons[i][j].addMouseListener(new GUIButtonClickListener(presetButtons[i][j], i, j) {

                    private boolean toggle = false;

                    @Override
                    public void mouseClicked(MouseEvent e, SGNode node) {
                        if (toggle) {
                            toggle = false;
                            this.getParent().setOff();
                        } else {
                            toggle = true;
                            this.getParent().setOn();
                            for (int i = 0; i < numPresetButtons / 2 - 1; i++) {
                                for (int j = 0; j < numPresetButtons / 2 - 1; j++) {
                                    if (i != this.getID()) {
                                        presetButtons[i][j].setOff();
                                    }
                                }
                            }
                        }
                    }
                });


                group.add(presetButtons[i][j]);
            }
        }
        return group;
    }

    public SGGroup createTrackSelectorInterface() {
        SGGroup group = new SGGroup();
        trackSelectionButtons = new GUIButton[nTracks];

        Point2D.Double p = new Point2D.Double(this.getX(), this.getY());
        Point2D p2 = this.getBaseShape().localToGlobal(p, p);


        for (int i = 0; i < nTracks; i++) {
            final int id = i;

            Point2D.Double p1 = new Point2D.Double((p2.getX() + this.getxStepOffset() + this.getX()) / 2, p2.getY() + (i * this.getStepShape().getHeight()) + 2);

            double bw = this.getStepShape().getWidth() * 0.8;
            double bh = this.getStepShape().getWidth() * 0.8;
            double bx = p1.x + this.getxStepOffset() - 35;
            double by = p1.y + (i * this.getStepShape().getHeight()) + 2;

            trackSelectionButtons[i] = new GUIButton(bx, by, bw, bh, Integer.toString(0));
            trackSelectionButtons[i].addMouseListener(new SelectionButtonListener(this) {

                private boolean toggle = false;

                @Override
                public void mouseClicked(MouseEvent e, SGNode node) {
                    if (trackSelectionButtons[id].isOn()) {
                        trackSelectionButtons[id].setOff();
                        sequencer.unfocusTrack(id);
                    } else if (!trackSelectionButtons[id].isOn()) {
                        trackSelectionButtons[id].setOn();
                        sequencer.updateFocussedTrack(sequencer.getLastFocussedTrack());
                        sequencer.selectFocusTrack(id);
                        sequencer.setCurrentFocussedTrack(id);
                    }
                    setTrackSelectionGroupOff(id);
                }
            });
            group.add(trackSelectionButtons[i].getComponentGroup());
        }

        return group;
    }

    private void setTrackSelectionGroupOff(int id) {
        for (int i = 0; i < nTracks - 1; i++) {
            if (i != id) {
                trackSelectionButtons[i].setOff();
            }
        }
    }

    private void addResolutionLabel() {

        double textX = this.getGlobalCoordinate().getX() + this.getWidth() - 26;
        double textY = this.getGlobalCoordinate().getY() + 30;

        SGText resolutionLabel = new SGText();
        resolutionLabel.setFont(new Font("helvitica", Font.PLAIN, 9));
        resolutionLabel.setFillPaint(Color.white);
        resolutionLabel.setLocation(new Point2D.Double(textX, textY));
        resolutionLabel.setText("reso");

        this.addComponent(resolutionLabel);
    }

    private void createLeds() {
        leds = new LED[nSteps];
        for (int i = 0; i < nSteps; i++) {
            int radius = 6;
            double tx = 76 + (i * ((radius * 2) + 3.4));
            double ty = this.getY() + this.getHeight() - 10;
            leds[i] = new LED(tx, ty, radius);
            leds[i].setLedOnColor(Color.GREEN);
            leds[i].setLedOffColor(new Color(0,255,0,50));
            this.add(leds[i]);
        }
    }

    @Override
    public void setCount(int i) {
        super.setCount(i);
        leds[this.getLastCount()].off();
        leds[this.getCurrentCount()].on();
    }

}
