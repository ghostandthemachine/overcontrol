/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.core;

import com.sun.scenario.scenegraph.JSGPanel;
import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.SGShape;
import java.awt.Color;
import javax.swing.Timer;
import overcontrol.synth.SynthControl;
import overcontrol.synth.TestSynth;
import overcontrol.midi.notesequencer.NoteScene;
import overcontrol.rythm.stepsequencer.AdvancedStepSequencer;
import overcontrol.rythm.stepsequencer.MasterTimerListener;

/**
 *
 * @author jon
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
    private SGShape listenerShape = new SGShape();
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
        seq.setBpm(120);
        
        //set current synths being triggered by passing an array or (id, synth)
        seq.setSynth(synths);

        //add to this jsg panel
        root.add(seq);

        MasterTimer masterTimer = new MasterTimer();
        masterTimer.addActionListener(new MasterTimerListener(seq, masterTimer));



        SynthControl sc = new SynthControl(new TestSynth());
        //   root.add(sc);


        NoteScene noteS = new NoteScene();
        //  root.add(ns.getComponentGroup());


        root.add(listenerShape);
        //root.add(ass.getComponentGroup());


        setScene(root);
        setBackground(Color.white);
    }
}
