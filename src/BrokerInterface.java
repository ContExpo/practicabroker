import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

public interface BrokerInterface extends Remote {
	//For servers
	public void altaServicio(String servidor, String servicio,  String[] listaParametros, String tipoRetorno) throws RemoteException;
	public void bajaServicio(String servidor, String servicio) throws RemoteException;

	//For clients
	public ArrayList<String> listarServicios() throws RemoteException;
	public String ejecutarServicio(String servicio, String[]parametros) throws RemoteException;
	
	public void ejecutarServicioAsync(String servicio, String[]parametros) throws RemoteException;
	public String obtenerRespuestaAsync(String servicio) throws RemoteException;
}
