/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.core;

import overcontrol.core.transport.Transport;
import java.awt.BorderLayout;
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
        frame.setPreferredSize(new Dimension(800, 800));
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("Scenario interface tests");
        frame.setLayout(new BorderLayout());
        MasterTimer timer = new MasterTimer();
        MasterPanel master = new MasterPanel(timer);

        Transport transport = new Transport(timer);
        transport.setPreferredSize(new Dimension(frame.getSize().width, 30));
        frame.add(master, BorderLayout.CENTER);
        frame.add(transport, BorderLayout.SOUTH);

        
    }
}
