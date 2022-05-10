import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

public class Broker implements BrokerInterface {

    private static String ipBroker;
    private static String portBroker;
    ArrayList<Servidor> servidores = new ArrayList<Servidor>(); 

    public Broker(String ipBroker_, String portBroker_) {
		this.ipBroker = ipBroker_;
        this.portBroker = portBroker_;
    }

    public String ejecutar_servicio(String nom_servicio, String[] parametros_servicio) {
        boolean encontrado = false;
        Iterator<Servidor> iterServidor = servidores.iterator();
        Servidor servidor = null;
        String tipo_retorno = "";
        String[] lista_param = null;
        // Se itera en la lista de servidores con sus servicios, hasta encontrar el servicio
        // [nom_servicio] y dejar en la variable [servidor] el servidor que contiene dicho servicio
        while (iterServidor.hasNext() && !encontrado) {
            servidor = iterServidor.next();
            Iterator<Servicio> servicio = servidor.getServicios().iterator();
            while (servicio.hasNext() && !encontrado) {
                Servicio iterServ = servicio.next();
                tipo_retorno = iterServ.getTipoRetorno();
                lista_param = iterServ.getListaParam();

                if (iterServ.getNombre().equals(nom_servicio)) {
                    encontrado = true;
                }
            }
        }
        if (encontrado) {
            try {
                // Se coge el objeto remoto del broker
                Registry registry = LocateRegistry.getRegistry(servidor.getIP());
                ServerInterface server = (ServerInterface) registry.lookup(servidor.getNombre());
                return server.ejecuta_metodo(nom_servicio,lista_param,tipo_retorno,parametros_servicio);
                
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void registrar_servidor(String host_remoto_IP_port, String nombre_registrado) {
        Servidor servidor = new Servidor(host_remoto_IP_port, nombre_registrado);
        servidores.add(servidor);
    }

    public void registrar_servicio(String nombre_regitrado, String nom_servicio, String[]
            lista_param, String tipo_retorno) {
        for (Servidor servidor : servidores) {
            if (servidor.getNombre().equals(nombre_regitrado)) {
                servidor.addServicio(nom_servicio, lista_param, tipo_retorno);
            }
        }
    }

    public ArrayList<String> listar_servicios() {
        ArrayList<String> listado = new ArrayList<String>();
        Iterator<Servidor> iterServer = servidores.iterator();
        while (iterServer.hasNext()) {
            Iterator<Servicio> iterServicio = iterServer.next().getServicios().iterator();
            while (iterServicio.hasNext()) {
                listado.add(iterServicio.next().toString());
            }
        }
        return listado;
    }

    public static void main (String [] args) throws MalformedURLException {

        System.setProperty("java.security.policy", "../java.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String[] hostname = args[0].split(":");

        try {
			Broker broker = new Broker(hostname[0], hostname[1]);
            System.out.println("Creado!");

            Naming.rebind("//" + ipBroker + ":" + portBroker + "/MyBrokerInterface", broker);
            System.out.println("Estoy registrado!");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
