package arqsoft;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

//TODO: imports necesarios
public class ServerBImpl extends UnicastRemoteObject
implements ServerB
{
	private static final long serialVersionUID = 1L;
	
	protected ServerBImpl() throws RemoteException {
		super();
	}

	@Override
	public char getRandomLetter() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHalf(int number) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}