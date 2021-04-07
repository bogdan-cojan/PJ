package pachet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
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
	public MyFrame() {
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Operand 1");
		lblNewLabel.setBounds(10, 10, 60, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Operand 2");
		lblNewLabel_1.setBounds(10, 36, 60, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(80, 7, 208, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 33, 208, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Adunare");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op1 = 0,op2 = 0;
				try {
					op1 = Integer.parseInt(textField.getText());
					op2 = Integer.parseInt(textField_1.getText());
				}
				catch(NumberFormatException nf){
					JOptionPane.showMessageDialog(null, "Operand 1 si Operand 2 trebuie sa fie numere !");
				}
				textField_2.setText(String.valueOf(op1+op2));
			}
		});
		btnNewButton.setBounds(90, 62, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Scadere");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op1 = 0, op2 = 0;
				try {
					op1 = Integer.parseInt(textField.getText());
					op2 = Integer.parseInt(textField_1.getText());
				}
				catch(NumberFormatException nf) {
					JOptionPane.showMessageDialog(null, "Operand 1 si Operand 2 trebuie sa fie numere !");
				}
				textField_2.setText(String.valueOf(op1-op2));
			}
		});
		btnNewButton_1.setBounds(203, 62, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Inmultire");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op1 = 0, op2 = 0;
				try {
					op1 = Integer.parseInt(textField.getText());
					op2 = Integer.parseInt(textField_1.getText());
				}
				catch(NumberFormatException nf) {
					JOptionPane.showMessageDialog(null, "Operand 1 si Operand 2 trebuie sa fie numere !");
				}
				textField_2.setText(String.valueOf(op1*op2));
			}
		});
		btnNewButton_2.setBounds(90, 93, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Impartire");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float op1 = 0, op2 = 0;
				try {
					op1 = Float.parseFloat(textField.getText());
					op2 = Float.parseFloat(textField_1.getText());
					if(op2 == 0) {
						JOptionPane.showMessageDialog(null, "Nu se poate imparti la 0 !");
						return;
					}
				}
				catch(NumberFormatException nf) {
					JOptionPane.showMessageDialog(null, "Operand 1 si Operand 2 trebuie sa fie numere !");
				}
				textField_2.setText(String.valueOf(op1/op2));
			}
		});
		btnNewButton_3.setBounds(203, 93, 85, 21);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_2 = new JLabel("Rezultat");
		lblNewLabel_2.setBounds(10, 135, 60, 13);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(80, 132, 208, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Sterge");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		btnNewButton_4.setBounds(109, 161, 85, 21);
		contentPane.add(btnNewButton_4);
	}
}