package limite;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpAISF {

	JFrame frameHelpAISF;

	/**
	 * Create the application.
	 */
	public HelpAISF() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameHelpAISF = new JFrame("Help AISF");
		frameHelpAISF.setSize(448, 300);
		frameHelpAISF.setLocationRelativeTo(frameHelpAISF);
		frameHelpAISF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameHelpAISF.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aspect Identification Specification Framework");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(60, 35, 315, 30);
		frameHelpAISF.getContentPane().add(lblNewLabel);
		
		JLabel lblVersion = new JLabel("Version : 1.0");
		lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVersion.setBounds(175, 85, 80, 30);
		frameHelpAISF.getContentPane().add(lblVersion);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameHelpAISF.dispose();
			}
		});
		btnNewButton.setBounds(165, 195, 100, 30);
		frameHelpAISF.getContentPane().add(btnNewButton);
		
		JLabel lblAuthor = new JLabel("Developper : BOUDEFFA Amin");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAuthor.setBounds(125, 135, 180, 30);
		frameHelpAISF.getContentPane().add(lblAuthor);
	}
}
