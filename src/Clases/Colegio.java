package Clases;


public class Colegio {
    private String nombre;
    private int id_localizacion;
    private static final String[] localizacionNombre = {"Rural 1","Rural 2","Rural 3","Frontera","VRAEM"};
    private static final double[] localizacion = {500,100,70,100,300};
    private int numTrabajadores;
    private int vacantes;
    private int id_tipo;
    private static final String[] tipo = {"Unidocente","Multigrado","Bilingue","Bilingue acreditado"};

    public Colegio(String nombre, int id_localizacion, int numTrabajadores, int vacantes, int id_tipo) {
        this.nombre = nombre;
        this.id_localizacion = id_localizacion;
        this.numTrabajadores = numTrabajadores;
        this.vacantes = vacantes;
        this.id_tipo = id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String[] getLocalizacionNombre() {
        return localizacionNombre;
    }

    public static double[] getLocalizacion() {
        return localizacion;
    }

    public static String[] getTipo() {
        return tipo;
    }

    public int getNumTrabajadores() {
        return numTrabajadores;
    }

    public void setNumTrabajadores(int numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public int getId_localizacion() {
        return id_localizacion;
    }

    public void setId_localizacion(int id_localizacion) {
        this.id_localizacion = id_localizacion;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    
    
}
