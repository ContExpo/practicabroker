package arqsoft;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

//TODO: imports necesarios
public class CollectionImpl extends UnicastRemoteObject
implements Collection
{
	//Private member variables
	private int m_number_of_books;
	private String m_name_of_collection;
	//Constructor
	public CollectionImpl() throws RemoteException
	{
	super();//Llama al constructor de UnicastRemoteObject
	this.m_name_of_collection = "Collection de Alessio";
	m_number_of_books = 420;
	}
	
	public static void main(String args[])
	{
		//Fijar el directorio donde se encuentra el java.policy
		//El segundo argumento es la ruta al java.policy
		System.setProperty("java.security.policy", "C:\\Users\\aless\\Desktop\\arqsoft\\java.policy");
		//Crear administrador de seguridad
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		//nombre o IP del host donde reside el objeto servidor
		
		try
		{
			LocateRegistry.createRegistry(3201);
		}
		catch (ExportException e)
		{
			System.out.println("Registry service already started");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		String hostName = "localhost:3201"; //se puede usar "IPhostremoto:puerto"
		//Por defecto RMI usa el puerto 1099
		try
		{
			//LocateRegistry.createRegistry(3201);
			System.out.println("Created registry service");
			// Crear objeto remoto
			CollectionImpl obj = new CollectionImpl();
			System.out.println("Creado!");
			//Registrar el objeto remoto
			Naming.rebind("//" + hostName + "/MyCollection", obj);
			System.out.println("Estoy registrado!");
			}
			catch (Exception ex)
			{
			System.out.println(ex);
			}
		}
	
	@Override
	public int number_of_books() throws RemoteException
	{
		return m_number_of_books;
	}
	
	@Override
	public String name_of_collection() throws RemoteException {
		return m_name_of_collection;
	}
	@Override
	public void name_of_collection(String _new_value) throws RemoteException {
		this.m_name_of_collection = _new_value;
		
	}
}