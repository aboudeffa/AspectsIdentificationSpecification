package metier;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Rapport {

	public void sauvegarderConflitsRecursifs(String s) throws IOException {

		PrintWriter pwr = new PrintWriter(new FileWriter("FileConflitsRecursifs.txt", true));
		pwr.println("The recursive conflicts between aspects are :");
		pwr.println(s);
		pwr.close();

	}
	
	public void sauvegarderInteractions(String s) throws IOException {

		PrintWriter pwr = new PrintWriter(new FileWriter("FileInteractions.txt", true));
		pwr.println("The interaction between aspects are :");
		pwr.println(s);
		pwr.close();

	}
	
	public void sauvegarderConflitsOrdre(String s) throws IOException {

		PrintWriter pwr = new PrintWriter(new FileWriter("FileConflitsOrdre.txt", true));
		//pwr.println("The order conflicts between are :");
		pwr.println(s);
		pwr.close();

	}
}
