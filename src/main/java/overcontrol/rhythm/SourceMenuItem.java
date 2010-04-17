/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.rhythm;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;

/**
 *
 * @author Jon
 */
class SourceMenuItem extends JMenuItem {

    SourceMenuButton menu;

    public SourceMenuItem(String string, SourceMenuButton menu) {
        super(string);
        this.addMouseListener(new SourceMenuMouseAdapter(this));
        this.menu = menu;
    }

    private void setMenuItemOver(SourceMenuItem item) {
        menu.setCurrentItemOver(this);
    }

    private class SourceMenuMouseAdapter extends MouseAdapter {

        SourceMenuItem item;

        private SourceMenuMouseAdapter(SourceMenuItem item) {
            this.item = item;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            menu.setCurrentItemOver(item);
            menu.setOverItem(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            menu.setOverItem(false);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseClicked(e);
            MenuElement[] path = MenuSelectionManager.defaultManager().getSelectedPath();
            if (menu.isOverItem()) {
                menu.getParentButton().setLabel(menu.getCurrentItemOver().getText());
            }
            System.out.println(menu.getCurrentItemOver().getText());

        }
    }
}
