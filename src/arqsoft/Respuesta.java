package arqsoft;

import java.io.Serializable;

public class Respuesta implements Serializable{

	@Override
	public String toString() {
		return "Respuesta [returned=" + returned + ", returnType=" + returnType + "]";
	}

	public String returned;
	public String returnType;
	
	public Respuesta(String returned, String returnType) {
		this.returned = returned;
		this.returnType = returnType;
	}
}
