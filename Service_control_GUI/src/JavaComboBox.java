import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JavaComboBox extends JPanel {

	private String [] comboTypes;
	private String chooseCombo;
	public JavaComboBox(String [] tmp) {
		comboTypes =tmp;
		// Create the combo box, and set 2nd item as Default
		JComboBox comboTypesList = new JComboBox(comboTypes);
		comboTypesList.setSelectedIndex(0);
		comboTypesList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbType = (JComboBox) e.getSource();
				chooseCombo =jcmbType.getSelectedItem().toString();
			}
		});
	
		add(comboTypesList, BorderLayout.NORTH);
	
	}
	public static void main(String s[]) {
		JFrame frame = new JFrame("JComboBox Usage Demo");
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		String [] tmp={"dfd","d","s","a"};
		frame.setContentPane(new JavaComboBox(tmp));
		frame.pack(); 
		frame.setVisible(true);
	}
}