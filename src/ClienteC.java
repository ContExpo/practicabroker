import java.net.SocketPermission;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

public class ClienteC {

    private static String ipBroker;
    private static String portBroker;

    public ClienteC (String ipBroker_, String portBroker_) {
        this.ipBroker = ipBroker_;
        this.portBroker = portBroker_;
    }

    private static void mostrarServicios (ArrayList<String> lista_servicios) {

        System.out.println("Lista de servicios:)");
        System.out.println("0 -> salir del programa");
        int iter = 1;
        for (String servicio : lista_servicios) {
            System.out.println(iter + ". " + servicio);
            iter++;
        }
    }

    private static int leerOpcion() {
        Scanner reader = new Scanner(System.in);
        reader.close();
        return reader.nextInt();
    }

    public static void main(String[] args) {

        System.setProperty("java.security.policy", "../java.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String[] hostname = args[0].split(":");

        try {
            ClienteC client = new ClienteC(hostname[0], hostname[1]);

            // Se coge el objeto remoto del broker
            BrokerInterface brokerInterface = (BrokerInterface) Naming.lookup("//"+ ipBroker + ":" + portBroker + "/MyBrokerInterface");
            ArrayList<String> lista_servicios = brokerInterface.listar_servicios();

            mostrarServicios(lista_servicios);
            boolean fin = false;
            do {
                int opcion = leerOpcion();
                if (0 >= opcion || opcion > lista_servicios.size()) {
                    fin = true;
                } else {
                    String servicioEscogido = brokerInterface.listar_servicios().get(opcion - 1);
                    if (servicioEscogido.contains("insertar_libro")) {
                        System.out.println("Introduzca el parámetro: ");
                    } else {
                        int i = servicioEscogido.indexOf(" ");
                        int i2 = servicioEscogido.indexOf("(");
                        String servicio = servicioEscogido.substring(i+1, i2);
                        System.out.println("LOG1: " + servicio);
                        String respuesta = brokerInterface.ejecutar_servicio(servicio,new String[0]);
                        System.out.println(respuesta);
                    }
                }
            } while (!fin);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
