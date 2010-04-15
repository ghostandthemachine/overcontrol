package overcontrol.transport;

import com.sun.scenario.scenegraph.SGAbstractShape.Mode;
import com.sun.scenario.scenegraph.SGGroup;
import com.sun.scenario.scenegraph.SGText;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import overcontrol.core.GUIComponent;
import overcontrol.core.MasterTimer;

public class ValueDisplay extends GUIComponent {
    SGText text = new SGText();

    public ValueDisplay(double tx, double ty, double th, double tw) {
        super(tx, ty, tw, th);
        super.setBaseShapeToRectangle();

        this.add(createDisplayText());

    }

    private SGText createDisplayText() {
        text.setText("0.0");
        text.setFont(new Font("Arial", Font.BOLD, 21));
        text.setFillPaint(Color.white);
        text.setMode(Mode.FILL);
        text.setLocation(new Point2D.Double(this.getX() + 5, this.getY() + 18));
        return text;
    }

    public void setText(String string) {
        text.setText(string);
    }
}
