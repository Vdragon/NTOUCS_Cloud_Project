
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class TemperatureConverter extends JFrame {
	private JPanel contentPane;
	private JRadioButton fahrenheit1;
	private JRadioButton fahrenheit2;
	private JRadioButton celsius1;
	private JRadioButton celsius2;
	private JRadioButton myTemp1;
	private JRadioButton myTemp2;

	private JTextField textFieldFrom;
	private JTextField textFieldTo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemperatureConverter frame = new TemperatureConverter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TemperatureConverter() {
		setTitle("Temperature Convertion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		setContentPane(contentPane);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());
		fahrenheit1 = new JRadioButton("Fahrenheit");
		fahrenheit2 = new JRadioButton("Fahrenheit");
		celsius1 = new JRadioButton("Celsius");
		celsius2 = new JRadioButton("Celsius");
		myTemp1 = new JRadioButton("MyTemp");
		myTemp2 = new JRadioButton("MyTemp");

		ButtonGroup g1 = new ButtonGroup();
		g1.add(fahrenheit1);
		g1.add(celsius1);
		g1.add(myTemp1);

		ButtonGroup g2 = new ButtonGroup();
		g2.add(fahrenheit2);
		g2.add(celsius2);
		g2.add(myTemp2);

		fahrenheit1.setSelected(true);
		fahrenheit2.setSelected(true);

		GridBagConstraints c = new GridBagConstraints();

		JLabel t1 = new JLabel("Convert from:");
		JLabel t2 = new JLabel("Enter Numeric Temperature:");
		JLabel t3 = new JLabel("Convert to:");
		JLabel t4 = new JLabel("Comparable Temperature is:");

		textFieldFrom = new JTextField();
		textFieldTo = new JTextField();
		textFieldTo.setEditable(false);

		setActionListener();
		
		c.weighty = 1;
		c.weightx = 1;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(t1, c);
		c.gridwidth = 1;
		c.weightx = 3;
		c.gridy = 1;
		contentPane.add(fahrenheit1, c);
		c.gridx = 1;
		contentPane.add(celsius1, c);
		c.gridx = 2;
		contentPane.add(myTemp1, c);
		c.gridwidth = 3;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 2;
		contentPane.add(t2, c);
		c.gridy = 3;
		contentPane.add(textFieldFrom, c);
		c.gridy = 4;
		contentPane.add(t3, c);
		c.gridwidth = 1;
		c.weightx = 3;
		c.gridy = 5;
		contentPane.add(fahrenheit2, c);
		c.gridx = 1;
		contentPane.add(celsius2, c);
		c.gridx = 2;
		contentPane.add(myTemp2, c);
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 3;
		c.weightx = 1;
		contentPane.add(t4, c);
		c.gridy = 7;
		contentPane.add(textFieldTo, c);

	}

	/**
	 * 設定各btn與textField change事件
	 */
	private void setActionListener() {
		textFieldFrom.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				calculate();
			}
		});

		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		};

		fahrenheit1.addActionListener(l);
		fahrenheit2.addActionListener(l);
		celsius1.addActionListener(l);
		celsius2.addActionListener(l);
		myTemp1.addActionListener(l);
		myTemp2.addActionListener(l);
	}

	/**
	 * 溫度轉換計算
	 */
	private void calculate() {
		double temperatureFrom = 0;
		try {
			temperatureFrom = Double.parseDouble(textFieldFrom.getText());
		} catch (Exception e1) {
			textFieldTo.setText("NAN");
			return;
		}

		double tmpF = 0, tmpC = 0, tmpMy = 0;

		if (fahrenheit1.isSelected()) {
			tmpF = temperatureFrom;
			tmpC = 5.0 / 9 * (temperatureFrom - 32);
			tmpMy = (tmpF + tmpC) * 0.5;
		} else if (celsius1.isSelected()) {
			tmpF = 9.0 / 5 * temperatureFrom + 32;
			tmpC = temperatureFrom;
			tmpMy = (tmpF + tmpC) * 0.5;
		} else {
			tmpF = ((temperatureFrom * 2) + (160.0 / 9.0)) * 9.0 / 14.0;
			tmpC = 5.0 / 9 * (tmpF - 32);
			tmpMy = temperatureFrom;
		}

		if (fahrenheit2.isSelected())
			textFieldTo.setText("" + tmpF);
		else if (celsius2.isSelected())
			textFieldTo.setText("" + tmpC);
		else
			textFieldTo.setText("" + tmpMy);
	}

}
