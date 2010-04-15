package overcontrol.rhythm.StepSequencer;

import com.sun.scenario.animation.Clip;
import com.sun.scenario.scenegraph.SGGroup;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import com.sun.scenario.scenegraph.SGShape;
import com.sun.scenario.scenegraph.fx.FXShape;
import overcontrol.core.Tools;

public class Step extends SGGroup {

    float x;
    float y;
    float w;
    float h;
    float r;
    float defaultVelocity = 100;
    float velocity = 0;
    int counter = 0;
    private boolean alive = false;
    boolean isAlive;
    StepSequencer parent;
    FXShape step = new FXShape();
    public FXShape velocityStep = new FXShape();
    FXShape hitStep = new FXShape();
    //Colors for base shape
    Color stepFillColor = new Color(150, 150, 150, 200);
    Color stepCountColor = Color.ORANGE;
    Color currentFillColor = stepFillColor;
    //Colors for velocity rect
    Color vStepColor = new Color(200, 255, 200);
    Color vStepCurrentFillColor = stepFillColor;
    //Colors for velocity rect
    Color hitColor = Color.white;
    private final int trackID;
    private final int stepID;
    private boolean isOn = false;
    int delayTime;
    Clip hardFadeOut;
    Clip softFadeOut;

    public Step(StepSequencer s, double tx, double ty, float tw, float th, float tr, int ti, int si) {
        x = (float) tx;
        y = (float) ty;
        w = tw;
        h = th;
        r = tr;
        trackID = ti;
        stepID = si;
        parent = s;

        delayTime = 200;

        //Set the animation fade out length to the same duration as a time between steps
        hardFadeOut = Clip.create(delayTime, hitStep, "opacity", 1f, 0f);
        softFadeOut = Clip.create(delayTime, hitStep, "opacity", 0.3f, 0f);

        add(step);
        add(velocityStep);
        add(hitStep);


        hitStep.setShape(new RoundRectangle2D.Float(x, y, w, h, r, r));
        hitStep.setFillPaint(hitColor);
        hitStep.setMode(SGShape.Mode.FILL);
        hitStep.setAntialiasingHint(RenderingHints.VALUE_ANTIALIAS_ON);
        hitStep.setOpacity(0);

        velocityStep.setShape(new RoundRectangle2D.Float(x, y, w, h, r, r));
        velocityStep.setFillPaint(vStepColor);
        velocityStep.setMode(SGShape.Mode.FILL);
        velocityStep.setAntialiasingHint(RenderingHints.VALUE_ANTIALIAS_ON);


        step.setShape(new RoundRectangle2D.Float(x, y, w, h, r, r));
        step.setFillPaint(stepFillColor);
        step.setMode(SGShape.Mode.STROKE_FILL);
        step.setDrawStroke(new BasicStroke(1.0f));
        step.setDrawPaint(Color.GRAY);
        step.setAntialiasingHint(RenderingHints.VALUE_ANTIALIAS_ON);
        step.addMouseListener(new StepMouseListener(this));

        //set the parent default step size for use with other gui elements
        parent.setStepShape(new RoundRectangle2D.Double(0, 0, w, h, r, r));

    }

    SGGroup getStepShape() {
        return this;
    }

    public void hit() {

        if (isAlive) {
            hardFadeOut.start();
        } else {
            softFadeOut.start();
        }
    }

    public void stepCountOff() {
    }

    public StepSequencer getParentSequencer() {
        return parent;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setVelocityToZero() {
        velocity = 0;
        updateParentVelocityArray(velocity);
        setStepOff();
    }

    //update differs from set in that it doesn't update the parent array to avoid overflow
    public void setVelocity(float f) {
        velocity = (float) Tools.constrain(f, 0.0, 1.0);
        updateParentVelocityArray(velocity);
        if(velocity > 0){
            setStepOn();
        } else {
            setStepOff();
        }
    }

    public SGGroup getGroup() {
        return this;
    }

    public float getVelocity() {
        return velocity;
    }

    private void updateParentVelocityArray(float velocity) {
        parent.updateVelocityArray(trackID, stepID, velocity);
    }

    void setStepOn() {
        isAlive = true;
        velocity = parent.getCurrentDefaultVelocity();

        velocityStep.setOpacity(Tools.constrain(velocity, 0.5f, 1.0f));
        velocityStep.setShape(new RoundRectangle2D.Float(x, y, w, h, r, r));
        velocityStep.setFillPaint(vStepColor);

        updateParentVelocityArray(velocity);
    }

    void setStepOff() {
        isAlive = false;
        velocity = 0;

        velocityStep.setOpacity(0);

        updateParentVelocityArray(velocity);
    }

    public void setAlive(boolean b) {
        isAlive = true;
    }

    public void setDelay(int d) {
        delayTime = d * 2;
        //Set the animation fade out length to the same duration as a time between steps
        hardFadeOut = Clip.create(delayTime, hitStep, "opacity", 1f, 0f);
        softFadeOut = Clip.create(delayTime, hitStep, "opacity", 0.3f, 0f);
    }
}
