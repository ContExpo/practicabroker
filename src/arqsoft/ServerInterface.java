import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Esta interfaz hace falta para que el Broker sepa còmo invocar metodos de los servidores
 * @author conte
 *
 */
public interface ServerInterface extends Remote {
	
	/**
	 * Ejecuta el servicio pasado por parametro, y si hace falta pasandole parametros
	 * @param servicio El servicio que se quiere ejecutar
	 * @param tipoParametros Los tipos de los parametros
	 * @param tipoRetorno Tipo del parametro de retorno del método a ejecutar
	 * @param parametros Los parametros pasados
	 * @return Devuelve una stringa con la variable de retorno
	 * @throws RemoteException 
	 */
	public String ejecutaServicio(String servicio, String[] parametros) throws RemoteException;
}