import java.rmi.*;

public class ServerB extends AbstractServer {

	private static String hostName;

	public ServerB(String hostname_) {
		this.hostName = hostname_;
	}

	public String dar_hora() {
        DateFormat dateF = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateF.format(date);
    }

	public String dar_fecha() {
        DateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateF.format(date);
    }

	public static void main (String [] args) {

		System.setProperty("java.security.policy", "../java.policy");
		System.setSecurityManager(new SecurityManager());

        try {
			ServerB server = new ServerB(args[0]);

			//Se crea un stub y posteriormente se introduce al registro
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = null;  

            //Intenta crear un nuevo registro o lo localiza si ya existe uno.    
			try{
				registry = LocateRegistry.createRegistry(port);
			}
			catch(RemoteException e){
				registry = LocateRegistry.getRegistry(port); 
			}

            String nombre_registro = "ServerBInterface";
            registry.bind(nombre_registro, stub);
			System.err.println("ServerB registrado en registro RMI");

            // Se coge el objeto remoto del broker
            registry = LocateRegistry.getRegistry(ipBroker);
            BrokerInterface brokerInterface = (BrokerInterface) registry.lookup("BrokerInterface");

            // Se registra el servidor dentro del broker
            brokerInterface.registrar_servidor(ipRegistro,nombre_registro);
            System.err.println("ServerB registrado en Broker");

            // Se registran los servicios dentro del broker
            brokerInterface.registrar_servicio(nombre_registro, "dar_hora",new String[0],"String");
            brokerInterface.registrar_servicio(nombre_registro, "dar_fecha",new String[0],"String");
            System.err.println("Servicios de ServerB registrados en Broker");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
