package arqsoft;

import java.util.Vector;

public class Servicio {

	private Vector<Parametro> lista_param;
	private String tipo_retorno;
	
	public Servicio(Vector<Parametro> lista_param, String tipo_retorno) {
		this.lista_param = lista_param;
		this.tipo_retorno = tipo_retorno;
	}

	public Vector<Parametro> getLista_param() {
		return lista_param;
	}

	public String getTipo_retorno() {
		return tipo_retorno;
	}

}
