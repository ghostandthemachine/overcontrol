/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.core;

import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.fx.FXShape;

/**
 *
 * @author Jon Rose
 */
public class RadioButtons extends SGGroup {

    private double x;
    private double y;
    private double width;
    private double height;
    private int columns;
    private int rows;
    private GUIButton[] buttons;
    private String SINGLE_ROW = "singleRow";
    private String TWO_ROWS = "twoRows";
    private String CUBE = "cube";
    private String layout;
    private final int nButtons;
    private FXShape radioShape;
    private String[] arguments;

    RadioButtons(double tx, double ty, double tw, double th, int col, int row, String lay) {
        x = tx;
        y = ty;
        width = tw;
        height = th;
        columns = col;
        rows = row;
        nButtons = columns * row;
        buttons = new GUIButton[nButtons];
        layout = lay;
//        radioShape = defaultShape();
        createButtons();
    }

    RadioButtons(double tx, double ty, double tw, double th, int col, int row, String lay, String[] args, SGNode shape) {
        x = tx;
        y = ty;
        width = tw;
        height = th;
        columns = col;
        rows = row;
        nButtons = columns * row;
        buttons = new GUIButton[nButtons];
        layout = lay;
        arguments = args;
        radioShape = (FXShape) shape;
        createButtons();
    }

    private void createButtons() {
        //set the shapes and locations based on the layout type specified.
        if (layout.equals(SINGLE_ROW)) {
            setSingleRow();
        } else if (layout.equals(TWO_ROWS)) {
            setTwoRows();
        } else if (layout.equals(CUBE)) {
            setCubeRows();
        }

    }

    private void setSingleRow() {
        for (int i = 0; i < nButtons; i++) {

            buttons[i].setBaseShape(radioShape);
        }
    }

    private void setTwoRows() {
    }

    private void setCubeRows() {
    }

}
