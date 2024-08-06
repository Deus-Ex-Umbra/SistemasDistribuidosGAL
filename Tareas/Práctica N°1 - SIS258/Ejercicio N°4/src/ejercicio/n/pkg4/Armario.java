package ejercicio.n.pkg4;

import java.util.ArrayList;
import java.util.List;

public class Armario {
    private int ida;
    private String codigo;
    private ArrayList<String> tipo;
    private int id_b;
    public Armario(int _ida, String _codigo, int _id_b) {
        this.ida = _ida;
        this.codigo = _codigo;
        this.id_b = _id_b;
        tipo = new ArrayList<String>(List.of("madera", "metal"));
    }

    @Override
    public String toString() {
        return "Armario{" + "ida=" + ida + ", codigo=" + codigo + ", tipo=" + tipo + ", id_b=" + id_b + '}';
    }
}
