import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public abstract class AbstractServer extends UnicastRemoteObject implements ServerInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6464229431270999986L;

	protected AbstractServer() throws RemoteException {
		super();
	}

	/**
	 * Metodo que, dado el nombre de otro metodo, sus parametros
	 * y su tipo de retorno, busca ese metodo empleando la 
	 * reflexividad de Java sobre la propia clase y lo ejecuta, 
	 * devolviendo el resultado en un String.
	 * @param servicio - Nombre del metodo a ejecutar
	 * @param tipoParametros - Array con los tipos de los paremetros del 
	 * metodo a ejecutar
	 * @param retorno - Tipo del parametro de retorno del metodo a ejecutar
	 * @param parametros - Array con los parametros del metodo a ejecutar
	 * @return Un String con la respuesta del metodo ejecutado.
	 */
	public String ejecutaServicio(String servicio, String[] parametros) 
			throws RemoteException 
	{
		//this is an instance of something that extends AbstractServer
		Class<? extends AbstractServer> c = this.getClass();
		try
		{
			//We get the method
			Class[] classArr = this.generateClassArray(parametros);
			Method method = c.getMethod(servicio, classArr);
			Object[] passablePars = this.generateParams(parametros, classArr);
			if (method.getReturnType() == void.class) {
				//Parametros would need a Object[] cast, but who cares?
				method.invoke(this, passablePars);
				return "";
			}
			else {
				return method.invoke(this, parametros).toString();
			}
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (NoSuchMethodException e){
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return"";
	}

	private Object[] generateParams(String[] parametros, Class[] classArr) {
		Object[] toRet = new Object[parametros.length];
		for (int i = 0; i<parametros.length; i++)
		{
			if (classArr[i] == String.class) {
				toRet[i] = parametros[i];
			}
			else 
				toRet[i] = new Integer(parametros[i]);
		}
		return toRet;
	}

	private Class[] generateClassArray(String[] parametros) {
		Class[] toRet = new Class[parametros.length];
		for (int i = 0; i<parametros.length; i++)
		{
			try {
				Integer.parseInt(parametros[i]);
				toRet[i] = Integer.class;
			} catch (NumberFormatException e) {
				toRet[i] = java.lang.String.class;
			}
		}
		return toRet;
	}
	
}
