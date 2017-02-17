package controle;

import java.sql.ResultSet;
import java.sql.SQLException;

import metier.Base;

public class ModificationBase {

	private int codeBase;
	private String nomusecase;
	private Base b;
	private ResultSet rs;
	
	public ModificationBase() {
		b = new Base();
	}
	public void modifierBase() throws SQLException{
		b.modifier("UPDATE base set nom='"+getNomUsecase()+"' where code='"+getCodeBase()+"'");
	}
	
	public int getCodeBase() {
		return codeBase;
	}
	public void setCodeBase(int codeBase) {
		this.codeBase = codeBase;
	}
	public String getNomUsecase() {
		return nomusecase;
	}
	public void setNomUsecase(String nomusecase) {
		this.nomusecase = nomusecase;
	}
}
