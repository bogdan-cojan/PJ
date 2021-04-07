package pachet;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	DefaultListModel<String> dm = new DefaultListModel<String>();
	private JList<String> list;
	void addList(String nume) {
		dm.addElement(nume);
		list.setModel(dm);
	}

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
		setTitle("Formatii Muzica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					addList(textField.getText());
					textField.setText("");
				}
			}
		});
		textField.setBounds(0, 0, 436, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		list = new JList<String>();
		list.setBounds(0, 20, 436, 202);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("Sterge");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndices().length > 0) {
					int[] indici = list.getSelectedIndices();
					for(int i=indici.length-1;i>=0;i--) {
						dm.removeElementAt(indici[i]);
					}
				}
			}
		});
		btnNewButton.setBounds(0, 232, 436, 21);
		contentPane.add(btnNewButton);
	}
}
