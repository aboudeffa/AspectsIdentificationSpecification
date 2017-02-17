package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Aspect extends Preoccupation{

	private int code_aspect;
	private String nom_aspect;
	private Base[] base;
	
	public Aspect() {
		super();
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
