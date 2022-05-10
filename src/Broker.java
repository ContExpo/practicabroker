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

    public void altaServicio(String servidor, String servicio, String[] listaParametros, String tipoRetorno) {
        for (Servidor srv : servidores) {
            if (srv.getNombre().equals(servidor)) {
                srv.addServicio(servicio, listaParametros, tipoRetorno);
            }
        }
	}

    public void bajaServicio(String servidor, String servicio) {
		for (Servidor srv : servidores) {
            if (srv.getNombre().equals(servidor)) {
                srv.removeServicio(servicio);
            }
        }
	}

    public ArrayList<String> listarServicios() {
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

    public String ejecutarServicio(String nom_servicio, String[] parametros_servicio) {
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

    public void ejecutarServicioAsync(String servicio, String[]parametros) {
		
	}

	public String obtenerRespuestaAsync(String servicio) {
		return null;
	}

    public static void main (String [] args) {

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
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
