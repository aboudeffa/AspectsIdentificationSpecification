package limite;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;

public class FenetreRapport {

	JFrame frameRapport;

	/**
	 * Create the application.
	 */
	public FenetreRapport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameRapport = new JFrame("Statement");
		frameRapport.setSize(600, 500);
		frameRapport.setLocationRelativeTo(frameRapport);
		frameRapport.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 584, 381);
		frameRapport.getContentPane().add(tabbedPane);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scp = new JScrollPane(textArea);
		scp.setName("Recursifs conflicts statement");
		scp.setBounds(0, 0, 590, 353);
		tabbedPane.add(scp);
		
		JTextArea textArea_1 = new JTextArea();
		JScrollPane scp_1 = new JScrollPane(textArea_1);
		scp_1.setName("Interaction statement");
		scp_1.setBounds(0, 0, 590, 353);
		tabbedPane.add(scp_1);
		
		JTextArea textArea_2 = new JTextArea();
		JScrollPane scp_2 = new JScrollPane(textArea_2);
		scp_2.setName("Order conflicts statement");
		scp_2.setBounds(0, 0, 590, 353);
		tabbedPane.add(scp_2);
		
		JButton button = new JButton("Close");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameRapport.dispose();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(230, 405, 130, 30);
		frameRapport.getContentPane().add(button);
		
		frameRapport.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					textArea.read(new FileReader("FileConflitsRecursifs.txt"), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					textArea_1.read(new FileReader("FileInteractions.txt"), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					textArea_2.read(new FileReader("FileConflitsOrdre.txt"), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
