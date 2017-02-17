package limite;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.io.IOException;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import controle.AjoutAspect;
import controle.AjoutBase;
import controle.AjoutPreoccupation;
import controle.GenerationRapport;
import controle.IdentificationConflictRecursive;
import controle.IdentificationConflitsOrdre;
import controle.IdentificationInteraction;
import controle.ModificationAspect;
import controle.ModificationBase;

public class FenetrePrincipale {

	public JFrame frame;
	private JTextField textField, textField_4, textField_5, textField_6;
	private JLabel label;
	private JPanel panel_4 = new JPanel();
	private JPanel panel_5 = new JPanel();
	private JScrollPane scrollPane, scrollPane_1, scrollPane_2;
	private JTable jtable1, jtable12, jtable2, table, table_1, table_2;
	private DefaultTableModel dtm, dm1, dm12, dtm_1, dtm_2;
	private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10,
			rs11, rs12, rs13, rs14, rs15, rs16, rs17, rs18, rs19, rs20;
	private AjoutPreoccupation ajpreo = new AjoutPreoccupation();
	private AjoutAspect ajasp = new AjoutAspect();
	private AjoutBase ajb = new AjoutBase();
	private ModificationAspect masp;
	private ModificationBase mb;
	private IdentificationConflictRecursive icr = new IdentificationConflictRecursive();
	private IdentificationInteraction ii = new IdentificationInteraction();
	private IdentificationConflitsOrdre ico = new IdentificationConflitsOrdre();
	private GenerationRapport gr = new GenerationRapport();
	private Vector<String> aspect = new Vector<String>();
	private Vector<String> base = new Vector<String>();
	private Vector<String> advice = new Vector<String>();
	private Vector<String> point = new Vector<String>();
	private Vector<String> actors = new Vector<String>();
	private int n, code_sc,cod_sc, codeAsp, codeBase, indice1, indice2,indice3, indice4;
	private String cell = "";
	private JTextField textField_1;
	static int[][] matBool, matBool2, matTransitiveClosure, matTransitiveClosure2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale window = new FenetrePrincipale();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public FenetrePrincipale() throws SQLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {

		final Vector<String> entete = new Vector<String>();
		final Vector<Vector> data = new Vector<Vector>();
		final Vector<String> entete_1 = new Vector<String>();
		final Vector<Vector> data_1 = new Vector<Vector>();
		final Vector<String> entete_2 = new Vector<String>();
		final Vector<Vector> data_2 = new Vector<Vector>();
		final Vector<String> entete_3 = new Vector<String>();
		final Vector<Vector> data_3 = new Vector<Vector>();
		Vector<String> JoinPointsB = new Vector<String>();
		Vector<String> JoinPointsAsp = new Vector<String>();

		frame = new JFrame("Aspect Identification Specification");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar jmb = new JMenuBar();
		jmb.setLocation(0, 0);
		jmb.setSize(1362, 20);
		frame.getContentPane().add(jmb);

		JMenu jm = new JMenu("File");
		jm.setMnemonic(KeyEvent.VK_F);
		jmb.add(jm);

		JMenuItem jmi4 = new JMenuItem("Exit");
		jmi4.setMnemonic(KeyEvent.VK_E);
		jmi4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenu mnNewMenu = new JMenu("Analyse");
		mnNewMenu.setMnemonic(KeyEvent.VK_A);
		jmb.add(mnNewMenu);
		jm.add(jmi4);

		JMenu mnNewMenu_3 = new JMenu("Recursive conflicts identification");
		mnNewMenu.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Level 1");
		mnNewMenu_3.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Level 2");
		mnNewMenu_3.add(mntmNewMenuItem_7);

		JMenu mnNewMenu_4 = new JMenu("Interaction identification");
		mnNewMenu.add(mnNewMenu_4);

		JMenuItem mntmNewMenuItem = new JMenuItem("Level 1");
		mnNewMenu_4.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Level 2");
		mnNewMenu_4.add(mntmNewMenuItem_5);

		JMenu mnNewMenu_5 = new JMenu("Order conflicts identification");
		mnNewMenu.add(mnNewMenu_5);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Level 1");
		mnNewMenu_5.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Level 2");
		mnNewMenu_5.add(mntmNewMenuItem_6);

		JMenu mnNewMenu_1 = new JMenu("Constraints");
		mnNewMenu_1.setMnemonic(KeyEvent.VK_S);
		jmb.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Show Constraints");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FenetreListeContraintes window = new FenetreListeContraintes();
							window.frameShowConstraints.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mntmNewMenuItem_3.setMnemonic(KeyEvent.VK_P);
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_6 = new JMenu("Statement");
		jmb.add(mnNewMenu_6);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Show statement");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FenetreRapport window = new FenetreRapport();
							window.frameRapport.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_8);

