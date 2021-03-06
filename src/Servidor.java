import java.util.*;

public class Servidor {

    private String nombre_registrado;
    private String host_remoto_IP_port;
    private ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();

    public Servidor(String host, String nombre) {
        nombre_registrado = nombre;
        host_remoto_IP_port = host;
    }

    public String getNombre() {
        return nombre_registrado;
    }

    public String getIP() {
		int index = host_remoto_IP_port.indexOf(":");
		if(index != -1){
			return host_remoto_IP_port.substring(0, index);
		}
        else{
			return host_remoto_IP_port;
		}
    }

    public String getPort() {
		int index = host_remoto_IP_port.indexOf(":");
		if(index != -1){
			return host_remoto_IP_port.substring(index+1);
		}
		else{
			return host_remoto_IP_port;
		}
	}

    public ArrayList<Servicio> getServicios() {
        return listaServicios;
    }

    public void addServicio(String nombre, String[] param, String retorno) {
        listaServicios.add(new Servicio(nombre, retorno, param));
    }

    public void removeServicio(String nombre) {
        listaServicios.remove(listaServicios.indexOf(nombre));
    }
}
