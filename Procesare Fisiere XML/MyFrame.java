package pachet;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {

	private JPanel contentPane;
	private JLabel ceas = new JLabel();
	private SimpleDateFormat timeFormat;
	private String time;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setResizable(false);
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
	
	public void setTime() {
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeFormat = new SimpleDateFormat("HH:mm:ss");
				time = timeFormat.format(Calendar.getInstance().getTime());
				ceas.setText(time);
			}
		});
		timer.start();
	}
	
	static DefaultTreeModel treeModel = new DefaultTreeModel(null);
	
	public static void recursiv(Node child, DefaultMutableTreeNode parent) {
		if(child.getTextContent().trim().compareTo("") != 0)
			if(child.getClass().getSimpleName().compareTo("DeferredElementImpl") == 0){
				Element e = (Element)child;
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(e.getTagName());
				parent.add(node);

				if(e.hasChildNodes()){
					NodeList list = e.getChildNodes();
					for(int i=0;i<list.getLength();i++){
						recursiv(list.item(i),node);
					}
				}
			}else if(child.getClass().getSimpleName().compareTo("DeferredTextImpl") == 0){
				Text t = (Text)child;
				String textContent = t.getTextContent();
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(textContent);
				parent.add(node);
			}
	}
	
	public static void xmlConstruct(String root, Element n) {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root);
		treeModel = new DefaultTreeModel(rootNode);

		if(n.hasChildNodes()) {
			@SuppressWarnings("unused")
			DefaultMutableTreeNode r = new DefaultMutableTreeNode(n.getTagName());
			NodeList aux = n.getChildNodes();
			
			for (int i=0;i<aux.getLength();i++) 
				recursiv(aux.item(i), rootNode);	
		}
	}

	public MyFrame() {
		setTitle("Procesare XML");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		timeFormat = new SimpleDateFormat("HH:mm:ss");
		time = timeFormat.format(Calendar.getInstance().getTime());
		ceas.setBounds(361, 10, 75, 25);
		contentPane.add(ceas);
		ceas.setText(time);
		setTime();
		
		JTree treeXML = new JTree();
		treeXML.setBounds(10, 43, 416, 210);
		
		JButton btnNewButton = new JButton("Open XML");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				File workingDirectory = new File(System.getProperty("user.dir"));
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
						"XML Documents (*.xml)", "xml");
				chooser.setDialogTitle("Open XML Files");
				chooser.setCurrentDirectory(workingDirectory);
				chooser.setFileFilter(xmlfilter);
				
				int returnVal = chooser.showOpenDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					String fileName = chooser.getSelectedFile().getName();
					
					try {
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						Document document = builder.parse(fileName);
						
						String name = fileName.substring(0, fileName.length() - 4);
						Element elt=document.getDocumentElement();
						
						xmlConstruct(name, elt);
						treeXML.setModel(treeModel);
						
					} catch(Exception e1) {
						System.out.println(e1.toString());
					}
				}
			}
		});
		btnNewButton.setBounds(20, 12, 110, 21);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 43, 416, 210);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(treeXML);
	}
}