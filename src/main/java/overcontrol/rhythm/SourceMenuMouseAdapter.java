/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.rhythm;

import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.event.SGMouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;

/**
 *
 * @author Jon
 */
class SourceMenuMouseAdapter extends SGMouseAdapter {

    SourceMenuButton button;

    public SourceMenuMouseAdapter(SourceMenuButton button) {
        this.button = button;


    }

    @Override
    public void mousePressed(MouseEvent e, SGNode node) {
        super.mousePressed(e, node);
        button.showMenu(e.getComponent(), e.getX(), e.getY());

    }
}
