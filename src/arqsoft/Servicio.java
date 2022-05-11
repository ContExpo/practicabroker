package arqsoft;

import java.io.Serializable;
import java.util.Arrays;

public class Servicio implements Serializable {

	public String[] tiposParametros;
	public String[] nombresParametros;
	public String tipoRetorno;
	
	@Override
	public String toString() {
		return "Servicio [tiposParametros="
				+ Arrays.toString(tiposParametros) + ", nombresParametros=" + Arrays.toString(nombresParametros)
				+ ", tipoRetorno=" + tipoRetorno + "]";
	}

	public Servicio(String[] tipoParametros, String[] nombresParametros, String tipo_retorno) {
		this.tiposParametros = tipoParametros;
		this.nombresParametros = nombresParametros;
		this.tipoRetorno = tipo_retorno;
	}


	
	

}