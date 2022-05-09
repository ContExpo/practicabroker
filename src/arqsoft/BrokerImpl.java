package arqsoft;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

//TODO: imports necesarios
public class BrokerImpl extends UnicastRemoteObject
implements Broker
{
	private static final long serialVersionUID = 1L;
	/**
	 * To track which unique names have already been registered by servers
	 */
	private ArrayList<String> registeredServers;
	/**
	 * Hashtable that saves every method registered by remote servers.
	 * To distinguish them if many server have same method the server.method notation will be used
	 */
	private Hashtable<String, Servicio> methods;

	protected BrokerImpl() throws RemoteException {
		super();
		this.registeredServers = new ArrayList<String>(5);
		this.methods = new Hashtable<String, Servicio>();
	}
	
	@Override
	public void alta_servicio(String nombre_servidor, String nom_servicio, Vector<Parametro> lista_param,
			String tipo_retorno) {
		String savedName = nombre_servidor + "." + nom_servicio;
		Servicio m = new Servicio (lista_param, tipo_retorno);
		methods.put(savedName, m);
	}
	
	@Override
	public void baja_servicio(String nombre_servidor, String nom_servicio) {
		methods.remove(nombre_servidor + "." + nom_servicio);
	}

	@Override
	public Enumeration<Servicio> lista_servicios() {
		return methods.elements();
	}

	@Override
	public Respuesta ejecutar_servicio(String nom_servicio, Vector parametros_servicio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ejecutar_servicio_asinc(String nom_servicio, Vector parametros_servicio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Respuesta obtener_respuesta_asinc(String nom_servicio) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}