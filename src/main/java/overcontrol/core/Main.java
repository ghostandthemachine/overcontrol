/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.core;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author jon
 */
public class Main {

    public Main() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("Scenario interface tests");
        MasterPanel master = new MasterPanel();
        frame.add(master);
    }
}
