
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainClass {

  public static void main(String[] args) {
    JButton jb = new JButton("Press Me");

    jb.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent ev) {
        System.out.println("ItemEvent!");
      }
    });

    jb.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent ev) {
        System.out.println("ChangeEvent!");
      }
    });

    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(jb);
    f.pack();
    f.setVisible(true);
  }
}