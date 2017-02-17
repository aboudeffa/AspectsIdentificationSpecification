package limite;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import controle.GenerationContraintes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class FenetreContrainte {

	protected JFrame frameContrainte;
	private JTextField textField;
	private GenerationContraintes gc;

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public FenetreContrainte() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameContrainte = new JFrame("Add Constraint");
		frameContrainte.setSize(650, 220);
		frameContrainte.setLocationRelativeTo(frameContrainte);
		frameContrainte.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New constraint ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(50, 45, 110, 30);
		frameContrainte.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(175, 45, 400, 30);
		frameContrainte.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAddConsraint = new JButton("OK");
		btnAddConsraint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gc = new GenerationContraintes();
				gc.setContrainte(textField.getText());
				try {
					gc.genererContraintes();
				} catch (IOException e) {
					e.printStackTrace();
				}
				textField.setText("");
			}
		});
		btnAddConsraint.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddConsraint.setBounds(190, 118, 100, 30);
		frameContrainte.getContentPane().add(btnAddConsraint);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameContrainte.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClose.setBounds(364, 118, 100, 30);
		frameContrainte.getContentPane().add(btnClose);
	}
}
