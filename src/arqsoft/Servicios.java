import java.io.Serializable;
import java.util.HashMap;

public class Servicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12452234540915932L;
	public HashMap<String, Servicio> methods;
	
	public Servicios(HashMap<String, Servicio> methods) {
		this.methods = methods;
	}

}
