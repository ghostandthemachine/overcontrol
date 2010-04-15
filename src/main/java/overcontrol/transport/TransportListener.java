/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.transport;

import overcontrol.core.*;
import com.sun.scenario.scenegraph.event.SGMouseAdapter;

/**
 *
 * @author Jon Rose
 */
public class TransportListener extends SGMouseAdapter {

    private Transport parent;
    private GUIButton button;

    public TransportListener(Transport t, GUIButton b) {
        parent = t;
        button = b;
    }



    public Transport getParent() {
        return parent;
    }

    public GUIButton getButton(){
        return button;
    }

}