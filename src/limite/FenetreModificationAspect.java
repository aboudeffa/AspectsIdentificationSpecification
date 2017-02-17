package limite;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;

import controle.AjoutPreoccupation;
import controle.ModificationAspect;
import controle.ModificationBase;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class FenetreModificationAspect {

	protected JFrame frameModificationAspect;
	private Vector<String> aspect = new Vector<String>();
	private Vector<String> base = new Vector<String>();
	private Vector<String> advice = new Vector<String>();
	private Vector<String> point = new Vector<String>();
	private ResultSet rs, rs1, rs2, rs3, rs4;
	private AjoutPreoccupation ajpreo = new AjoutPreoccupation();
	private int codeAsp,codeB,code_sc;
	private String Modaspect,Modusecase,Modadvice,Modoperateur,Modpoint;

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public FenetreModificationAspect(int casp, int cb, String aspect, String use, String adv, String op, String p) throws SQLException {
		this.codeAsp=casp;
		this.codeB=cb;
		this.Modaspect=aspect;
		this.Modusecase=use;
		this.Modadvice=adv;
		this.Modoperateur=op;
		this.Modpoint=p;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {

		frameModificationAspect = new JFrame("Update Specification");
		frameModificationAspect.setSize(400, 475);
		frameModificationAspect.setLocationRelativeTo(frameModificationAspect);
		frameModificationAspect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameModificationAspect.getContentPane().setLayout(null);

		// *******************************************************

		aspect.addElement("Choose an aspect !");
		rs1 = ajpreo.consulterPreoccupation();
		while (rs1.next()) {
			aspect.addElement(rs1.getString(1));
		}

		base.addElement("Choose a base !");
		rs2 = ajpreo.consulterPreoccupation();
		while (rs2.next()) {
			base.addElement(rs2.getString(1));
		}

		advice.addElement("Choose an advice !");
		rs3 = ajpreo.consulterScenario();
		while (rs3.next()) {
			advice.addElement(rs3.getString(2));
		}

		point.addElement("Choose a point !");
		rs4 = ajpreo.consulterStep();
		while (rs4.next()) {
			point.addElement(rs4.getString(1));
		}
		
		// *******************************************************
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(175, 64, 150, 30);
		comboBox.setModel(new DefaultComboBoxModel(aspect));
		frameModificationAspect.getContentPane().add(comboBox);

		JLabel label = new JLabel("Aspect name");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(55, 64, 90, 30);
		frameModificationAspect.getContentPane().add(label);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(175, 119, 150, 30);
		comboBox_1.setModel(new DefaultComboBoxModel(base));
		frameModificationAspect.getContentPane().add(comboBox_1);

		JLabel lblAffectedUsecase = new JLabel("Affected usecase");
		lblAffectedUsecase.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAffectedUsecase.setBounds(25, 119, 120, 30);
		frameModificationAspect.getContentPane().add(lblAffectedUsecase);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(175, 175, 150, 30);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajpreo.setNom_s(comboBox_2.getSelectedItem().toString());
				try {
					rs3 = ajpreo.consulterIdScenario();
					if (rs3.next()) {
						code_sc = rs3.getInt(1);
						ajpreo.setCode_sc(code_sc);
					}
					/**
					 * refresh jcombobox Point
					 */
					rs4 = ajpreo.consulterStep();
					while (rs4.next()) {
						point.addElement(rs4.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		comboBox_2.setModel(new DefaultComboBoxModel(advice));
		frameModificationAspect.getContentPane().add(comboBox_2);

		JLabel lblAdvice = new JLabel("Advice");
		lblAdvice.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdvice.setBounds(93, 175, 52, 30);
		frameModificationAspect.getContentPane().add(lblAdvice);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(175, 230, 150, 30);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {
				"Choose an operator !", "Overlap.before", "Overlap.after",
				"Override", "Wrap" }));
		frameModificationAspect.getContentPane().add(comboBox_3);

		JLabel lblOperation = new JLabel("Operator");
		lblOperation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperation.setBounds(74, 230, 71, 30);
		frameModificationAspect.getContentPane().add(lblOperation);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(175, 282, 150, 30);
		comboBox_4.setModel(new DefaultComboBoxModel(point));
		frameModificationAspect.getContentPane().add(comboBox_4);

		JLabel lblAffectedPoint = new JLabel("Affected point");
		lblAffectedPoint.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAffectedPoint.setBounds(45, 282, 100, 30);
		frameModificationAspect.getContentPane().add(lblAffectedPoint);

		// *****************************************************
		
		comboBox.setSelectedItem(Modaspect);
		comboBox_1.setSelectedItem(Modusecase);
		comboBox_2.setSelectedItem(Modadvice);
		comboBox_3.setSelectedItem(Modoperateur);
		comboBox_4.setSelectedItem(Modpoint);
		
		// ****************************************************
		
		JButton btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ModificationAspect masp = new ModificationAspect();;
					ModificationBase mb = new ModificationBase();
					masp.setCodeAspect(codeAsp);
					masp.setNomAspect(Modaspect);
					masp.setAdvice(Modadvice);
					masp.setOperation(Modoperateur);
					masp.setPoint(Modpoint);
					masp.modifierAspect();
					// *****************************
					mb.setCodeBase(codeB);
					mb.setNomUsecase(Modusecase);
					mb.modifierBase();

				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		});
		btnValidate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnValidate.setBounds(93, 359, 100, 30);
		frameModificationAspect.getContentPane().add(btnValidate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameModificationAspect.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClose.setBounds(203, 359, 100, 30);
		frameModificationAspect.getContentPane().add(btnClose);
		
	}
}
