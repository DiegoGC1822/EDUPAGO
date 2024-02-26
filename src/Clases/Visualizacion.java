package Clases;

public class Visualizacion {
    private Noticia noticia;
    private Trabajador trabajador;;
    private String estado = "No visto";

    public Visualizacion() {
    }

    public Visualizacion(Noticia noticia) {
        this.noticia = noticia;
    }

    public Visualizacion(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Visualizacion(Noticia noticia, Trabajador trabajador) {
        this.noticia = noticia;
        this.trabajador = trabajador;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
