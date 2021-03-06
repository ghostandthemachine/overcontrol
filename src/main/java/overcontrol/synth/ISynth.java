/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.synth;

/**
 *
 * @author Jon
 */
public interface ISynth {

    ISynthParameter[] getParams();

    String getName();

    void play(float[] args);

    void kill();

    void control(String parameter, float value);

    void hitAt(long time, float velocity);

    void hit(float velocity);
}
