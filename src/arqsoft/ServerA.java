package arqsoft;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;

//TODO: imports necesarios
public class ServerA extends AbstractServer {
	private static final long serialVersionUID = 1L;
	private static final String brokerHostname = "localhost"; //IP y puerto del broker, es publico y estatico
	private static final int brokerPort = 1099;
    private static final String myHostname = "localhost"; //IP del host del registro RMI
    private static final int myPort = 1100; //Puerto default de RMI
	
	protected ServerA() throws RemoteException {
		
	}

	@SuppressWarnings("unused")
	private int getRandomNumber() throws RemoteException {
		return (int) (Math.random()*100);
	}

	@SuppressWarnings("unused")
	private int getDouble(int number) throws RemoteException {
		return number*2;
	}
	
	public char getRandomLetter() throws RemoteException {
		return 'x';
	}

	public String toUpperCase(String text) throws RemoteException {
		return text.toUpperCase();
	}
	
	public static void main(String[] args) {
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
			ServerA obj = new ServerA();
			System.out.println("Creado server A!");
			//Registrar el objeto remoto
			Naming.rebind("//" + myHostname + "/ServerA", obj);
			System.out.println("Estoy registrado!");
			
			//Aquì llamamos el broker para registrar servicios
			
			BrokerInterface broker =
					(BrokerInterface) Naming.lookup("//"+ brokerHostname + ":" + brokerPort + "/Broker");
			System.out.println(broker.listaServicios());
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}