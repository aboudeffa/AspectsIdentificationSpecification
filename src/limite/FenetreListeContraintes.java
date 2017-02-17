package limite;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;

public class FenetreListeContraintes {

	public JFrame frameShowConstraints;

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public FenetreListeContraintes() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frameShowConstraints = new JFrame("Show Constraints");
		frameShowConstraints.setSize(515, 420);
		frameShowConstraints.setLocationRelativeTo(frameShowConstraints);
		frameShowConstraints.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scp = new JScrollPane(textArea);
		scp.setBounds(50, 50, 400, 250);
		frameShowConstraints.getContentPane().add(scp);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameShowConstraints.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(200, 330, 130, 30);
		frameShowConstraints.getContentPane().add(btnNewButton);
		
		frameShowConstraints.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					textArea.read(new FileReader("FileContraintes.txt"), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
