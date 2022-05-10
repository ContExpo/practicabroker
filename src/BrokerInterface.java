import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

public interface BrokerInterface extends Remote {
	String ejecutar_servicio(String nom_servicio, String[] parametros_servicio) throws RemoteException;
	void registrar_servidor(String host_remoto_IP_port, String nombre_registrado) throws RemoteException;
	void registrar_servicio(String nombre_regitrado, String nom_servicio, String[] lista_param,
							String tipo_retorno) throws RemoteException;
	ArrayList<String> listar_servicios() throws RemoteException;
}
