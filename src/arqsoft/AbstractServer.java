package arqsoft;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class AbstractServer extends UnicastRemoteObject implements ServerInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6464229431270999986L;

	protected AbstractServer() throws RemoteException {
		super();
	}

	/**
	 * Método que, dado el nombre de otro método, sus parámetros
	 * y su tipo de retorno, busca ese método empleando la 
	 * reflexividad de Java sobre la propia clase y lo ejecuta, 
	 * devolviendo el resultado en un String.
	 * @param servicio - Nombre del método a ejecutar
	 * @param tipoParametros - Array con los tipos de los parámetros del 
	 * método a ejecutar
	 * @param retorno - Tipo del parámetro de retorno del método a ejecutar
	 * @param parametros - Array con los parámetros del método a ejecutar
	 * @return Un String con la respuesta del método ejecutado.
	 */
	public String ejecutaServicio(String servicio, String[] parametros) 
			throws RemoteException 
	{
		//this is an instance of something that extends AbstractServer
		Class<? extends AbstractServer> c = this.getClass();
		try
		{
			//We get the method
			Method method = c.getMethod(servicio);
			//Si no hace falta devolver nada
			if (method.getReturnType() == void.class) {
				//Parametros would need a Object[] cast, but who cares?
				method.invoke(this, parametros);
				return "";
			}
			else {
				return method.invoke(this, parametros).toString();
			}
		}
		catch (IllegalAccessException e){
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
	
	@Override
	public String listaServicios() {
		String toRet = "";
		Method[] allMethods = this.getClass().getDeclaredMethods();
		for (Method method : allMethods) {
		    if (Modifier.isPublic(method.getModifiers())) {
		    	if (!method.getName().equals("listaServicios"))
		    		toRet += method.toString() + "\n";
		    }
		}
		return toRet;
	}
}
