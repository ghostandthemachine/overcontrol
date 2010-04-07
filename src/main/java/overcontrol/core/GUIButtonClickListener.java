/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.core;

import com.sun.scenario.scenegraph.event.SGMouseAdapter;
import overcontrol.rythm.stepsequencer.AdvancedStepSequencer;

/**
 *
 * @author Jon
 */
public class GUIButtonClickListener extends SGMouseAdapter {

    public GUIButton parent;
    public AdvancedStepSequencer advSeq;
    private int row;
    private int column;
    private int ID;

    public GUIButtonClickListener(GUIButton c, int col, int r) {
        parent = c;
        column = col;
        row = r;
    }

    public GUIButtonClickListener(AdvancedStepSequencer c, int col, int r) {
        advSeq = c;
        column = col;
        row = r;
    }

    public GUIButton getParent() {
        return parent;
    }

    public AdvancedStepSequencer getSequencer() {
        return advSeq;
    }

    public int getID() {
        return ID;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}