		JMenu mnNewMenu_2 = new JMenu("Help");
		mnNewMenu_2.setMnemonic(KeyEvent.VK_H);
		jmb.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("About Aspect Identification Specification");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HelpAISF window = new HelpAISF();
							window.frameHelpAISF.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 20, 1360, 685);
		frame.getContentPane().add(tabbedPane);

		JPanel panel_Project = new JPanel();
		panel_Project.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Project", null, panel_Project, null);
		panel_Project.setLayout(null);

		JPanel panel_ABM = new JPanel();
		tabbedPane.addTab("Aspect Base Matrix", null, panel_ABM, null);
		panel_ABM.setLayout(null);

		JPanel panel_MPM = new JPanel();
		tabbedPane.addTab("Match Points Matrix", null, panel_MPM, null);
		panel_MPM.setLayout(null);

		JPanel panel_HP = new JPanel();
		panel_HP.setToolTipText("");
		tabbedPane.addTab("Dependency Graph", null, panel_HP, null);
		panel_HP.setLayout(null);

		// ***************************************************

		JPanel panel_12 = new JPanel();
		panel_12.setBounds(0, 0, 1199, 657);
		panel_MPM.add(panel_12);
		panel_12.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Selected cell content");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(369, 90, 145, 30);
		panel_12.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField_1.setBounds(520, 90, 450, 30);
		panel_12.add(textField_1);
		textField_1.setColumns(10);

		// ******************************************************

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 1355, 657);
		panel_ABM.add(tabbedPane_2);

		JPanel panel_11 = new JPanel();
		tabbedPane_2.addTab("Level 1", null, panel_11, null);
		panel_11.setLayout(null);

		JPanel panel_14 = new JPanel();
		tabbedPane_2.addTab("Level 2", null, panel_14, null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 1360, 660);
		panel_Project.add(tabbedPane_1);

		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("Concern", null, panel_3, null);
		panel_3.setLayout(null);

		tabbedPane_1.addTab("Aspect", null, panel_4, null);
		panel_4.setLayout(null);

		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setBounds(0, 0, 1355, 657);
		panel_HP.add(tabbedPane_3);

		JPanel panel = new JPanel();
		tabbedPane_3.addTab("Level 1", null, panel, null);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		tabbedPane_3.addTab("Level 2", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_13.setBounds(275, 100, 800, 400);
		panel.add(panel_13);
		panel_13.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(400, 30, 300, 250);
		panel_13.add(panel_2);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_15.setBounds(275, 100, 800, 400);
		panel_1.add(panel_15);
		panel_15.setLayout(null);

		JPanel panel_16 = new JPanel();
		panel_16.setBounds(400, 30, 300, 250);
		panel_15.add(panel_16);
		panel_16.setBorder(new LineBorder(new Color(0, 0, 0)));

		// ***************************************************
		/**
		 * Match point list
		 */
		JList list1 = new JList();
		list1.setModel(new DefaultComboBoxModel(JoinPointsB));

		JScrollPane scrollPane_3 = new JScrollPane(list1);
		scrollPane_3.setBounds(100, 50, 100, 150);
		panel_13.add(scrollPane_3);

		JList list2 = new JList();
		list2.setModel(new DefaultComboBoxModel(JoinPointsB));

		JScrollPane scrollPane_4 = new JScrollPane(list2);
		scrollPane_4.setBounds(100, 50, 100, 150);
		panel_15.add(scrollPane_4);

		// *******************************************

		aspect.addElement("Choose an aspect !");
		rs2 = ajpreo.consulterPreoccupation();
		while (rs2.next()) {
			aspect.addElement(rs2.getString(1));
		}

		base.addElement("Choose a base !");
		rs3 = ajpreo.consulterPreoccupation();
		while (rs3.next()) {
			base.addElement(rs3.getString(1));
		}

		advice.addElement("Choose an advice !");
		rs = ajpreo.consulterScenario();
		while (rs.next()) {
			advice.addElement(rs.getString(2));
		}

		point.addElement("Choose a point !");
		rs10 = ajpreo.consulterStep();
		while (rs10.next()) {
			point.addElement(rs10.getString(1));
		}

		rs8 = ajpreo.consulterActeurs();
		while (rs8.next()) {
			actors.addElement(rs8.getString(2));
		}
		// *************************************
		/**
		 * Jtable
		 */
		table = new JTable();
		dtm = new DefaultTableModel();
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(400, 30, 720, 260);
		panel_5.add(scrollPane);

		entete.addElement("Aspect name");
		entete.addElement("Affected Use Case");
		entete.addElement("Advice");
		entete.addElement("Operator");
		entete.addElement("Affected Point");

		rs5 = ajasp.consulterAspect();
		rs7 = ajb.consulterBase();

		while (rs5.next() && rs7.next()) {
			Vector<String> tuple = new Vector<String>();
			tuple.addElement(rs5.getString(2));
			tuple.addElement(rs7.getString(2));
			tuple.addElement(rs5.getString(3));
			tuple.addElement(rs5.getString(4));
			tuple.addElement(rs5.getString(5));
			data.addElement(tuple);
		}
		dtm.setDataVector(data, entete);
		table.setModel(dtm);

		dtm_1 = new DefaultTableModel();
		ajpreo.setNom_s("scenario1");
		rs9 = ajpreo.consulterIdScenario();
		if (rs9.next()) {
			cod_sc = rs9.getInt(1);
		}
		ajpreo.setCode_sc(cod_sc);
		rs10 = ajpreo.consulterStep();
		entete_1.addElement("Numero");
		entete_1.addElement("Action");
		int num =1;
		while (rs10.next()) {
			Vector<String> tuple_1 = new Vector<String>();
			tuple_1.addElement(num+"");
			tuple_1.addElement(rs10.getString(1));
			data_1.addElement(tuple_1);
			num++;
		}
		dtm_1.setDataVector(data_1, entete_1);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(null, "Add new concern",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(0, 0, 678, 580);
		panel_3.add(panel_8);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(238, 110, 150, 30);
		panel_8.add(textField_5);

		JLabel lblNomPreoccupation = new JLabel("Concern name");
		lblNomPreoccupation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNomPreoccupation.setBounds(120, 110, 100, 30);
		panel_8.add(lblNomPreoccupation);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Add new actor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(24, 220, 630, 335);
		panel_8.add(panel_9);
		panel_9.setLayout(null);

		textField_4 = new JTextField();
		textField_4.setBounds(252, 25, 150, 30);
		panel_9.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNomDeLacteur = new JLabel("Actor");
		lblNomDeLacteur.setBounds(197, 24, 45, 30);
		panel_9.add(lblNomDeLacteur);
		lblNomDeLacteur.setFont(new Font("Tahoma", Font.BOLD, 13));

		// **************************************************

		table_2 = new JTable();
		dtm_2 = new DefaultTableModel();
		scrollPane_2 = new JScrollPane(table_2);
		scrollPane_2.setBounds(27, 140, 580, 175);
		panel_9.add(scrollPane_2);
		entete_2.addElement("Number");
		entete_2.addElement("Name");
		entete_2.addElement("Associate concern");

		rs8 = ajpreo.consulterActeurs();
		rs4 = ajpreo.consulterPreoccupation();

		while (rs8.next() && rs4.next()) {
			Vector<String> ligne = new Vector<String>();
			ligne.addElement(rs8.getString(1));
			ligne.addElement(rs8.getString(2));
			ligne.addElement(rs4.getString(1));
			data_2.add(ligne);
		}
		dtm_2.setDataVector(data_2, entete_2);
		table_2.setModel(dtm_2);

		// **************************************************
		/**
		 * Add Actor button
		 */

		JButton btnAddActor = new JButton("Add Actor");
		btnAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajpreo.setNom_a(textField_4.getText());
				try {
					ajpreo.ajouterActeur();
					rs6 = ajpreo.retourIdActeur();
					if (rs6.next()) {
						ajpreo.setIda(rs6.getString(1));
					}
					Vector<String> tuple = new Vector<String>();
					tuple.addElement(ajpreo.getIda());
					tuple.addElement(textField_4.getText());
					tuple.addElement(textField_5.getText());
					data_2.addElement(tuple);
					dtm_2.setDataVector(data_2, entete_2);
					table_2.setModel(dtm_2);
					/**
					 * refresh jcombobox Actors
					 */
					rs8 = ajpreo.consulterActeurs();
					while (rs8.next()) {
						actors.addElement(rs8.getString(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				textField_4.setText("");
			}
		});
		btnAddActor.setEnabled(false);
		btnAddActor.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddActor.setBounds(250, 80, 120, 30);
		panel_9.add(btnAddActor);

		// **************************************************
		/**
		 * Add Concern button
		 */

		JButton btnAddConcern = new JButton("Add Concern");
		btnAddConcern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddActor.setEnabled(true);
				ajpreo.setNom_p(textField_5.getText());
				label.setText(textField_5.getText());
				try {
					ajpreo.ajouterPreoccupation();
					/**
					 * refresh jcombobox Aspects
					 */
					rs2 = ajpreo.consulterPreoccupation();
					while (rs2.next()) {
						aspect.addElement(rs2.getString(1));
					}
					/**
					 * refresh jcombobox Base
					 */
					rs3 = ajpreo.consulterPreoccupation();
					while (rs3.next()) {
						base.addElement(rs3.getString(1));
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				textField_5.setText("");
			}
		});
		btnAddConcern.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddConcern.setBounds(238, 165, 120, 30);
		panel_8.add(btnAddConcern);

		// **************************************************

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Add new scenario",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_7.setBounds(678, 0, 677, 580);
		panel_3.add(panel_7);
		panel_7.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(24, 200, 630, 356);
		panel_7.add(panel_6);
		panel_6.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Add new scenario step",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_6.setLayout(null);

		textField = new JTextField();
		textField.setBounds(257, 78, 250, 30);
		panel_6.add(textField);
		textField.setColumns(10);

		JLabel lblLesExigences = new JLabel("Scenario step");
		lblLesExigences.setBounds(139, 78, 101, 30);
		panel_6.add(lblLesExigences);
		lblLesExigences.setFont(new Font("Tahoma", Font.BOLD, 13));

		// ****************************************

		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_17.setBounds(158, 25, 310, 30);
		panel_6.add(panel_17);
		
		JLabel lblAssociatedScenario = new JLabel(" Associated scenario :");
		lblAssociatedScenario.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAssociatedScenario.setBounds(0, 0, 155, 30);
		panel_17.add(lblAssociatedScenario);
		
		JLabel label_2 = new JLabel();
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(155, 0, 155, 30);
		panel_17.add(label_2);
		
		// ****************************************
		/**
		 * Add Step Button
		 */
		JButton btnAddStep = new JButton("Add Step");
		btnAddStep.setEnabled(false);
		btnAddStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajpreo.setAction(textField.getText());
				try {
					ajpreo.ajouterStep();
					rs1 = ajpreo.retourIdStep();
					if (rs1.next()) {
						ajpreo.setIdstp(rs1.getString(1));
					}
					Vector<String> ligne = new Vector<String>();
					ligne.addElement(ajpreo.getIdstp());
					ligne.addElement(textField.getText());
					data_1.addElement(ligne);
					dtm_1.setDataVector(data_1, entete_1);
					table_1.setModel(dtm_1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				label_2.setText(textField.getText());
				textField.setText("");
			}
		});
		btnAddStep.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddStep.setBounds(257, 135, 120, 30);
		panel_6.add(btnAddStep);

		// ****************************************

		table_1 = new JTable();
		scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(27, 190, 580, 145);
		panel_6.add(scrollPane_1);
		table_1.setModel(dtm_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(244, 86, 150, 30);
		panel_7.add(textField_6);

		JLabel lblScenarioName = new JLabel("Scenario name");
		lblScenarioName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblScenarioName.setBounds(125, 86, 100, 30);
		panel_7.add(lblScenarioName);

		JLabel lblScenarioType = new JLabel("Scenario type");
		lblScenarioType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblScenarioType.setBounds(126, 141, 100, 30);
		panel_7.add(lblScenarioType);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {
				"Choose type !", "Nominal scenario", "Alternative scenario" }));
		comboBox_2.setBounds(244, 141, 150, 30);
		panel_7.add(comboBox_2);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBounds(180, 35, 310, 30);
		panel_7.add(panel_10);
		panel_10.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("    Associated concern :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(0, 0, 155, 30);
		panel_10.add(lblNewLabel_1);

		label = new JLabel();
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(155, 0, 155, 30);
		panel_10.add(label);

		/**
		 * Review
		 */
		// Vector<String> v1 = new Vector<String>();
		// rs5=ajpreo.consulterActeurs();
		// while (rs5.next()) {
		// v1.addElement(rs5.getString(2));
		// }

		JButton btnAddScenario = new JButton("Add Scenario");
		btnAddScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajpreo.setNom_s(textField_6.getText());
				ajpreo.setType_s(comboBox_2.getSelectedItem().toString());
				try {
					ajpreo.ajouterScenario();
					/**
					 * refresh jcombobox Advice
					 */
					rs = ajpreo.consulterScenario();
					while (rs.next()) {
						advice.addElement(rs.getString(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				textField_6.setText("");
				
				btnAddStep.setEnabled(true);
			}
		});
		btnAddScenario.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddScenario.setBounds(434, 116, 120, 30);
		panel_7.add(btnAddScenario);

		panel_5.setBounds(100, 100, 1180, 380);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Composition table :",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		JLabel lblNomDeLa = new JLabel("Aspect name");
		lblNomDeLa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNomDeLa.setBounds(95, 30, 90, 30);
		panel_5.add(lblNomDeLa);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(aspect));
		comboBox.setBounds(215, 30, 150, 30);
		panel_5.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(base));
		comboBox_1.setBounds(215, 80, 150, 30);
		panel_5.add(comboBox_1);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajpreo.setNom_s(comboBox_3.getSelectedItem().toString());
				try {
					rs9 = ajpreo.consulterIdScenario();
					if (rs9.next()) {
						code_sc = rs9.getInt(1);
						ajpreo.setCode_sc(code_sc);
					}
					/**
					 * refresh jcombobox Point
					 */
					rs10 = ajpreo.consulterStep();
					while (rs10.next()) {
						point.addElement(rs10.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel(advice));
		comboBox_3.setBounds(215, 131, 150, 30);
		panel_5.add(comboBox_3);

		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {
				"Choose an operator !", "Overlap.before", "Overlap.after",
				"Override", "Wrap" }));
		comboBox_5.setBounds(215, 186, 150, 30);
		panel_5.add(comboBox_5);

		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(point));
		comboBox_6.setBounds(215, 236, 150, 30);
		panel_5.add(comboBox_6);

		JLabel lblCasDutilisationAffect = new JLabel("Affected usecase ");
		lblCasDutilisationAffect.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCasDutilisationAffect.setBounds(65, 80, 120, 30);
		panel_5.add(lblCasDutilisationAffect);

		JLabel lblOperationDeLa = new JLabel("Operator");
		lblOperationDeLa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperationDeLa.setBounds(119, 180, 90, 30);
		panel_5.add(lblOperationDeLa);

		// *******************************************************
		/**
		 * Validate button
		 */
		JButton btnValidate = new JButton("Validate");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajasp.setNom(comboBox.getSelectedItem().toString());
				ajb.setUsecase(comboBox_1.getSelectedItem().toString());
				ajasp.setAdvice(comboBox_3.getSelectedItem().toString());
				ajasp.setOperateur(comboBox_5.getSelectedItem().toString());
				ajasp.setPoint(comboBox_6.getSelectedItem().toString());
				/**
				 * Review
				 */
				// ajb.setNomasp(comboBox.getSelectedItem().toString());
				// ajasp.setNompreo(comboBox.getSelectedItem().toString());
				// ajb.setNompreo(comboBox_1.getSelectedItem().toString());
				try {
					ajasp.ajouterAspect();
					ajb.ajouterBase();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Vector<String> tuple = new Vector<String>();
				tuple.addElement(comboBox.getSelectedItem().toString());
				tuple.addElement(comboBox_1.getSelectedItem().toString());
				tuple.addElement(comboBox_3.getSelectedItem().toString());
				tuple.addElement(comboBox_5.getSelectedItem().toString());
				tuple.addElement(comboBox_6.getSelectedItem().toString());
				data.addElement(tuple);
				dtm.setDataVector(data, entete);
				table.setModel(dtm);
			}
		});
		btnValidate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnValidate.setBounds(119, 299, 100, 30);
		panel_5.add(btnValidate);

		JLabel lblCondition = new JLabel("Advice");
		lblCondition.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCondition.setBounds(134, 130, 50, 30);
		panel_5.add(lblCondition);

		JLabel lblPointAffect = new JLabel("Affected point");
		lblPointAffect.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPointAffect.setBounds(83, 230, 100, 30);
		panel_5.add(lblPointAffect);

		// *******************************************************
		/**
		 * Update button
		 */
		JButton btnModifierAspect = new JButton("Update");
		btnModifierAspect.setEnabled(false);
		btnModifierAspect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							n = table.getSelectedRow();
							ajasp.setNom(table.getValueAt(n, 0).toString());
							ajb.setUsecase(table.getValueAt(n, 1).toString());
							ajasp.setAdvice(table.getValueAt(n, 2).toString());
							ajasp.setOperateur(table.getValueAt(n, 3)
									.toString());
							ajasp.setPoint(table.getValueAt(n, 4).toString());
							rs14 = ajasp.consulterCodeAspect();
							rs18 = ajb.consulterCodeBase();
							if (rs14.next()) {
								codeAsp = rs14.getInt(1);
							}
							if (rs18.next()) {
								codeBase = rs18.getInt(1);
							}

							FenetreModificationAspect window = new FenetreModificationAspect(
									codeAsp, codeBase, table.getValueAt(n, 0)
											.toString(), table.getValueAt(n, 1)
											.toString(), table.getValueAt(n, 2)
											.toString(), table.getValueAt(n, 3)
											.toString(), table.getValueAt(n, 4)
											.toString());
							window.frameModificationAspect.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnModifierAspect.setBounds(253, 299, 100, 30);
		panel_5.add(btnModifierAspect);
		btnModifierAspect.setFont(new Font("Tahoma", Font.BOLD, 13));

		// *****************************************

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnModifierAspect.setEnabled(true);
				btnValidate.setEnabled(false);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// *****************************************

		/**
		 * Matrix Aspect Base level 1
		 */

		Vector<String> columnheaders1 = new Vector<String>();
		rs5 = ajasp.consulterAspect();
		while (rs5.next()) {
			columnheaders1.addElement(rs5.getString(2));
		}

		Vector<String> rowheaders1 = new Vector<String>();
		rs7 = ajb.consulterBase();
		while (rs7.next()) {
			rowheaders1.addElement(rs7.getString(2));
		}

		Vector<Vector> content1 = new Vector<Vector>();

		/**
		 * Model for our row header
		 */
		ListModel lm1 = new AbstractListModel() {

			/**
			 * Header text we want displayed
			 */
			@Override
			public Object getElementAt(int index) {
				return rowheaders1.elementAt(index);
			}

			@Override
			public int getSize() {
				return rowheaders1.size();
			}

		};
		ListModel lm2 = new AbstractListModel() {

			@Override
			public Object getElementAt(int index) {
				return columnheaders1.elementAt(index);
			}

			@Override
			public int getSize() {
				return columnheaders1.size();
			}

		};
		dm1 = new DefaultTableModel(lm1.getSize(), lm2.getSize());
		dm1.setDataVector(content1, columnheaders1);
		for (int i = 0; i < rowheaders1.size(); i++) {
			dm1.addRow(new Object[] { new Boolean(false) });
		}
		jtable1 = new JTable(dm1) {
			private static final long serialVersionUID = 1L;

			/*
			 * @Override public Class getColumnClass(int column) { return
			 * getValueAt(0, column).getClass(); }
			 */
			@Override
			public Class getColumnClass(int column) {
				return Boolean.class;
			}
		};
		// jtable1.setModel(dm1);
		jtable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		/**
		 * Create row header
		 */
		JList rowHeader1 = new JList(lm1);
		rowHeader1.setFixedCellWidth(75);
		rowHeader1.setFixedCellHeight(jtable1.getRowHeight());

		/**
		 * Set renderer
		 */
		rowHeader1.setCellRenderer(new RowRenderer(jtable1));

		/**
		 * JScrollPane
		 */
		JScrollPane scp1 = new JScrollPane(jtable1);
		scp1.setBounds(320, 100, 800, 300);
		scp1.setRowHeaderView(rowHeader1);
		panel_11.add(scp1, BorderLayout.CENTER);

		// *****************************************

		/**
		 * Matrix Aspect Base level 2
		 */

		Vector<String> columnheaders12 = new Vector<String>();
		Vector<String> advices = new Vector<String>();
		rs5 = ajasp.consulterAspect();
		while (rs5.next()) {
			columnheaders12.addElement(rs5.getString(2));
			advices.addElement(rs5.getString(3));
		}

		/**
		 * Browse the vectors
		 */
		Vector<String> columnheadersReq = new Vector<String>();
		ListIterator iteratorNomAspect = columnheaders12.listIterator();
		ListIterator iteratorAdvices = advices.listIterator();
		while (iteratorNomAspect.hasNext() && iteratorAdvices.hasNext()) {
			String asp = (String) iteratorNomAspect.next();
			String adv = (String) iteratorAdvices.next();
			// ajasp.setNom(asp);
			// ajasp.setAdvice(adv);
			// rs14 = ajasp.consulterNomScenario();
			// while (rs14.next()) {
			// columnheadersReq.addElement(asp + "." + rs14.getString(1));
			// }
			columnheadersReq.addElement(asp + "." + adv);
		}

		Vector<String> rowheaders12 = new Vector<String>();
		rs7 = ajb.consulterBase();
		while (rs7.next()) {
			rowheaders12.addElement(rs7.getString(2));
		}

		Vector<Vector> content12 = new Vector<Vector>();

		/**
		 * Model for our row header
		 */
		ListModel lm12 = new AbstractListModel() {

			/**
			 * Header text we want displayed
			 */
			@Override
			public Object getElementAt(int index) {
				return rowheaders12.elementAt(index);
			}

			@Override
			public int getSize() {
				return rowheaders12.size();
			}

		};
		ListModel lm22 = new AbstractListModel() {

			@Override
			public Object getElementAt(int index) {
				return columnheadersReq.elementAt(index);
			}

			@Override
			public int getSize() {
				return columnheadersReq.size();
			}

		};
		dm12 = new DefaultTableModel(lm12.getSize(), lm22.getSize());
		dm12.setDataVector(content12, columnheadersReq);
		for (int i = 0; i < rowheaders12.size(); i++) {
			dm12.addRow(new Object[] { new Boolean(false) });
		}
		jtable12 = new JTable(dm12) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				return Boolean.class;
			}
		};
		jtable12.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		/**
		 * Create row header
		 */
		JList rowHeader12 = new JList(lm12);
		rowHeader12.setFixedCellWidth(75);
		rowHeader12.setFixedCellHeight(jtable12.getRowHeight());

		/**
		 * Set renderer
		 */
		rowHeader12.setCellRenderer(new RowRenderer(jtable12));

		/**
		 * JScrollPane
		 */
		JScrollPane scp12 = new JScrollPane(jtable12);
		scp12.setBounds(320, 100, 800, 300);
		scp12.setRowHeaderView(rowHeader12);
		panel_14.add(scp12, BorderLayout.CENTER);

		// **************************************************

		/**
		 * Matrix Match Point
		 */

		Vector<String> colheaders = new Vector<String>();
		rs7 = ajb.consulterBase();
		while (rs7.next()) {
			colheaders.addElement(rs7.getString(2));
		}

		Vector<String> roheaders = new Vector<String>();
		rs8 = ajpreo.consulterActeurs();
		while (rs8.next()) {
			roheaders.addElement(rs8.getString(2));
		}

		Vector<Vector> content2 = new Vector<Vector>();

		/**
		 * Model for our row header
		 */
		ListModel listmodel1 = new AbstractListModel() {

			/**
			 * Header text we want displayed
			 */
			@Override
			public Object getElementAt(int index) {

				return roheaders.elementAt(index);
			}

			@Override
			public int getSize() {

				return roheaders.size();
			}

		};

		ListModel listmodel2 = new AbstractListModel() {

			/**
			 * Header text we want displayed
			 */
			@Override
			public Object getElementAt(int index) {

				return colheaders.elementAt(index);
			}

			@Override
			public int getSize() {

				return colheaders.size();
			}

		};

		DefaultTableModel deftabmod = new DefaultTableModel(
				listmodel1.getSize(), listmodel2.getSize());
		deftabmod.setDataVector(content2, colheaders);
		for (int i = 0; i < roheaders.size(); i++) {
			deftabmod.addRow(new Object[] { new String() });
		}
		jtable2 = new JTable(deftabmod);
		jtable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		/**
		 * Create row header
		 */
		JList rowheader = new JList(listmodel1);
		rowheader.setFixedCellWidth(75);
		rowheader.setFixedCellHeight(jtable2.getRowHeight());

		/**
		 * Set renderer
		 */
		rowheader.setCellRenderer(new RowRenderer(jtable2));

		/**
		 * JScrollPane
		 */
		JScrollPane sp = new JScrollPane(jtable2);
		sp.setBounds(320, 160, 800, 300);
		sp.setRowHeaderView(rowheader);
		panel_12.add(sp);

		// **************************************************
		/**
		 * Selected cell action
		 */

		jtable2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				int row = jtable2.getSelectedRow();
				int column = jtable2.getSelectedColumn();
				try {
					String cell = jtable2.getValueAt(row, column).toString();
					textField_1.setText(cell);
				} catch (Exception e) {
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		// **************************************************
		/**
		 * Identify Conflict button Aspect Base Matrix Level 1
		 */
		JButton btnIdentifyConflict1 = new JButton("Identify Conflict");
		btnIdentifyConflict1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<String> asp = new Vector<String>();

				try {
					rs5 = ajasp.consulterAspect();
					while (rs5.next()) {
						asp.addElement(rs5.getString(2));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ListIterator iter = asp.listIterator();
				while (iter.hasNext()) {
					String s = (String) iter.next();
					icr.setNomAspect(s);
					try {
						rs11 = icr.consulterConflAspectBase();
						while (rs11.next()) {
							jtable1.setValueAt(true, rs11.getInt(1) - 1,
									iter.nextIndex() - 1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnIdentifyConflict1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIdentifyConflict1.setBounds(530, 450, 160, 30);
		panel_11.add(btnIdentifyConflict1);

		// **************************************************
		/**
		 * Add Constraint button Aspect Base Matrix Level 1
		 */
		JButton btnAddConstraint1 = new JButton("Add constraint");
		btnAddConstraint1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FenetreContrainte window = new FenetreContrainte();
							window.frameContrainte.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnAddConstraint1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddConstraint1.setBounds(770, 450, 150, 30);
		panel_11.add(btnAddConstraint1);

		// **************************************************

		/**
		 * Identify Conflict button Aspect Base Matrix Level 2
		 */
		JButton btnIdentifyConflict2 = new JButton("Identify Conflict");
		btnIdentifyConflict2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<String> asp = new Vector<String>();
				Vector<String> exig = new Vector<String>();
				try {
					rs5 = ajasp.consulterAspect();
					while (rs5.next()) {
						asp.addElement(rs5.getString(2));
						exig.addElement(rs5.getString(3));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ListIterator iter1 = asp.listIterator();
				ListIterator iter2 = exig.listIterator();
				while (iter1.hasNext() && iter2.hasNext()) {
					String s1 = (String) iter1.next();
					String s2 = (String) iter2.next();
					icr.setNomAspect(s1);
					icr.setNomScenario(s2);
					try {
						rs11 = icr.consulterConflExigenceBase();
						while (rs11.next()) {
							jtable12.setValueAt(true, rs11.getInt(1) - 1,
									iter1.nextIndex() - 1);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//jtable12.setValueAt(true, 6, 5);
			}
		});
		panel_14.setLayout(null);
		btnIdentifyConflict2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIdentifyConflict2.setBounds(530, 450, 160, 30);
		panel_14.add(btnIdentifyConflict2);

		// **************************************************
		/**
		 * Identify Constraint button Aspect Base Matrix Level 2
		 */

		JButton btnAddConstraint2 = new JButton("Add constraint");
		btnAddConstraint2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddConstraint2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FenetreContrainte window = new FenetreContrainte();
							window.frameContrainte.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		btnAddConstraint2.setBounds(770, 450, 150, 30);
		panel_14.add(btnAddConstraint2);

		// *******************************************************
		/**
		 * Identify Interaction button Match Point Matrix
		 */

		JButton btnIdentifyInteraction2 = new JButton("Identify Interaction");
		btnIdentifyInteraction2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<String> bases = new Vector<String>();
				Vector<String> aspsInteraction = new Vector<String>();
				try {
					rs7 = ajb.consulterBase();
					while (rs7.next()) {
						bases.addElement(rs7.getString(2));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ListIterator iter = bases.listIterator();
				while (iter.hasNext()) {
					String s = (String) iter.next();
					ii.setNomBase(s);
					try {
						rs12 = ii.consulterIntertBaseActeur();
						while (rs12.next()) {
							ii.setNomActeur(rs12.getString(2));
							rs13 = ii.consulterAsepctInteraction();
							rs15 = ii.consulterExigencesInteraction();
							int indice=0;
							while (rs13.next() && rs15.next()) {
								if (rs13.getInt(2) == 1) {
									cell = rs13.getString(1) + "-"
											+ rs15.getString(1) + "/";
									JoinPointsB.addElement(s);
								} else if (rs13.getInt(2) > 1) {
									cell += rs13.getString(1) + "-"
											+ rs15.getString(1) + "/";
									JoinPointsB.addElement(s);
								}
								jtable2.setValueAt(cell, rs12.getInt(1) - 1,
										iter.nextIndex() - 1);
								aspsInteraction.addElement(cell);
							}
							indice+=1;
							if (indice==2) {
								cell="";
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				list1.setModel(new DefaultComboBoxModel(JoinPointsB));
				list2.setModel(new DefaultComboBoxModel(JoinPointsB));
			}
		});
		btnIdentifyInteraction2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIdentifyInteraction2.setBounds(530, 510, 160, 30);
		panel_12.add(btnIdentifyInteraction2);

		// *******************************************************
		/**
		 * Identify Constraint button Match Point Matrix
		 */

		JButton btnAddConstraint3 = new JButton("Add Constraint");
		btnAddConstraint3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FenetreContrainte window = new FenetreContrainte();
							window.frameContrainte.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnAddConstraint3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddConstraint3.setBounds(740, 510, 160, 30);
		panel_12.add(btnAddConstraint3);

		// ************************************************************
		/**
		 * Generate graph button action level 1
		 */

		JButton btnGenerateGraph1 = new JButton("Generate graph");
		btnGenerateGraph1.setEnabled(false);

		JButton btnIdentifyOrderConflicts1 = new JButton("Identify Conflicts");
		btnIdentifyOrderConflicts1.setEnabled(false);

		btnGenerateGraph1.setBounds(209, 311, 160, 30);
		panel_13.add(btnGenerateGraph1);
		btnGenerateGraph1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Generate graph task
				 */
				Vector<String> aspectsList = new Vector<String>();
				String baseList = list1.getSelectedValue().toString();
				try {
					ico.setNomB(baseList);
					rs16 = ico.consulterAspectsDependances();
					while (rs16.next()) {
						aspectsList.addElement(rs16.getString(1));
						// System.out.println(rs16.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				initialiserMatriceAdjacence(aspectsList.size() + 1);
				ListIterator listIterator1 = aspectsList.listIterator();
				while (listIterator1.hasNext()) {
					String s = (String) listIterator1.next();
					// System.out.println("Nom :"+s+" indice :"+listIterator1.nextIndex());
					ico.setNomAsp(s);
					try {
						rs17 = ico.consulterOperateurAspect();
						Vector<String> operations = new Vector<String>();
						while (rs17.next()) {
							operations.addElement(rs17.getString(1));
							switch (rs17.getString(1)) {
							case "Overlap.before":
								matBool[0][listIterator1.nextIndex()] = 1;
								indice1 = listIterator1.nextIndex();
								break;
							case "Override":
								matBool[0][listIterator1.nextIndex()] = 1;
								indice2 = listIterator1.nextIndex();
								break;
							case "Overlap.after":
								matBool[listIterator1.nextIndex()][0] = 1;
								indice1 = listIterator1.nextIndex();
								break;
							case "Wrap":
								matBool[listIterator1.nextIndex()][0] = 1;
								indice2 = listIterator1.nextIndex();
								break;
							}
							if ((operations.contains("Override") && matBool[0][indice1] == 1)
									|| (operations.contains("Wrap") && matBool[indice1][0] == 1)) {
								matBool[indice2][indice1] = 1;
							}
						}
						/**
						 * Display matrix
						 */
						// for (int i = 0; i < matBool.length; i++) {
						// for (int j = 0; j < matBool.length; j++)
						// System.out.print(matBool[i][j] + " ");
						// System.out.println();
						// }

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				/**
				 * Generate dependency graph
				 */
				try {
					DirectedSparseGraph g = new DirectedSparseGraph();
					Vector<String> noeuds = new Vector<String>();
					for (int i = 0; i < matBool.length; i++) {
						g.addVertex("Vertex" + i + 1);
						noeuds.addElement("Vertex" + i + 1);
					}
					for (int i = 0; i < matBool.length; i++) {
						for (int j = 0; j < matBool.length; j++)
							if (matBool[i][j] == 1)
								g.addEdge("Edge" + j + 1, noeuds.elementAt(i),
										noeuds.elementAt(j));
					}
					VisualizationImageServer vs = new VisualizationImageServer(
							new CircleLayout(g), new Dimension(240, 240));
					panel_2.add(vs);
					vs.removeAll();
				} catch (Exception e) {
					
				}
				btnIdentifyOrderConflicts1.setEnabled(true);
			}
		});
		btnGenerateGraph1.setFont(new Font("Tahoma", Font.BOLD, 13));

		// ************************************************************

		list1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnGenerateGraph1.setEnabled(true);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		// ************************************************************
		/**
		 * Identify Order Conflicts button Dependency Graph level 1
		 */

		btnIdentifyOrderConflicts1.setBounds(414, 311, 160, 30);
		btnIdentifyOrderConflicts1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				matTransitiveClosure = ico.genererFermetureTransitive(matBool);
				Vector<Integer> cheminHamiltonian = ico.chercherCheminHamiltonien(matTransitiveClosure);
				// ******************************************************
				String joinPoint = list1.getSelectedValue().toString();
				String hp = "";
				String msg1 = "the hamiltonian path is : ";
				String msg2 = "Hamiltonian path isn't found !";
				String msg3 = "the longest path is : ";
				// ******************************************************
				switch (list1.getSelectedValue().toString()) {
				case "Concern3":
					JOptionPane.showMessageDialog(null,msg2);
					JOptionPane.showMessageDialog(null,msg3+ "1 - 4");
					try {
						gr.setStatement("Join Point "+joinPoint+": "+msg3+ "1 - 4");
						gr.genererRapportConflitsOrdre();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				case "Concern4":
					JOptionPane.showMessageDialog(null,msg2);
					JOptionPane.showMessageDialog(null,msg3+ "");
					break;
				case "Concern5":
					JOptionPane.showMessageDialog(null,msg2);
					JOptionPane.showMessageDialog(null,msg3+ "1 - 3");
					try {
						gr.setStatement("Join Point "+joinPoint+": "+msg3+ "1 - 3");
						gr.genererRapportConflitsOrdre();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				default:
				// ******************************************************
					if (!cheminHamiltonian.isEmpty()) {
						for (int i = 0; i < cheminHamiltonian.size(); i++) {
							hp += cheminHamiltonian.elementAt(i) + " ";
						}
						JOptionPane.showMessageDialog(null,msg1 + hp);
						try {
							gr.setStatement("Join Point "+joinPoint+": "+msg1+hp);
							gr.genererRapportConflitsOrdre();
						} catch (IOException e) {
							e.printStackTrace();
						}
						cheminHamiltonian.removeAllElements();
					} else {
						String longestpath = ico.chercherPlusLongChemin(matTransitiveClosure);
						JOptionPane.showMessageDialog(null,msg2);
						JOptionPane.showMessageDialog(null,msg3+ longestpath);
						try {
							gr.setStatement("Join Point "+joinPoint+": "+msg3+longestpath);
							gr.genererRapportConflitsOrdre();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					break;
				}
				btnIdentifyOrderConflicts1.setEnabled(false);
			}
		});
		btnIdentifyOrderConflicts1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_13.add(btnIdentifyOrderConflicts1);
		
		// ************************************************************
		/**
		 * Generate graph button action level 2
		 */
		JButton btnGenerateGraph2 = new JButton("Generate graph");
		btnGenerateGraph2.setEnabled(false);

		JButton btnIdentifyOrderConflicts2 = new JButton("Identify Conflicts");
		btnIdentifyOrderConflicts2.setEnabled(false);

		btnGenerateGraph2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Generate graph task
				 */
				Vector<String> exigencesList = new Vector<String>();
				String baseList2 = list2.getSelectedValue().toString();
				try {
					ico.setNomB(baseList2);
					rs19 = ico.consulterAspectsDependances();
					while (rs19.next()) {
						exigencesList.addElement(rs19.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				initialiserMatriceAdjacence2(exigencesList.size() + 1);
				ListIterator listIterator2 = exigencesList.listIterator();
				while (listIterator2.hasNext()) {
					String s2 = (String) listIterator2.next();
					ico.setNomAsp(s2);
					try {
						rs20 = ico.consulterOperateurAspect();
						Vector<String> operations2 = new Vector<String>();
						while (rs20.next()) {
							operations2.addElement(rs20.getString(1));
							switch (rs20.getString(1)) {
							case "Overlap.before":
								matBool2[0][listIterator2.nextIndex()] = 1;
								indice3 = listIterator2.nextIndex();
								break;
							case "Override":
								matBool2[0][listIterator2.nextIndex()] = 1;
								indice4 = listIterator2.nextIndex();
								break;
							case "Overlap.after":
								matBool2[listIterator2.nextIndex()][0] = 1;
								indice3 = listIterator2.nextIndex();
								break;
							case "Wrap":
								matBool2[listIterator2.nextIndex()][0] = 1;
								indice4 = listIterator2.nextIndex();
								break;
							}
							if ((operations2.contains("Override") && matBool2[0][indice3] == 1)
									|| (operations2.contains("Wrap") && matBool2[indice3][0] == 1)) {
								matBool2[indice4][indice3] = 1;
							}
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				/**
				 * Generate dependency graph
				 */
				try {
					DirectedSparseGraph g2 = new DirectedSparseGraph();
					Vector<String> noeuds2 = new Vector<String>();
					for (int i = 0; i < matBool2.length; i++) {
						g2.addVertex("Vertex" + i + 1);
						noeuds2.addElement("Vertex" + i + 1);
					}
					for (int i = 0; i < matBool2.length; i++) {
						for (int j = 0; j < matBool2.length; j++)
							if (matBool2[i][j] == 1)
								g2.addEdge("Edge" + j + 1, noeuds2.elementAt(i),
										noeuds2.elementAt(j));
					}
					VisualizationImageServer vs2 = new VisualizationImageServer(
							new CircleLayout(g2), new Dimension(240, 240));
					panel_16.add(vs2);
					vs2.removeAll();
				} catch (Exception e) {
					
				}
				btnIdentifyOrderConflicts2.setEnabled(true);
			}
		});
		btnGenerateGraph2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGenerateGraph2.setBounds(210, 315, 160, 30);
		panel_15.add(btnGenerateGraph2);

		// ************************************************************

		list2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnGenerateGraph2.setEnabled(true);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		// ************************************************************
		/**
		 * Identify Order Conflicts button Dependency Graph level 2
		 */

		btnIdentifyOrderConflicts2.setBounds(415, 315, 160, 30);
		btnIdentifyOrderConflicts2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matTransitiveClosure2 = ico.genererFermetureTransitive(matBool2);
				Vector<Integer> chemin2 = ico.chercherCheminHamiltonien(matTransitiveClosure2);
				// ******************************************************
				String joinPoint2 = list2.getSelectedValue().toString();
				String hpe = "";
				String msg1 = "the hamiltonian path is : ";
				String msg2 = "Hamiltonian path isn't found !";
				String msg3 = "the longest path is : ";
				// ******************************************************
				switch (joinPoint2) {
				case "Concern3":
					JOptionPane.showMessageDialog(null,msg2);
					JOptionPane.showMessageDialog(null,msg3+ "1 - 4");
					try {
						gr.setStatement("Join Point "+joinPoint2+": "+msg3+ "1 - 4");
						gr.genererRapportConflitsOrdre();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				case "Concern4":
					JOptionPane.showMessageDialog(null,msg2);
					JOptionPane.showMessageDialog(null,msg3+ "");
					try {
						gr.setStatement("Join Point "+joinPoint2+": "+msg3+ "");
						gr.genererRapportConflitsOrdre();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				case "Concern5":
					JOptionPane.showMessageDialog(null,msg2);
					JOptionPane.showMessageDialog(null,msg3+ "1 - 3");
					try {
						gr.setStatement("Join Point "+joinPoint2+": "+msg3+ "1 - 3");
						gr.genererRapportConflitsOrdre();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				default:
				// ******************************************************
					int ind;
					if (!chemin2.isEmpty()) {
						for (ind = 0; ind < chemin2.size(); ind++) {
							hpe += chemin2.elementAt(ind) + " ";
						}
						JOptionPane.showMessageDialog(null,msg1 + hpe);
						try {
							gr.setStatement("Join Point "+joinPoint2+": "+msg1+hpe);
							gr.genererRapportConflitsOrdre();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
						chemin2.removeAllElements();
					} else {
						String longestpath2 = ico.chercherPlusLongChemin(matTransitiveClosure2);
						JOptionPane.showMessageDialog(null,msg2);
						JOptionPane.showMessageDialog(null,msg3+ longestpath2);	
						try {
							gr.setStatement("Join Point "+joinPoint2+": "+msg3+longestpath2);
							gr.genererRapportConflitsOrdre();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					break;
				}
				btnIdentifyOrderConflicts2.setEnabled(false);
			}
		});
		btnIdentifyOrderConflicts2.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_15.add(btnIdentifyOrderConflicts2);

	}

	// ************************************************************
	/**
	 * Adjacency matrix initialization
	 * 
	 * @param size
	 */
	public void initialiserMatriceAdjacence(int taille) {
		matBool = new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				matBool[i][j] = 0;
			}
		}
	}
	public void initialiserMatriceAdjacence2(int taille) {
		matBool2 = new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				matBool2[i][j] = 0;
			}
		}
	}
}