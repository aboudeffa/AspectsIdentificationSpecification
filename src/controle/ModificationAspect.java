package controle;

import java.sql.ResultSet;
import java.sql.SQLException;

import metier.Aspect;

public class ModificationAspect {

	int codeAspect;
	private String nomAspect,advice,base,operation,point;
	private Aspect asp;
	
	public ModificationAspect() {
		asp = new Aspect();
	}
	public void modifierAspect() throws SQLException{
		asp.modifier("UPDATE aspect set nom='"+getNomAspect()+"',advice='"+getAdvice()+"',operation='"+getOperation()+"',point='"+getPoint()+"'"
				+ "where code='"+getCodeAspect()+"'");
	}
	
	public int getCodeAspect() {
		return codeAspect;
	}
	public void setCodeAspect(int codeAspect) {
		this.codeAspect = codeAspect;
	}
	public String getNomAspect() {
		return nomAspect;
	}
	public void setNomAspect(String nomAspect) {
		this.nomAspect = nomAspect;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	} 
	
	
}
