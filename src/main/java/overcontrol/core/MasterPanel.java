package overcontrol.core;

import com.sun.scenario.scenegraph.JSGPanel;
import com.sun.scenario.scenegraph.SGGroup;
import java.awt.Color;
import javax.swing.Timer;
import overcontrol.synth.TestSynth;
import overcontrol.rhythm.StepSequencer.AdvancedStepSequencer;

/**
 *
 * @author Jon Rose
 * 3-28-10
 */
public class MasterPanel extends JSGPanel {

    SGGroup root = new SGGroup();
    public static double mouseX;
    public static double mouseY;
    public static double lastX;
    public static double lastY;
    public static double xVel;
    public static double yVel;
    public static boolean mousePressed = false;
    public static boolean mouseDragged = false;
    private Timer timer;
    private float speed = (float) ((1.0 / 120.0) * 60000.0);
    private int counter;

    public MasterPanel() {

        TestSynth[] synths = {
            new TestSynth(),
            new TestSynth(),
            new TestSynth(),
            new TestSynth(),
            new TestSynth(),
            new TestSynth(),
            new TestSynth(),
            new TestSynth(),
        };


        AdvancedStepSequencer seq = new AdvancedStepSequencer(20, 20, 16, 8);

        //set current synths being triggered by passing an array or (id, synth)
        seq.setSynth(synths);

        //add to this jsg panel
        root.add(seq);
        
        setScene(root);
        setBackground(Color.white);
    }
}
