package Clases;


public class Reclamo {
    private int id;
    private int id_estado;
    private static final String[] estado = {"Resuelto","No procede","No revisado"};
    private int id_tipo;
    private static final String[] tipo = {"Falta de pagos","Dato erroneo","Otro"};
    private String detalle;
    private String fecha;

    public Reclamo() {
    }
    
    public Reclamo(int id_estado, String detalle, int id_tipo, String fecha) {
        this.id_estado = id_estado;
        this.detalle = detalle;
        this.id_tipo = id_tipo;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String[] getEstado() {
        return estado;
    }

    public static String[] getTipo() {
        return tipo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
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
