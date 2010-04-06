package overcontrol.rythm.stepsequencer;

import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.SGText;
import com.sun.scenario.scenegraph.fx.FXShape;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.geom.Point2D;
import overcontrol.core.Triangle;
import overcontrol.core.GUIButton;
import overcontrol.core.GUIButtonClickListener;
import overcontrol.core.LED;

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

    public AdvancedStepSequencer(float tx, float ty, int tsteps, int ttracks) {
        super(tx, ty, 250, 100, tsteps, ttracks, 50, 0, 90, 10);
        nTracks = ttracks;
        nSteps = tsteps;

        this.addComponent(createTrackSelectorInterface());
//        this.addComponent(createPresetInterface());

        ResolutionDial resoultionDial = new ResolutionDial(this.getX() + this.getWidth() - 28, this.getY() + 6, 16, this);
        resoultionDial.setBaseShapeOpacity(0.3f);
        addResolutionLabel();

        addComponent(resoultionDial);

        createButtons();

        createLeds();


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

        trigger.setBaseColor(Color.lightGray);
        trigger.setOnColor(Color.white);
        trigger.addIndicator(createTriangle());
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
                    public void clicked() {
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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 nt()].on();
    }
}
