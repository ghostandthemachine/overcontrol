/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.rhythm;

import com.sun.scenario.scenegraph.SGComponent;
import com.sun.scenario.scenegraph.SGText;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.geom.Point2D;
import javax.swing.JPopupMenu;
import overcontrol.core.GUIButton;

/**
 *
 * @author Jon
 */
public class SourceMenuButton extends GUIButton {

    private SGText label = new SGText();
    private JPopupMenu menu = new JPopupMenu("source");
    private SGComponent menuComp = new SGComponent();
    private String[] args = {"soundsi", "guiat", "bass", "drums", "airguiate"};
    private SourceMenuItem currentItemOver;
    private boolean isOverItem = false;

    public SourceMenuButton(double tx, double ty, double tw, double th, int id) {
        super(tx, ty, tw, th, "source_menu " + Integer.toString(id));

        label.setText("source");
        label.setFillPaint(Color.white);
        label.setFont(new Font("helvetica", Font.PLAIN, 12));
        label.setLocation(new Point2D.Double(x, y + height * 0.9));
        label.setVerticalAlignment(SGText.VAlign.BOTTOM);

        this.setWidth(label.getBounds().getWidth());

        this.addIndicator(label);
        addItems();

        menuComp.setComponent(menu);

        this.add(menuComp);
        this.addMouseListener(new SourceMenuMouseAdapter(this));

    }

    public void setItems(String[] list) {
    }

    public void addItems() {
        for (int i = 0; i < args.length - 1; i++) {
            menu.add(new SourceMenuItem(args[i], this));
        }
    }

    void showMenu(Component component, int x, int y) {
        menu.show(component, (int) this.x, (int) (this.y + this.height));
        menuComp.setVisible(true);
    }

    void hideMenu() {
        menuComp.setVisible(false);
        menu.setVisible(false);
    }

    JPopupMenu getMenu() {
        return menu;
    }

    void setCurrentItemOver(SourceMenuItem item) {
        currentItemOver = item;
    }

    SourceMenuItem getCurrentItemOver() {
        return currentItemOver;
    }

    SourceMenuButton getParentButton() {
        return this;
    }

    void setLabel(String text) {
        label.setText(text);
    }

    boolean isOverItem() {
        return isOverItem;
    }

    void setOverItem(boolean b) {
        isOverItem = true;
    }
}
