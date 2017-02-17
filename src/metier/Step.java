package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Step {

	private Statement st;
	
	public Step() {
		ConnectionBDD conx = SingletonConnectionBDD.getConnectionBDD();
	}

	public void ajouter(String sql) throws SQLException {
		st.executeUpdate(sql);
	}

	public ResultSet retourId(String sql_id) throws SQLException {
		return st.executeQuery(sql_id);
	}

	public ResultSet consulter(String sql) throws SQLException {
		return st.executeQuery(sql);
	}
}
