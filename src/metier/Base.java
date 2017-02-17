package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Base extends Preoccupation{

	private int code_base;
	private String nom_base;
	private Aspect[] aspect;
	
	public Base() {
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
