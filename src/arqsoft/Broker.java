package arqsoft;

import java.lang.reflect.Method;
import java.rmi.Remote;
import java.util.Enumeration;
import java.util.Vector;

public interface Broker extends Remote {
	//For servers
	void alta_servicio(String nombre_servidor, String nom_servicio, Vector<Parametro> lista_param, String tipo_retorno);
	void baja_servicio(String nombre_servidor, String nom_servicio);
	//For clients
	Enumeration<Servicio> lista_servicios();
	Respuesta ejecutar_servicio(String nom_servicio, Vector parametros_servicio);
	void ejecutar_servicio_asinc(String nom_servicio, Vector parametros_servicio);
	Respuesta obtener_respuesta_asinc(String nom_servicio);
}
