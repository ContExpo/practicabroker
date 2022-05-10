package arqsoft;

import java.rmi.Remote;
import java.util.Enumeration;

public interface BrokerInterface extends Remote {
	//For servers
	public void altaServicio(String servidor, String servicio,  String[] tipoParametros,
			String[] nombresParametros, String tipoRetorno);
	public void bajaServicio(String servidor, String servicio);
	//For clients
	public Enumeration<Servicio> listaServicios();
	public Respuesta ejecutarServicio(String servicio, String[]parametros);
	
	public void ejecutarServicioAsync(String servicio, String[]parametros);
	public Respuesta obtenerRespuestaAsync(String servicio);
}
