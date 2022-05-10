package arqsoft;

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
	/**
	 * Devuelve una String para el usuario que lo informe de todos los servicios.
	 * Nota: no todos los servicios estàn disponibles, ya què el servidor puede quitarlos del broker
	 * @return la stringa
	 */
	public String listaServicios();
}