package controle;

import java.sql.ResultSet;
import java.sql.SQLException;

import metier.Aspect;
import metier.Preoccupation;

public class AjoutAspect {
	
	private String nom,advice,operateur,point,nompreo;
	private int code_a,codepreo;
	private Aspect asp;
	private Preoccupation preo;
	private ResultSet rs;
	
	public AjoutAspect() {
		asp = new Aspect();
		preo = new Preoccupation();
	}
	
	public void ajouterAspect() throws SQLException{
		rs=preo.consulter("select code from preoccupation where nom='"+getNompreo()+"'");
		if (rs.next()) {
			codepreo=rs.getInt(1);
		}
		String sql="insert into aspect values(null,'"+getNom()+"','"+getAdvice()+"','"+getOperateur()+"','"+getPoint()+"','"+codepreo+"')";
		asp.ajouter(sql);
	}
	
	public ResultSet consulterAspect() throws SQLException{
		return asp.consulter("select DISTINCT * from aspect");
	}
	
	public ResultSet consulterCodeAspect() throws SQLException{
		return asp.consulter("select code from aspect where nom='"+getNom()+"' and advice='"+getAdvice()+"' and operation='"+getOperateur()+"' and point='"+getPoint()+"'");
	}
	
	public ResultSet consulterNomAspect() throws SQLException{
		return asp.consulter("select DISTINCT nom from aspect");
	}
//	/**
//	 * ignored method
//	 * @return
//	 * @throws SQLException
//	 */
//	public ResultSet consulterNomScenario() throws SQLException{
//		rs=asp.consulter("select code from aspect where nom='"+getNom()+"' and advice='"+getAdvice()+"'");
//		if (rs.next()) {
//			code_a=rs.getInt(1);
//		}
//		String sql="select scenario.nom from scenario where scenario.code_preo in ( "
//				+ "select preoccupation.code from preoccupation where preoccupation.code in ( "
//				+ "select aspect.code_preo from aspect where aspect.code='"+code_a+"'))";
//		return asp.consulter(sql);
//	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getNompreo() {
		return nompreo;
	}
	public void setNompreo(String nompreo) {
		this.nompreo = nompreo;
	}
}
