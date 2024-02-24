package Clases;


public class Capacitacion {
    private String id;
    private String titulo;
    private String duracion;
    private String modalidad;
    private String detalle;
    private String fecha;

    public Capacitacion(String id, String titulo, String duracion, String modalidad, String detalle, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.modalidad = modalidad;
        this.detalle = detalle;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
