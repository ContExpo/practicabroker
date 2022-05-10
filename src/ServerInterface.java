import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	String ejecuta_metodo(String servicio, String [] tipoParametros, String retorno, String [] parametros) throws RemoteException;
}
