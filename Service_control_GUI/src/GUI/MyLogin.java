package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;

class MyLogin extends JFrame implements ActionListener
{
	private final JButton submitBtn;
	private final JPanel panel;
	private final JLabel usernameLabel, passwordLabel;
	private JTextField  usernameField;
	private JPasswordField passwordField;
	
	private JFrame father;
	
	MyLogin(JFrame masterForm)
	{
		super("Login form");
		setBounds(500, 300, 250, 150);
		father = masterForm;
		
		usernameLabel = new JLabel("管理者帳號:",SwingConstants.CENTER);
		usernameField = new JTextField(15);

		passwordLabel = new JLabel("管理者密碼:",SwingConstants.CENTER);
		passwordField = new JPasswordField(15);

		submitBtn =new JButton("登入");
		submitBtn.addActionListener(this);
		
		panel = new JPanel(new GridLayout(3,3,5,5));
		
		TitledBorder titled;
		titled = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "安全登入");
		titled.setTitleJustification(TitledBorder.CENTER);
		titled.setTitlePosition(TitledBorder.DEFAULT_POSITION);
		titled.setTitleColor(Color.red);
		
		panel.setBorder(titled);
		
		
		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(submitBtn);
		// to fill the GridLayout
		panel.add(new JLabel(""));
		
		
		add(panel,BorderLayout.CENTER);
		
		setTitle("雲端系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
    public void actionPerformed(ActionEvent ee)
	{
		
		
		if(usernameField.getText().equals("user")) {
			//if(new String(passwordField.getPassword()).equals("1")) {
				this.setVisible(false);				// hide myself
				father.setVisible(true);			// show my father
				this.dispose();						// clean my resource
				return;
			//}
		}
		// send an option pane that does not match
		JOptionPane.showMessageDialog(this, "帳號或密碼錯誤，請在試一次");
		this.usernameField.setText("");
		this.passwordField.setText("");
		
	}	
}
