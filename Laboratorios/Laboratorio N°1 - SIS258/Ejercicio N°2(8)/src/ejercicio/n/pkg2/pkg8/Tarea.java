package ejercicio.n.pkg2.pkg8;

import java.util.Date;

public class Tarea {
    private int id;
    private String descripcion;
    private Date limite;
    private String estado;
    public Tarea(int id, String descripcion, Date fechaLimite, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.limite = fechaLimite;
        this.estado = estado;
    }
    public Tarea(String descripcion, Date fechaLimite, String estado) {
        this(0, descripcion, fechaLimite, estado);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLimite() {
        return limite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.limite = fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tarea [id=" + id + ", descripcion=" + descripcion + ", fechaLimite=" + limite + ", estado=" + estado + "]";
    }
}
