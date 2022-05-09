package arqsoft;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

//TODO: imports necesarios
public class ServerAImpl extends UnicastRemoteObject
implements ServerA
{
	private static final long serialVersionUID = 1L;
	
	protected ServerAImpl() throws RemoteException {
		super();
	}

	@Override
	public int getRandomNumber() throws RemoteException {
		return 0;
	}

	@Override
	public int getDouble(int number) throws RemoteException {
		return number*2;
	}
	
}