import java.lang.reflect.*;
import java.rmi.*;

public abstract class AbstractServer implements ServerInterface{

	public String ejecuta_metodo(String servicio, String [] tipoParametros, String retorno, String [] parametros) {
		Class c = this.getClass();
		try{
			//Se obtiene le método mediante reflexividad
			Method method = c.getMethod(servicio, getClassArray(tipoParametros));
			//Comprueba el tipo de retorno
			if(retorno.equals("void")){
				method.invoke(this, parametros);
				return "";
			}
			else if(retorno.equals("String[]")){
				String [] lista = (String [])method.invoke(this, parametros);
				String respuesta = "";
				for(String s:lista){
					respuesta+=s +"\n";
				}
				return respuesta;
			}
			else{
				return (String)method.invoke(this, parametros);
			}
		}
		catch (NoSuchMethodException e){
			e.printStackTrace();
		}
		catch (IllegalAccessException e){
			e.printStackTrace();
		}
		catch (InvocationTargetException e){
			e.printStackTrace();
		}
		return"";
	}

	protected Class[] getClassArray(String [] param){
		Class [] array = new Class[param.length];
		try{
			for(int i=0;i<param.length;i++){
				int index = param[i].indexOf(" ");
				if((param[i].substring(0, index)).equals("String")){
					array[i] = Class.forName("java.lang.String");
				}
			}
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return array;
	}
}
