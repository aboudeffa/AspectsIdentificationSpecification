package metier;

public class SingletonConnectionBDD {
	
	private static ConnectionBDD cbdd;
	
	SingletonConnectionBDD(){
		super();
	}
	
	static {
			cbdd = new ConnectionBDD();
	}
	
	public static ConnectionBDD getConnectionBDD() {
		return cbdd;
	}
	
}
