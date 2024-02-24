package Clases;

public class Noticia {
   private String id;
   private String titulo;
   private String tipo;
   private String detalle;
   private String fecha;

    public Noticia(String id, String titulo, String tipo, String detalle, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
