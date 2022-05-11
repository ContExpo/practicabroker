package arqsoft;

import java.rmi.*;
import java.util.*;

public class ClienteC {

    private static String ipBroker;
    private static String portBroker;

    private static void mostrarServicios (Servicios lista_servicios) {
    	System.out.println("****************************************************");
        System.out.println("Lista de servicios:");
        
		lista_servicios.methods.forEach((k, v) -> {
            System.out.println( "\"" + k + "\"" + " : " + v.toString());
        });
    }

    public static void main(String[] args) {

    	Scanner reader = new Scanner(System.in);
    	System.out.println("**CLIENTE**");
    	ipBroker = "localhost";
    	portBroker = "1099";
        System.setProperty("java.security.policy", "./java.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            //ClienteC client = new ClienteC(hostname[0], hostname[1]);

            // Se coge el objeto remoto del broker
            BrokerInterface brokerInterface = (BrokerInterface) 
            		Naming.lookup("//"+ ipBroker + ":" + portBroker + "/Broker847");
            do {
            	Servicios lista_servicios = brokerInterface.listarServicios();
                mostrarServicios(lista_servicios);
                System.out.print("Servicio que ejecutar: ");
                String opcion = reader.nextLine();
                Servicio servicio = brokerInterface.listarServicios().methods.get(opcion);
                String[] pars = new String[servicio.tiposParametros.length];
                for (int i = 0; i < servicio.tiposParametros.length; i++)
                {
                	System.out.println("Inserta " + servicio.tiposParametros[i] + " " + servicio.nombresParametros[i] + ": ");
                	pars[i] = reader.nextLine();
                }
                Respuesta respuesta = brokerInterface.ejecutarServicio(opcion, pars);
                System.out.println(respuesta);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
