package controle;

import metier.Base;
import metier.Scenario;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IdentificationConflictRecursive {
	private String nomAspect;
	private String nomScenario;
	private Base b;
	private Scenario sc;
	
	public IdentificationConflictRecursive() {
		b = new Base();
		sc = new Scenario();
	}
	public ResultSet consulterConflAspectBase() throws SQLException{
		String sql="select base.code,base.nom from base where base.code in "
				+ "( select couper.id_base from couper where couper.code_asp in "
						+ "( select aspect.code from aspect where aspect.nom = '"+getNomAspect()+"' ))";
		return b.consulter(sql);
	}
	
	public ResultSet consulterConflExigenceBase() throws SQLException{
		String sql="select base.code,base.nom from base where base.code in "
				+ "( select couper.id_base from couper where couper.code_asp in "
						+ "( select aspect.code from aspect where aspect.nom = '"+getNomAspect()+"' and aspect.code_preo in "
								+ "( select preoccupation.code from preoccupation where preoccupation.code in"
									+ "( select scenario.code_preo from scenario where scenario.nom='"+getNomScenario()+"'))))";
		return sc.consulter(sql);
	}
	
	public String getNomAspect() {
		return nomAspect;
	}

	public void setNomAspect(String nomAspect) {
		this.nomAspect = nomAspect;
	}
	public String getNomScenario() {
		return nomScenario;
	}
	public void setNomScenario(String nomScenario) {
		this.nomScenario = nomScenario;
	}
	
}
