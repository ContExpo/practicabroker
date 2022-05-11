package arqsoft;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

public class BrokerImpl extends UnicastRemoteObject
implements BrokerInterface
{
	private static final long serialVersionUID = 1L;
	private static final int myPort = 1099;
	private static final String myHostname = "localhost:" + myPort;
	private boolean bajadoPrimerServicio = false;
	/**
	 * Hashtable that saves every method registered by remote servers.
	 * To distinguish them if many server have same method the server.method notation will be used
	 */
	private HashMap<String, Servicio> methods;

	protected BrokerImpl() throws RemoteException {
		super();
		this.methods = new HashMap<String, Servicio>();
	}
	
	@Override
	public void altaServicio(String servidor, String servicio,  String[] tipoParametros,
			String[] nombresParametros, String tipoRetorno) {
		String savedName = servidor + "." + servicio;
		Servicio m = new Servicio (tipoParametros, nombresParametros, tipoRetorno);
		methods.put(savedName, m);
		System.out.println("Registered new service : " + savedName + " : " + m.toString());
	}
	
	@Override
	public void bajaServicio(String nombre_servidor, String nom_servicio) {
		methods.remove(nombre_servidor + "." + nom_servicio);
	}

	@Override
	public Servicios listarServicios() {
		return new Servicios(methods);
	}

	@Override
	public Respuesta ejecutarServicio(String servicio, String[]parametros) {
		System.out.println("Recuesta de invocacion de " + servicio);
		if(!methods.containsKey(servicio)) {
			System.out.println("Servicio no encontrado");
			return null;
		}
			
		String serverName = servicio.substring(0, servicio.lastIndexOf("."));
		String methodName = servicio.substring(servicio.lastIndexOf(".") + 1);
		Servicio s = methods.get(servicio);
		
		System.out.println("Invocando " + methodName + " en " + serverName);
		try {
			ServerInterface server = (ServerInterface) 
					Naming.lookup("//"+ serverName);
			String r = server.ejecutaServicio(methodName, parametros);
			return new Respuesta(r, s.tipoRetorno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void ejecutarServicioAsync(String servicio, String[]parametros) {
		return;
	}
	public Respuesta obtenerRespuestaAsync(String servicio) {
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("***BROKER***");
		//Fijar el directorio donde se encuentra el java.policy
		//El segundo argumento es la ruta al java.policy
		System.setProperty("java.security.policy", "./java.policy");
		//Crear administrador de seguridad
		if (System.getSecurityManager() == null) {
		    System.setSecurityManager(new SecurityManager());
		}
		
		try
		{
			//Es el equivalente de ejecutar rmiregistry
			LocateRegistry.createRegistry(BrokerImpl.myPort);
			System.out.println("Successfully started rmiregistry");
		}
		catch (ExportException e)
		{
			System.out.println("Registry service already started");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		try
		{
			// Crear objeto remoto
			BrokerImpl obj = new BrokerImpl();
			System.out.println("Creado BrokerImpl!");
			//Registrar el objeto remoto
			Naming.rebind("//" + myHostname + "/Broker847", obj);
			System.out.println("Estoy registrado!");
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}