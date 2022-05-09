package arqsoft;
import java.rmi.Remote;
import java.rmi.RemoteException;
//TODO: otros imports
public interface ServerB extends Remote {
	//metodos de la interface
	char getRandomLetter() throws RemoteException;
	int getHalf(int number) throws RemoteException;
	//String practica_hecha(String _mi_NIA, String _mi_directorio) throws RemoteException;
}
