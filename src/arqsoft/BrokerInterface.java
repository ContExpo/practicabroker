package arqsoft;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Enumeration;

public interface BrokerInterface extends Remote {
	//For servers
	public void altaServicio(String servidor, String servicio,  String[] tipoParametros,
			String[] nombresParametros, String tipoRetorno) throws RemoteException;
	public void bajaServicio(String servidor, String servicio) throws RemoteException;
	//For clients
	public Servicios listaServicios() throws RemoteException;
	public Respuesta ejecutarServicio(String servicio, String[]parametros) throws RemoteException;
	
	public void ejecutarServicioAsync(String servicio, String[]parametros) throws RemoteException;
	public Respuesta obtenerRespuestaAsync(String servicio) throws RemoteException;
}
