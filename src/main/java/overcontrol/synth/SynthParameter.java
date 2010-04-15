/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.synth;

/**
 *
 * @author Jon Rose
 */
public class SynthParameter implements ISynthParameter {

    String name;
    float start;
    float end;
    float step;
    float defaultValue;

    public SynthParameter(String name, float start, float end, float step, float defaultValue) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.step = step;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getStart() {
        return start;
    }

    @Override
    public float getEnd() {
        return end;
    }

    @Override
    public float getStep() {
        return step;
    }

    @Override
    public float getDefaultValue() {
        return defaultValue;
    }
}
