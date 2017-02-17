package controle;

import java.io.IOException;

import metier.Rapport;

public class GenerationRapport {

	private Rapport r;
	private String statement;

	public GenerationRapport() {
		r = new Rapport();
	}

	public void genererRapportConflitsRecursifs() throws IOException {
		r.sauvegarderConflitsRecursifs(getStatement());
	}

	public void genererRapportInteraction() throws IOException {
		r.sauvegarderInteractions(getStatement());
	}

	public void genererRapportConflitsOrdre() throws IOException {
		r.sauvegarderConflitsOrdre(getStatement());
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	
}
