import java.util.Arrays;

public class Servicio {

	private String nombre;
    private String type_return;
    private String[] params;

    public Servicio(String nombre_, String type_return_, String[] params_) {
        nombre = nombre_;
        type_return = type_return_;
        params = params_;
    }
    
	//Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipoRetorno() {
        return type_return;
    }

    public String[] getListaParam() {
        return params;
    }

    public String toString() {
        String parametros = Arrays.toString(params);
        return type_return + " " + nombre + "(" + parametros + ")";
    }
}
