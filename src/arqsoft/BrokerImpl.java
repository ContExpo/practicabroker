package arqsoft;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;

public class BrokerImpl extends UnicastRemoteObject
implements BrokerInterface
{
	private static final long serialVersionUID = 1L;
	/**
	 * Hashtable that saves every method registered by remote servers.
	 * To distinguish them if many server have same method the server.method notation will be used
	 */
	private Hashtable<String, Servicio> methods;

	protected BrokerImpl() throws RemoteException {
		super();
		this.methods = new Hashtable<String, Servicio>();
	}
	
	@Override
	public void altaServicio(String servidor, String servicio,  String[] tipoParametros,
			String[] nombresParametros, String tipoRetorno) {
		String savedName = servidor + "." + servicio;
		Servicio m = new Servicio (tipoParametros, nombresParametros, tipoRetorno);
		methods.put(savedName, m);
	}
	
	@Override
	public void bajaServicio(String nombre_servidor, String nom_servicio) {
		methods.remove(nombre_servidor + "." + nom_servicio);
	}

	@Override
	public Enumeration<Servicio> listaServicios() {
		return methods.elements();
	}

	@Override
	public Respuesta ejecutarServicio(String servicio, String[]parametros) {
		// TODO Auto-generated method stub
		return null;
	}

	public void ejecutarServicioAsync(String servicio, String[]parametros) {
		
	}
	public Respuesta obtenerRespuestaAsync(String servicio) {
		return null;
	}
	
	
}