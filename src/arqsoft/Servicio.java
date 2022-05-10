package arqsoft;

public class Servicio {

	private String[] tiposParametros;
	private String[] nombresParametros;
	private String tipoRetorno;
	
	public Servicio(String[] tipoParametros, String[] nombresParametros, String tipo_retorno) {
		this.tiposParametros = tipoParametros;
		this.nombresParametros = nombresParametros;
		this.tipoRetorno = tipo_retorno;
	}

	public String[] getTiposParametros() {
		return tiposParametros;
	}

	public String[] getNombresParametros() {
		return nombresParametros;
	}

	
	public String getTipoRetorno() {
		return tipoRetorno;
	}

}