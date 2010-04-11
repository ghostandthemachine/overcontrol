/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package overcontrol.core;

import overcontrol.transport.Transport;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author jon rose
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
        MasterPanel master = new MasterPanel();

        Transport transport = new Transport();
        transport.setPreferredSize(new Dimension(frame.getSize().width, 30));
        frame.add(master, BorderLayout.CENTER);
        frame.add(transport, BorderLayout.SOUTH);
    }
}
