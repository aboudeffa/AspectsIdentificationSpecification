package controle;

import metier.Acteur;
import metier.Aspect;
import metier.Scenario;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IdentificationInteraction {
	
	private String nomBase;
	private String nomActeur;
	private Aspect asp;
	private Acteur act;
	private Scenario sc;
	public IdentificationInteraction() {
		act = new Acteur();
		asp = new Aspect();
		sc = new Scenario();
	}
	public ResultSet consulterIntertBaseActeur() throws SQLException{
		String sql="select actor.id,actor.nom from actor where actor.id in "
				+ "( select posseder.id_actor from posseder where posseder.code_preo in " 
					+ "( select preoccupation.code from preoccupation where preoccupation.code in " 
						+ "( select base.code_preo from base where base.nom = '"+getNomBase()+"' )))";
		return act.consulter(sql);
	}

	public ResultSet consulterAsepctInteraction() throws SQLException{
		String sql="select aspect.nom, count(aspect.nom) from aspect where aspect.code in "
				+ "( select couper.code_asp from couper where couper.id_base in "
					+ "( select base.code from base where base.nom ='"+getNomBase()+"' and base.code_preo in "
						+ "( select preoccupation.code from preoccupation where preoccupation.code in "
							+ "( select posseder.code_preo from posseder where posseder.id_actor in "
								+ "( select actor.id from actor where actor.nom='"+getNomActeur()+"')))))";
		return asp.consulter(sql);
	}
	
	public ResultSet consulterExigencesInteraction() throws SQLException{
		String sql="select scenario.nom from scenario where scenario.code_preo in "
				+ "( select preoccupation.code from preoccupation where preoccupation.code in "
					+ "( select base.code_preo from base where base.nom ='"+getNomBase()+"' and base.code_preo in "
						+ "( select preoccupation.code from preoccupation where preoccupation.code in "
							+ "( select posseder.code_preo from posseder where posseder.id_actor in "
								+ "( select actor.id from actor where actor.nom='"+getNomActeur()+"')))))";
		return sc.consulter(sql);
	}
	
	public String getNomBase() {
		return nomBase;
	}

	public void setNomBase(String nomBase) {
		this.nomBase = nomBase;
	}
	
	public String getNomActeur() {
		return nomActeur;
	}

	public void setNomActeur(String nomActeur) {
		this.nomActeur = nomActeur;
	}	
	
}
