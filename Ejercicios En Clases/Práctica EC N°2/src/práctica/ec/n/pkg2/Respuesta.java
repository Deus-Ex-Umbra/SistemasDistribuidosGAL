package práctica.ec.n.pkg2;
import java.io.*;

public class Respuesta implements Serializable{
    private String estado; //cadena de guiones
    private Integer numero_vidas; //número de vidas que quedan

    public Respuesta(String estado, Integer numero_vidas) {
        this.estado = estado;
        this.numero_vidas = numero_vidas;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumero_vidas() {
        return numero_vidas;
    }

    public void setNumero_vidas(Integer numero_vidas) {
        this.numero_vidas = numero_vidas;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "estado=" + estado + ", numero_vidas=" + numero_vidas + '}';
    }
    
}
