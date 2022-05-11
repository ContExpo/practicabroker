package arqsoft;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;

//TODO: imports necesarios
public class ServerB extends AbstractServer {
	private static final long serialVersionUID = 1L;
	private static final String brokerHostname = "localhost"; //IP y puerto del broker, es publico y estatico
	private static final int brokerPort = 1099;
    private static final String myHostname = "localhost";
    private static final int myPort = 1100;
    private static final String myIdentifier = myHostname + ":" + myPort + "/" + "ServerA847";
	
	protected ServerB() throws RemoteException {
		
	}

	public char getRandomLetter() throws RemoteException {
		return 'x';
	}

	public String toUpperCase(String text) throws RemoteException {
		return text.toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println("***Server A***");
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
			LocateRegistry.createRegistry(ServerB.myPort);
			System.out.println("Successfully started rmiregistry on port " + ServerB.myPort);
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
			ServerB obj = new ServerB();
			System.out.println("Creado server A!");
			//Registrar el objeto remoto
			Naming.rebind("//" + myIdentifier, obj);
			System.out.println("Estoy registrado en //" + myIdentifier + "!");
			
			//Aquí llamamos el broker para registrar servicios
			
			BrokerInterface broker =
					(BrokerInterface) Naming.lookup("//"+ brokerHostname + ":" + brokerPort + "/Broker847");
			System.out.println("Servicios registrados en el broker antes de mi:");
			Servicios lista_servicios = broker.listarServicios();
			lista_servicios.methods.forEach((k, v) -> {
	            System.out.println( k + " : " + v.toString());
	        });
			
			//**Registrando mis metodos**
			//Cuidado: el servidor tiene que registrarse para cada metodo como
			//IP:puerto:collectionName
			broker.altaServicio(myIdentifier, "getRandomNumber", new String[0], new String[0], "int");
			broker.altaServicio(myIdentifier, "getRandomLetter", new String[0], new String[0], "char");
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}