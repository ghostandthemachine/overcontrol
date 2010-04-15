package overcontrol.rhythm;

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

    public AdvancedStepSequencer(float tx, float ty, float width, float  height, int tsteps, int ttracks) {
        super(tx, ty, width - (width/8), 150, tsteps, ttracks, width/8, 6, width/8, 10);
        nTracks = ttracks;
        nSteps = tsteps;
        this.addComponent(createTrackSelectorInterface());
        ResolutionDial resoultionDial = new ResolutionDial(this.getX() + this.getWidth() - 40, this.getY() + 16, 26, this);
        resoultionDial.setBaseShapeOpacity(0.3f);
        addResolutionLabel();
        addComponent(resoultionDial);
        createButtons();
   
        
        //     this.add(createPresetInterface());

        createLeds();
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
        trigger = new GUIButton(tx, ty, 20, 20, "trigger") {
            private boolean toggle = false;

            @Override
            public void clicked() {
                if (toggle) {
                    toggle = false;
                    trigger.setOff();
                } else {
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

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < (numPresetButtons / 2); j++) {
                double tx = 3 + (i * (bw + 3)) + this.getX();
                double ty = 20 + (j * (bh + 3)) + this.getY();
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
                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < numPresetButtons / 2; j++) {
                                    if (i != this.getID()) {
                                        presetButtons[i][j].setOff();
                                    }
                                }
                            }
                        }
                    }
                });
                Color presetBaseColor = new Color(175,175,175);
                presetButtons[i][j].setBaseColor(presetBaseColor);
                SGText id = new SGText();
                id.setText(Integer.toString(i * j));
                id.setFont(new Font("helvetica",Font.BOLD,12));
                id.setDrawPaint(Color.white);

                double tbx = presetButtons[i][j].getX() + 3;
                double tby = presetButtons[i][j].getY() + 3;
                id.setLocation(new Point2D.Double(tbx, tby));

                presetButtons[i][j].addIndicator(id);

                group.add(presetButtons[i][j]);
            }
        }
        return group;
    }

    public SGGroup createTrackSelectorInterface() {
        SGGroup group = new SGGroup();
        trackSelectionButtons = new GUIButton[nTracks];
        System.out.println(this.getStepShape().getBounds());
        for (int i = 0; i < nTracks; i++) {
            final int id = i;

            double bw = this.getStepShape().getWidth() * 0.8;
            double bh = this.getStepShape().getHeight() * 0.8;
            double bx = this.getX() + this.getxStepOffset() - (bw + bw/2);
            double by = this.getY();
            by += (i * this.getStepShape().getHeight());

            trackSelectionButtons[i] = new GUIButton(bx, by, bw, bh, Integer.toString(0));
            trackSelectionButtons[i].addMouseListener(new SelectionButtonListener(this) {

                private boolean toggle = false;

                @Override
                public void mouseClicked(MouseEvent e, SGNode node) {
                    if (trackSelectionButtons[id].isOn()) {
                        trackSelectionButtons[id].setOff();
                        sequencer.unfocusTrack(id);
                    } else {
                        trackSelectionButtons[id].setOn();
                        sequencer.selectFocusTrack(id);
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

        double textX = this.getGlobalCoordinate().getX() + this.getWidth() - 46;
        double textY = this.getGlobalCoordinate().getY() + 55;

        SGText resolutionLabel = new SGText();
        resolutionLabel.setFont(new Font("helvitica", Font.PLAIN, 9));
        resolutionLabel.setFillPaint(Color.white);
        resolutionLabel.setLocation(new Point2D.Double(textX, textY));
        resolutionLabel.setText("resolution");

        this.addComponent(resolutionLabel);
    }

    private void createLeds() {
        leds = new LED[nSteps];
        for (int i = 0; i < nSteps; i++) {
            double radius = this.getStepShape().getWidth() / 3;
            double tx = this.getX() + this.getXOffset() + (this.getStepShape().getWidth() / 4);
            tx += i * (this.getStepShape().getWidth() + 0.275);
            double ty = this.getY() + this.getHeight() - radius;
            leds[i] = new LED(tx, ty, radius);
            leds[i].setLedOnColor(Color.GREEN);
            leds[i].setLedOffColor(new Color(0, 255, 0, 50));
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
