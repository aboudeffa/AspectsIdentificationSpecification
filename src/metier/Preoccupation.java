package metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Preoccupation {

	Connection con;
	Statement st;
	
	public Preoccupation() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
				JOptionPane.showMessageDialog(null, "Erreur de chargerment du pilote");
	    }
		try{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/plateformeaspect","root","");
			try {
				st=con.createStatement();
			} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erreur de création du statement");
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erreur de connection à la BDD");
		}
	}
	
	public void ajouter(String sql) throws SQLException{
		st.executeUpdate(sql);
	}
	
	public void modifier(String sql) throws SQLException{
		st.executeUpdate(sql);
	}
	public ResultSet consulter(String sql) throws SQLException{
		return st.executeQuery(sql);
		
	}
}
