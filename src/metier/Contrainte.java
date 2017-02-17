package metier;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Contrainte {

	public void ajouterContrainte(String contrainte) throws IOException {
		
		PrintWriter pwr = new PrintWriter(new FileWriter("FileContraintes.txt",true));
		pwr.println(contrainte);
		pwr.close();
		
	}
	
}
