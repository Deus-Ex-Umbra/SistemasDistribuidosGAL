package retencionjudicial;
import java.util.ArrayList;

public interface IBanco {
    ArrayList<String> buscar(String _ci, String _nombres, String _apellidos);
    Boolean congelar(String _nro_cuenta, Double _monto); 
}
