package arqsoft;
import java.rmi.Remote;
import java.rmi.RemoteException;
//TODO: otros imports
public interface ServerA extends Remote {
	//metodos de la interface
	int getRandomNumber() throws RemoteException;
	int getDouble(int number) throws RemoteException;
	//String practica_hecha(String _mi_NIA, String _mi_directorio) throws RemoteException;
}
