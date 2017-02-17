package controle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import metier.Aspect;
import metier.CheminHamiltonien;
import metier.FermetureTransitive;
import metier.PlusLongChemin;
import metier.Scenario;

public class IdentificationConflitsOrdre {
	
	private String nomAsp,nomB,nomAdv;
	private Aspect asp;
	private Scenario sc;
	private FermetureTransitive ft;
	private CheminHamiltonien ch;
	private PlusLongChemin plch;
	
	public IdentificationConflitsOrdre(){
		asp = new Aspect();
		sc = new Scenario();
		ft = new FermetureTransitive();
		ch = new CheminHamiltonien();
		plch = new PlusLongChemin();
	}
	
	public ResultSet consulterAspectsDependances() throws SQLException{
		String sql="select aspect.nom from aspect where aspect.code in"
					+ "(select couper.code_asp from couper where couper.id_base in"
						+ "(select base.code from base where base.nom='"+getNomB()+"' ))";
		return asp.consulter(sql);
	}
	
	public ResultSet consulterOperateurAspect() throws SQLException{
		String sql="select aspect.operation from aspect where aspect.nom='"+getNomAsp()+"' and aspect.code in"
					+ "(select couper.code_asp from couper where couper.id_base in"
						+ "(select base.code from base where base.nom='"+getNomB()+"' ))";
		return asp.consulter(sql);
	}
	
	public int [][] genererFermetureTransitive(int [][] mat){
		return ft.genererFermetureTransitive(mat);
	}
	
	public Vector<Integer> chercherCheminHamiltonien(int [][] mat){
		return ch.chercherCheminHamilronien(mat);	
	}
	
	public String chercherPlusLongChemin(int [][] mat){
		return plch.chercherPlusLongChemin(mat);	
	}
	
	public ResultSet consulterExigencesDependances() throws SQLException{
		String sql="select scenario.nom from scenario where scenario.code_preo in"
					+ "(select preoccupation.code from preoccupation where preoccupation.code in"
						+ "(select aspect.code_preo from aspect where aspect.code in"
							+ "(select couper.code_asp from couper where couper.id_base in"
								+ "(select base.code from base where base.nom='"+getNomB()+"' ))))";
		return sc.consulter(sql);
	}
	public ResultSet consulterOperateurExigence() throws SQLException{
		String sql="select aspect.operation from aspect where aspect.code_preo in"
					+ "(select preoccupation.code from preoccupation where preoccupation.code in"
						+ "(select scenario.code_preo from scenario where scenario.nom='"+getNomAdv()+"' and scenario.code_preo in"
							+ "(select preoccupation.code from preoccupation where preoccupation.code in"
								+ "(select base.code from base where base.nom='"+getNomB()+"' ))))";
		return asp.consulter(sql);
	}
	public String getNomAsp() {
		return nomAsp;
	}

	public void setNomAsp(String nomAsp) {
		this.nomAsp = nomAsp;
	}

	public String getNomB() {
		return nomB;
	}

	public void setNomB(String nomB) {
		this.nomB = nomB;
	}

	public String getNomAdv() {
		return nomAdv;
	}

	public void setNomAdv(String nomAdv) {
		this.nomAdv = nomAdv;
	}
	
}
