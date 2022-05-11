import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;

//TODO: imports necesarios
public class ServerA extends AbstractServer {
	private static final long serialVersionUID = 1L;
	private static String brokerHostname = "localhost"; //IP y puerto del broker, es publico y estatico
	private static int brokerPort = 1099;
    private static final String myHostname = "localhost";
    private static final int myPort = 1100;
    private static final String myIdentifier = myHostname + ":" + myPort + "/" + "ServerA847";
	
	protected ServerA() throws RemoteException {
		
	}

	public int getRandomNumber() throws RemoteException {
		BrokerInterface broker;
		try {
			broker = (BrokerInterface) Naming.lookup("//"+ brokerHostname + ":" + brokerPort + "/Broker847");
			broker.bajaServicio(myIdentifier, "getRandomNumber");
			broker.altaServicio(myIdentifier, "getZero", new String[0], new String[0], "int");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		return (int) (Math.random()*100);
	}
	
	public int getZero() throws RemoteException {
		return 0;
	}

	public int getDouble(Integer number) throws RemoteException {
		return number*2;
	}
	
	public static void main(String[] args) {
		if (args.length == 2) {
			brokerHostname = args[1];
			brokerPort = Integer.parseInt(args[2]);
		}
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
			LocateRegistry.createRegistry(ServerA.myPort);
			System.out.println("Successfully started rmiregistry on port " + ServerA.myPort);
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
			ServerA obj = new ServerA();
			System.out.println("Creado server A!");
			//Registrar el objeto remoto
			Naming.rebind("//" + myIdentifier, obj);
			System.out.println("Estoy registrado en //" + myIdentifier + "!");
			
			//Aqu� llamamos el broker para registrar servicios
			
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
			String[] parTypes= {"int"};
			String[] parNames = {"number"};
			broker.altaServicio(myIdentifier, "getRandomNumber", new String[0], new String[0], "int");
			broker.altaServicio(myIdentifier, "getDouble", parTypes, parNames, "int");
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}