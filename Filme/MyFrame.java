package pachet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

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
		setTitle("Filme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 206, 536, 157);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Film", "An Lansare", "Actori", "Genuri"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Film");
		lblNewLabel.setBounds(120, 10, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Actori");
		lblNewLabel_1.setBounds(120, 49, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(160, 7, 283, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(160, 46, 283, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("An Lansare");
		lblNewLabel_2.setBounds(201, 83, 67, 13);
		contentPane.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2015, 2015, 2020, 1));
		spinner.setBounds(278, 80, 67, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_3 = new JLabel("Genuri");
		lblNewLabel_3.setBounds(51, 104, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Comedie");
		chckbxNewCheckBox.setBounds(102, 116, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Drama");
		chckbxNewCheckBox_1.setBounds(221, 116, 93, 21);
		contentPane.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Istoric");
		chckbxNewCheckBox_2.setBounds(334, 116, 93, 21);
		contentPane.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Romantic");
		chckbxNewCheckBox_3.setBounds(158, 139, 93, 21);
		contentPane.add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Actiune");
		chckbxNewCheckBox_4.setBounds(278, 139, 93, 21);
		contentPane.add(chckbxNewCheckBox_4);
		
		JButton btnNewButton = new JButton("Adauga");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text="";
				if(chckbxNewCheckBox.isSelected()) {
					text+=chckbxNewCheckBox.getText();
					text+=" ";}
				if(chckbxNewCheckBox_1.isSelected()) {
					text+=chckbxNewCheckBox_1.getText();
					text+=" ";}
				if(chckbxNewCheckBox_2.isSelected()) {
					text+=chckbxNewCheckBox_2.getText();
					text+=" ";}
				if(chckbxNewCheckBox_3.isSelected()) {
					text+=chckbxNewCheckBox_3.getText();
					text+=" ";}
				if(chckbxNewCheckBox_4.isSelected()) {
					text+=chckbxNewCheckBox_4.getText();
					text+=" ";}
			
			String[] filme= {textField.getText(),textField_1.getText(),String.valueOf(spinner.getValue()),text};
			DefaultTableModel tabel =(DefaultTableModel) table.getModel();
			tabel.addRow(filme);
			}
		});
		btnNewButton.setBounds(51, 166, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sterge");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tabel =(DefaultTableModel) table.getModel();
				if(table.getSelectedRows().length>0) {
					int[] indici=table.getSelectedRows();
					for(int i=indici.length-1;i>=0;i--) 
						{tabel.removeRow(indici[i]);}
				}
			}
		});
		btnNewButton_1.setBounds(369, 166, 85, 21);
		contentPane.add(btnNewButton_1);
	}
}
