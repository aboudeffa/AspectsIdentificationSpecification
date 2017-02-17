package controle;

import java.sql.ResultSet;
import java.sql.SQLException;

import metier.Acteur;
import metier.Preoccupation;
import metier.Scenario;
import metier.EtapeScenario;

public class AjoutPreoccupation {

	private String nom_p,nom_a,nom_s,type_s,action;
	private String ida,idstp;
	private int code_sc;
	private Preoccupation preo;
	private Scenario sc;
	private Acteur act;
	private EtapeScenario stp;
	private ResultSet rs1,rs2;
	private int codeP,codeA,codeS,codeStp;
	
	public AjoutPreoccupation() {
		preo = new Preoccupation();
		act = new Acteur();
		sc = new Scenario();
		stp = new EtapeScenario();
	}
	public void ajouterPreoccupation() throws SQLException{
		preo.ajouter("insert into preoccupation values(null,'"+getNom_p()+"')");
	}
	public void ajouterActeur() throws SQLException{
		rs1=preo.consulter("select code from preoccupation where nom='"+getNom_p()+"'");
		if (rs1.next()) {
			codeP=Integer.parseInt(rs1.getString(1));
		}
		act.ajouter("insert into actor values(null,'"+getNom_a()+"')");
		rs2=act.consulter("select id from actor where nom='"+getNom_a()+"'");
		if (rs2.next()) {
			codeA=Integer.parseInt(rs2.getString(1));
		}
		act.ajouter("insert into posseder values(null,'"+codeP+"','"+codeA+"')");
	}
	public ResultSet consulterPreoccupation() throws SQLException{
		String sql="select nom from preoccupation";
		return preo.consulter(sql);
	}
	public ResultSet consulterActeurs() throws SQLException{
		String sql="select * from actor";
		return act.consulter(sql);	
	}
	public ResultSet consulterActeursSpecifiques() throws SQLException{
		String sql="select nom from actor";
		return act.consulter(sql);	
	}
	public ResultSet retourIdActeur() throws SQLException{
		String sql_id="select id from actor where nom='"+getNom_a()+"'";
		return act.retourId(sql_id);
	}
	public void ajouterScenario() throws SQLException{
		rs1=preo.consulter("select code from preoccupation where nom='"+getNom_p()+"'");
		if (rs1.next()) {
			codeS=Integer.parseInt(rs1.getString(1));
		}
		sc.ajouter("insert into scenario values(null,'"+getNom_s()+"','"+getType_s()+"','"+codeS+"')");
	}
	public ResultSet consulterScenario() throws SQLException{
		String sql="select * from scenario";
		return sc.consulter(sql);
	}
	public ResultSet consulterIdScenario() throws SQLException{
		String sql="select code from scenario where nom='"+getNom_s()+"'";
		return sc.consulter(sql);
	}
	public void ajouterStep() throws SQLException {
		rs1=sc.consulter("select code from scenario where nom='"+getNom_s()+"' and type='"+getType_s()+"'");
		if (rs1.next()) {
			codeStp=rs1.getInt(1);
		}
		stp.ajouter("insert into etape values(null,'"+getAction()+"','"+codeStp+"')");
	}
	public ResultSet consulterStep() throws SQLException{
		String sql="select action from etape where code_sc='"+getCode_sc()+"'";
		return stp.consulter(sql);
	}
	
	public ResultSet retourIdStep() throws SQLException{
		String sql_id="select code from etape where action='"+getAction()+"'";
		return stp.retourId(sql_id);
	}
	public String getNom_p() {
		return nom_p;
	}
	public void setNom_p(String nom) {
		this.nom_p = nom;
	}
	public String getNom_a() {
		return nom_a;
	}
	public void setNom_a(String nom_a) {
		this.nom_a = nom_a;
	}
	public String getIda() {
		return ida;
	}
	public void setIda(String ida) {
		this.ida = ida;
	}
	public String getNom_s() {
		return nom_s;
	}
	public void setNom_s(String nom_s) {
		this.nom_s = nom_s;
	}
	public String getType_s() {
		return type_s;
	}
	public void setType_s(String type_s) {
		this.type_s = type_s;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIdstp() {
		return idstp;
	}
	public void setIdstp(String idstp) {
		this.idstp = idstp;
	}
	public int getCode_sc() {
		return code_sc;
	}
	public void setCode_sc(int code_sc) {
		this.code_sc = code_sc;
	}
	
}
