package controle;

import java.io.IOException;

import metier.Contrainte;

public class GenerationContraintes {

	private String contrainte;
	private Contrainte cont;
	
	public GenerationContraintes(){
		cont = new Contrainte();
	}
	
	public void genererContraintes() throws IOException{
		cont.ajouterContrainte(getContrainte());
	}

	public String getContrainte() {
		return contrainte;
	}

	public void setContrainte(String contrainte) {
		this.contrainte = contrainte;
	}
	
}
