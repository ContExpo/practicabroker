package arqsoft;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente
{	
	@SuppressWarnings({ "removal", "deprecation" })
	public static void main(String[] args){
		System.setProperty("java.security.policy", "./java.policy");
		if (System.getSecurityManager() == null) {
			
			System.setSecurityManager(new SecurityManager());
		}
		try
		{
			//Paso 1 - Obtener una referencia al objeto servidor creado anteriormente
			String hostname = "localhost:1099"; //se puede usar "IP:puerto"
			//server sería el objeto remoto
			Collection server =
			(Collection) Naming.lookup("//"+ hostname + "/MyCollection");
			//Paso 2 - Invocar remotamente los metodos del objeto servidor:
			//TODO: obtener el nombre de la colecci ́on y el n ́umero de libros
			//TODO: cambiar el nombre de la coleccion y ver que ha funcionado
			System.out.printf("The remote collection \"%s\" has %d books", server.name_of_collection(), server.number_of_books());
			server.name_of_collection("Nuevo nombre + guapo");
			
			//PARA ENTREGAR
			//String hostname = "155.210.152.177"; //IP del profesor
			//String resultado = server.practica_hecha("A847803", "/home/a847803/RMI/");
			//System.out.println("El profesor me responde: " + resultado);
			
		}
		catch (Exception ex)
		{
		System.out.println(ex);
		}
	}
}