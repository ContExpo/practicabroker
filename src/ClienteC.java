import java.net.SocketPermission;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteC {

    private static String ipBroker;

    public ClienteC (String ipBroker_) {
        this.ipBroker = ipBroker_;
    }

    private static int numServicios (BrokerInterface brokerInterface) {
        ArrayList<String> lista_servicios = brokerInterface.listar_servicios();
        return lista_servios.size();
    }

    private static void mostrarServicios (BrokerInterface brokerInterface) {
        ArrayList<String> lista_servicios = brokerInterface.listar_servicios();

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

        try {
            ClientC client = ClientC(args[0]);

            // Se coge el objeto remoto del broker
            BrokerInterface brokerInterface = (BrokerInterface) Naming.lookup("//"+ hostname + "/MyBrokerInterface");

            mostrarServicios(brokerInterface);
            boolean fin = false;
            do {
                int opcion = leerOpcion();
                if (0 >= opcion || opcion > numServicios(brokerInterface)) {
                    fin = true;
                } else {
                    String servicioEscogido = lista_servicios.get(opcion - 1);
                    if (servicioEscogido.contains("insertar_libro")) {
                        System.out.println("Introduzca el parámetro: ");
                        teclado.nextLine();
                        String [] parametros = {teclado.nextLine()};
						brokerInterface.ejecutar_servicio("insertar_libro",parametros);
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
        }
    }
}
