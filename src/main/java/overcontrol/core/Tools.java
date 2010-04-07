package overcontrol.core;

import com.sun.scenario.scenegraph.SGShape;
import com.sun.scenario.scenegraph.fx.FXShape;
import java.awt.Color;
import java.awt.Point;
import java.util.*;

public class Tools {

    public static float constrain(float value, float lowerConstraint, float upperConstraint) {
        float outputValue;
        if (value < lowerConstraint) {
            outputValue = lowerConstraint;
        } else if (value > upperConstraint) {
            outputValue = upperConstraint;
        } else {
            outputValue = value;
        }
        return outputValue;
    }

    public static float constrain(float value, float lowerConstraint) {
        float outputValue;
        if (value < lowerConstraint) {
            outputValue = lowerConstraint;
        } else {
            outputValue = value;
        }
        return outputValue;
    }

    public static double constrain(double value, double lowerConstraint, double upperConstraint) {
        double outputValue;
        if (value < lowerConstraint) {
            outputValue = lowerConstraint;
        } else if (value > upperConstraint) {
            outputValue = upperConstraint;
        } else {
            outputValue = value;
        }
        return outputValue;
    }

    public static int constrain(int value, int lowerConstraint, int upperConstraint) {
        int outputValue;
        if (value < lowerConstraint) {
            outputValue = lowerConstraint;
        } else if (value > upperConstraint) {
            outputValue = upperConstraint;
        } else {
            outputValue = value;
        }
        return outputValue;
    }

    public static int constrain(int value, int lowerConstraint) {
        int outputValue;
        if (value < lowerConstraint) {
            outputValue = lowerConstraint;
        } else {
            outputValue = value;
        }
        return outputValue;
    }

    public static float map(float value, float srcMin, float srcMax, float tgtMin, float tgtMax) {
        // Figure out how 'wide' each range is
        float scaleFactor = (tgtMax - tgtMin) / (srcMax - srcMin);
        float scaledValue = (value - srcMin) * scaleFactor + tgtMin;
        return scaledValue;
    }

    public static double map(double value, double srcMin, double srcMax, double tgtMin, double tgtMax) {
        // Figure out how 'wide' each range is
        double scaleFactor = (tgtMax - tgtMin) / (srcMax - srcMin);
        double scaledValue = (value - srcMin) * scaleFactor + tgtMin;
        return scaledValue;
    }

    public static float random(float lo, float hi) {
        Random rn = new Random();

        float n = hi - lo + 1;
        float i = rn.nextInt() % n;
        if (i < 0) {
            i = -i;
        }
        return lo + i;

    }

    public static double distance(double ax, double ay, double bx, double by) {
        double a = Math.abs(ax - bx);
        double b = Math.abs(bx - by);
        double c = Math.sqrt((a * a) + (b * b));
        return c;

    }

    public static FXShape createTriangle(GUIButton shape, int pad) {
        FXShape triangle = new FXShape();
        //top left
        Point p1 = new Point((int) (pad + shape.getX()), (int) (pad + shape.getY()));
        //right
        Point p2 = new Point((int) (shape.getWidth() - pad + shape.getX()), (int) (shape.getY() + shape.getHeight() / 2));
        //bottom left
        Point p3 = new Point((int) (pad + shape.getX()), (int) (shape.getY() + shape.getHeight() - pad));

        Triangle tri = new Triangle(p1, p2, p3);
        triangle.setShape(tri);
        return triangle;
    }
}
