package controle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Aspect;
import metier.Base;
import metier.Preoccupation;

public class AjoutBase {

	private String usecase,nompreo,nomasp;
	private int codepreo,codeb,codeasp;
	private Base b;
	private Preoccupation preo;
	private Aspect asp;
	private ResultSet rs1,rs2,rs3;
	
	public AjoutBase(){
		b = new Base();
		preo = new Preoccupation();
		asp = new Aspect();
	}
	
	public void ajouterBase() throws SQLException{
		rs1=preo.consulter("select code from preoccupation where nom='"+getNompreo()+"'");
		if (rs1.next()) {
			codepreo=rs1.getInt(1);
		}
		b.ajouter("insert into base values(null,'"+getUsecase()+"','"+codepreo+"')");
		rs2=b.consulter("select code from base where nom='"+getUsecase()+"'");
		if (rs2.next()) {
			codeb=rs2.getInt(1);
		}
		rs3=asp.consulter("select code from aspect where nom='"+getNomasp()+"'");
		if (rs3.next()) {
			codeasp=rs3.getInt(1);
		}
		b.ajouter("insert into couper values(null,'"+codeasp+"','"+codeb+"')");
	}
	
	public ResultSet consulterBase() throws SQLException{
		String sql="select DISTINCT * from base;";
		return b.consulter(sql);
	}
	
	public ResultSet consulterCodeBase() throws SQLException{
		String sql = "select code from base where nom='"+getUsecase()+"'";
		return b.consulter(sql);
	}
	public ResultSet consulterNomBase() throws SQLException{
		String sql="select DISTINCT nom from base;";
		return b.consulter(sql);
	}
	
	public String getUsecase() {
		return usecase;
	}

	public void setUsecase(String usecase) {
		this.usecase = usecase;
	}
	
	public String getNompreo() {
		return nompreo;
	}
	
	public void setNompreo(String nompreo) {
		this.nompreo = nompreo;
	}
	
	public String getNomasp() {
		return nomasp;
	}
	
	public void setNomasp(String nomasp) {
		this.nomasp = nomasp;
	}
	
}
