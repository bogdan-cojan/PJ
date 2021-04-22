package pachet;

import java.sql.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	private JPanel contentPane;

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

	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_1;

	int adauga = 0;
	int editat = 0;
	
	/**
	 * Create the frame.
	 */
	public MyFrame() throws SQLException {
		setTitle("Tabel MySQL - Persoane");
		String url = "jdbc:mysql://localhost:3306/test";
		Statement sql;
		ResultSet rs;
		Connection con = DriverManager.getConnection (url, "admin", "admin");
		sql = con.createStatement();
		sql = con.prepareStatement("select * from persoane where username=admin and password=admin",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rs = sql.executeQuery("select * from persoane");
		
		rs.first();
		int first = rs.getRow();
		rs.last();
		int size = rs.getRow();
		rs.first();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 436, 40);
		contentPane.add(toolBar);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setEnabled(false);
		Image img = new ImageIcon(this.getClass().getResource("/MoveFirst.png")).getImage();
		toolBar.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(img));
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setEnabled(false);
		Image img_1 = new ImageIcon(this.getClass().getResource("/MovePrevious.png")).getImage();
		toolBar.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(img_1));
		
		JTextField textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		toolBar.add(textField);
		textField.setText( first+"/"+size);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton();
		Image img_2 = new ImageIcon(this.getClass().getResource("/MoveNext.png")).getImage();
		toolBar.add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(img_2));
		
		JButton btnNewButton_3 = new JButton();
		Image img_3 = new ImageIcon(this.getClass().getResource("/MoveLast.png")).getImage();
		toolBar.add(btnNewButton_3);
		btnNewButton_3.setIcon(new ImageIcon(img_3));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					
					btnNewButton.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					
					btnNewButton_2.setEnabled(true);
					btnNewButton_3.setEnabled(true);
					
					textField_1.setText(String.valueOf(rs.getInt("ID")));
					textField_2.setText(rs.getString("Nume"));
					textField_3.setText(String.valueOf(rs.getInt("Varsta")));
					textField.setText(first+"/"+size);
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						if(rs.previous() && rs.getRow() != 1)
						{
							btnNewButton.setEnabled(true);
							btnNewButton_1.setEnabled(true);
							
							btnNewButton_2.setEnabled(true);
							btnNewButton_3.setEnabled(true);
							
							textField_1.setText(String.valueOf(rs.getInt("ID")));
							textField_2.setText(rs.getString("Nume"));
							textField_3.setText(String.valueOf(rs.getInt("Varsta")));
							textField.setText(rs.getRow()+"/"+size);
						}
						else {
							rs.first();
							
							btnNewButton.setEnabled(false);
							btnNewButton_1.setEnabled(false);
							
							btnNewButton_2.setEnabled(true);
							btnNewButton_3.setEnabled(true);
							
							textField_1.setText(String.valueOf(rs.getInt("ID")));
							textField_2.setText(rs.getString("Nume"));
							textField_3.setText(String.valueOf(rs.getInt("Varsta")));
							textField.setText(rs.getRow()+"/"+size);
						}
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.next() && rs.getRow() != size) {
						btnNewButton.setEnabled(true);
						btnNewButton_1.setEnabled(true);
						
						btnNewButton_2.setEnabled(true);
						btnNewButton_3.setEnabled(true);
						
						textField_1.setText(String.valueOf(rs.getInt("ID")));
						textField_2.setText(rs.getString("Nume"));
						textField_3.setText(String.valueOf(rs.getInt("Varsta")));
						textField.setText(rs.getRow()+"/"+size);
					}
					else {
						rs.last();
						
						btnNewButton.setEnabled(true);
						btnNewButton_1.setEnabled(true);
						
						btnNewButton_2.setEnabled(false);
						btnNewButton_3.setEnabled(false);
						
						textField_1.setText(String.valueOf(rs.getInt("ID")));
						textField_2.setText(rs.getString("Nume"));
						textField_3.setText(String.valueOf(rs.getInt("Varsta")));
						textField.setText(rs.getRow()+"/"+size);
					}
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.last();
					
					btnNewButton.setEnabled(true);
					btnNewButton_1.setEnabled(true);
					
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
					
					textField_1.setText(String.valueOf(rs.getInt("ID")));
					textField_2.setText(rs.getString("Nume"));
					textField_3.setText(String.valueOf(rs.getInt("Varsta")));
					textField.setText(size+"/"+size);
					}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_4 = new JButton();
		Image img_4 = new ImageIcon(this.getClass().getResource("/Add.png")).getImage();
		toolBar.add(btnNewButton_4);
		btnNewButton_4.setIcon(new ImageIcon(img_4));
		
		JButton btnNewButton_5 = new JButton();
		Image img_5 =new ImageIcon(this.getClass().getResource("/Edit.png")).getImage();
		toolBar.add(btnNewButton_5);
		btnNewButton_5.setIcon(new ImageIcon(img_5));
		
		JButton btnNewButton_6 = new JButton();
		Image img_6 =new ImageIcon(this.getClass().getResource("/Delete.png")).getImage();
		toolBar.add(btnNewButton_6);
		btnNewButton_6.setIcon(new ImageIcon(img_6));
		
		JButton btnNewButton_7 = new JButton();
		Image img_7 =new ImageIcon(this.getClass().getResource("/find.JPG")).getImage();
		toolBar.add(btnNewButton_7);
		btnNewButton_7.setIcon(new ImageIcon(img_7));
		
		JButton btnNewButton_8 = new JButton();
		Image img_8 =new ImageIcon(this.getClass().getResource("/save.JPG")).getImage();
		toolBar.add(btnNewButton_8);
		btnNewButton_8.setIcon(new ImageIcon(img_8));
		btnNewButton_8.setEnabled(false);
		
		JButton btnNewButton_9 = new JButton();
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.getRow() != 1) {
						btnNewButton.setEnabled(true);
						btnNewButton_1.setEnabled(true);
					}
					else
					{
						btnNewButton.setEnabled(false);
						btnNewButton_1.setEnabled(false);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				try {
					if(rs.getRow() != size)
					{
						btnNewButton_2.setEnabled(true);
						btnNewButton_3.setEnabled(true);
					}
					else
					{
						btnNewButton_2.setEnabled(false);
						btnNewButton_3.setEnabled(false);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				btnNewButton_4.setEnabled(true);
				btnNewButton_5.setEnabled(true);
				btnNewButton_6.setEnabled(true);
				btnNewButton_7.setEnabled(true);
				btnNewButton_8.setEnabled(false);
				btnNewButton_9.setEnabled(false);
				
				textField_1.setEditable(false);
				try {
					textField_1.setText(String.valueOf(rs.getInt("ID")));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				textField_2.setEditable(false);
				try {
					textField_2.setText(rs.getString("Nume"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				textField_3.setEditable(false);
				try {
					textField_3.setText(String.valueOf(rs.getInt("Varsta")));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				adauga = 0;
				editat = 0;
			}
		});
		Image img_9 =new ImageIcon(this.getClass().getResource("/undo.JPG")).getImage();
		toolBar.add(btnNewButton_9);
		btnNewButton_9.setIcon(new ImageIcon(img_9));
		btnNewButton_9.setEnabled(false);
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
				btnNewButton_4.setEnabled(false);
				btnNewButton_5.setEnabled(false);
				btnNewButton_6.setEnabled(false);
				btnNewButton_7.setEnabled(false);
				btnNewButton_8.setEnabled(true);
				btnNewButton_9.setEnabled(true);
				
				textField_1.setEditable(true);
				textField_1.setText("");
				
				textField_2.setEditable(true);
				textField_2.setText("");
				
				textField_3.setEditable(true);
				textField_3.setText("");
				
				adauga = 1;
			}
		});
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
				btnNewButton_4.setEnabled(false);
				btnNewButton_5.setEnabled(false);
				btnNewButton_6.setEnabled(false);
				btnNewButton_7.setEnabled(false);
				btnNewButton_8.setEnabled(true);
				btnNewButton_9.setEnabled(true);
				
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				
				editat = 1;
			}
		});
		
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"OK","CANCEL"};
				int sterg = JOptionPane.showOptionDialog(null, "Sunteti sigur ca doriti sa stergeti aceasta inregistrare din baza de date ?", "Stergere", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
	
				switch(sterg) {
				case JOptionPane.YES_OPTION:
					try {
						int id_curent = rs.getInt("ID");
						String url = "jdbc:mysql://localhost:3306/test";
						
						PreparedStatement sql_temp = null;
						Connection con = null;
						
						con = DriverManager.getConnection(url, "admin", "admin");
						sql_temp = con.prepareStatement("delete from persoane where ID = ?");
						
						int sters = sql_temp.executeUpdate("delete from persoane where ID = '"+id_curent+"'");
						con.close();
						
						if(sters > 0) {
							JOptionPane.showMessageDialog(null, "Stergere cu succes !" + "\n"+ "Reporniti aplicatia pentru o actualizare a aplicatiei cu baza de date.", "Succes", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Eroare la stergere !", "Eroare", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
				break;
				}
			}
		});
		
		btnNewButton_7.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String cautare = JOptionPane.showInputDialog(null, "Dati nume: ");
			int gasit = 0;
			try {
				int previous_position = rs.getRow();
				rs.beforeFirst();
				do {
					rs.next();
					
					if(rs.isAfterLast()) {
						JOptionPane.showMessageDialog(null,"Persoana nu se afla in baza de date !", "Eroare", JOptionPane.ERROR_MESSAGE);
						rs.absolute(previous_position);
						break;
					}
					
					if(rs.getString("Nume").equals(cautare)) {
						gasit = 1;
						
						textField_1.setText(String.valueOf(rs.getInt("ID")));
						textField_2.setText(rs.getString("Nume"));
						textField_3.setText(String.valueOf(rs.getInt("Varsta")));
						textField.setText(rs.getRow()+"/"+size);
						
						if(rs.getRow() == first) {
							btnNewButton.setEnabled(false);
							btnNewButton_1.setEnabled(false);
							
							btnNewButton_2.setEnabled(true);
							btnNewButton_3.setEnabled(true);
						}
						
						if(rs.getRow() == size) {
							btnNewButton.setEnabled(true);
							btnNewButton_1.setEnabled(true);
							
							btnNewButton_2.setEnabled(false);
							btnNewButton_3.setEnabled(false);
						}
						
						if(rs.getRow() != first && rs.getRow() != size) {
							btnNewButton.setEnabled(true);
							btnNewButton_1.setEnabled(true);
							
							btnNewButton_2.setEnabled(true);
							btnNewButton_3.setEnabled(true);
						}
					}
				}while(gasit == 0);
				
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
		
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adauga == 1) {
					try {
		                String url = "jdbc:mysql://localhost:3306/test";
		                
		                PreparedStatement sql_temp = null;
		                Connection con = null;
		                
		                con = DriverManager.getConnection(url, "admin", "admin");
		                sql_temp = con.prepareStatement("insert into persoane(ID, Nume, Varsta) values(?, ?, ?)");
		                
		                int id_temp = Integer.parseInt(textField_1.getText());
		                String nume_temp = textField_2.getText();
		                int varsta_temp = Integer.parseInt(textField_3.getText());
		                	
		                sql_temp.setInt(1, id_temp);
		                sql_temp.setString(2, nume_temp);
		                sql_temp.setInt(3, varsta_temp);
		                
		                int succes = sql_temp.executeUpdate();
		                con.close();
		               
		                if(succes > 0) {
		                	JOptionPane.showMessageDialog(null, "Adaugare cu succes !" + "\n"+ "Reporniti aplicatia pentru o actualizare a aplicatiei cu baza de date.", "Succes", JOptionPane.INFORMATION_MESSAGE);
		                }
		                else
		                {
		                	JOptionPane.showMessageDialog(null, "Eroare la adaugare !", "Eroare", JOptionPane.ERROR_MESSAGE);
		                }
		                
		                btnNewButton.setEnabled(true);
						btnNewButton_1.setEnabled(true);
						btnNewButton_2.setEnabled(true);
						btnNewButton_3.setEnabled(true);
						btnNewButton_4.setEnabled(true);
						btnNewButton_5.setEnabled(true);
						btnNewButton_6.setEnabled(true);
						btnNewButton_7.setEnabled(true);
						btnNewButton_8.setEnabled(false);
						btnNewButton_9.setEnabled(false);
						
						textField_1.setEditable(false);
						textField_1.setText(String.valueOf(rs.getInt("ID")));
						
						textField_2.setEditable(false);
						textField_2.setText(rs.getString("Nume"));
						
						textField_3.setEditable(false);
						textField_3.setText(String.valueOf(rs.getInt("Varsta")));
						
						adauga = 0;
					}
		            catch (SQLException e1) {
		                e1.printStackTrace();
		            }
				}
				
				if(editat == 1) {
					try {
						int id_curent = rs.getInt("ID");
						String url = "jdbc:mysql://localhost:3306/test";
						
						PreparedStatement sql_temp = null;
				        Connection con = null;
				        
				        con = DriverManager.getConnection(url, "admin", "admin");
				        sql_temp = con.prepareStatement("update persoane set ID = ?, Nume = ?, Varsta = ? where ID = ?");
				        
				        int id_temp = Integer.parseInt(textField_1.getText());
				        String nume_temp = textField_2.getText();
				        int varsta_temp = Integer.parseInt(textField_3.getText());
				        
				        int editat_succes = sql_temp.executeUpdate("update persoane set ID = '"+id_temp+"', Nume = '"+nume_temp+"', Varsta = '"+varsta_temp+"' where ID = '"+id_curent+"'");
				        con.close();
				        
				        if(editat_succes > 0) {
				        	JOptionPane.showMessageDialog(null, "Editare cu succes !" + "\n"+ "Reporniti aplicatia pentru o actualizare a aplicatiei cu baza de date.", "Succes", JOptionPane.INFORMATION_MESSAGE);
				        }
				        else
				        {
				        	JOptionPane.showMessageDialog(null, "Eroare la editare !", "Eroare", JOptionPane.ERROR_MESSAGE);
				        }
				        
				        btnNewButton.setEnabled(true);
				        btnNewButton_1.setEnabled(true);
				        btnNewButton_2.setEnabled(true);
				        btnNewButton_3.setEnabled(true);
				        btnNewButton_4.setEnabled(true);
				        btnNewButton_5.setEnabled(true);
				        btnNewButton_6.setEnabled(true);
				        btnNewButton_7.setEnabled(true);
				        btnNewButton_8.setEnabled(false);
				        btnNewButton_9.setEnabled(false);
				        
				        textField_1.setEditable(false);
				        textField_1.setText(String.valueOf(id_temp));
				        
				        textField_2.setEditable(false);
				        textField_2.setText(nume_temp);
				        
				        textField_3.setEditable(false);
				        textField_3.setText(String.valueOf(varsta_temp));
				        
				        editat = 0;
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(71, 74, 45, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nume:");
		lblNewLabel_1.setBounds(71, 116, 45, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Varsta:");
		lblNewLabel_2.setBounds(71, 166, 45, 19);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(126, 71, 232, 25);
		contentPane.add(textField_1);
		textField_1.setText(String.valueOf(rs.getInt("ID")));
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(126, 113, 232, 25);
		contentPane.add(textField_2);
		textField_2.setText(rs.getString("Nume"));
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(126, 163, 232, 25);
		contentPane.add(textField_3);
		textField_3.setText(String.valueOf(rs.getInt("Varsta")));
		textField_3.setColumns(10);
		
	}

}
