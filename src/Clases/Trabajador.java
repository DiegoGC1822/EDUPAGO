package Clases;

import java.util.List;

public class Trabajador {
    private String nombre;
    private String apellido;
    private Colegio colegio;
    private String DNI;
    private int horasTrabajadas;
    private int id_gradoAcademico;
    private static final  String[] gradoAcademico = {"Licenciatura","Maestria","Doctorado"};
    private int id_rol;
    private static final String[] rol = {"Profesor","Auxiliar"};
    private int id_tipo;
    private static final String[] tipo = {"Contratado","Nombrado"};
    private int id_cargoExtra;
    private static final String[] cargoExtra = {"Director", "Subdirector", "Jerarquico", "Ninguno"};
    private int id_escala;
    private static final double[] escala={3100.50, 3410.55, 3720.60, 4030.65, 4650.75, 5425.88, 5890.95, 6511.05,0};
    private List<Pago> pagos;
    private List<Reclamo> reclamos;

    public Trabajador() {
    }

    public Trabajador(String nombre, String apellido, Colegio colegio, String DNI, int horasTrabajadas, int id_gradoAcademico, int id_rol, int id_tipo, int id_cargoExtra, int id_escala, List<Pago> pagos, List<Reclamo> reclamos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.colegio = colegio;
        this.DNI = DNI;
        this.horasTrabajadas = horasTrabajadas;
        this.id_gradoAcademico = id_gradoAcademico;
        this.id_rol = id_rol;
        this.id_tipo = id_tipo;
        this.id_cargoExtra = id_cargoExtra;
        this.id_escala = id_escala;
        this.pagos = pagos;
        this.reclamos = reclamos;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public static String[] getRol() {
        return rol;
    }
    
    public void createPago(Pago pago, Trabajador trabajador, Colegio colegio, int id_localizacion){
        pago.setSueldoBruto(pago.calcularSueldo(trabajador.getEscala()[id_escala - 1],pago.getCargoExtraPago(id_cargoExtra - 1),
                trabajador.getHorasTrabajadas(),colegio.getLocalizacion()[id_localizacion - 1]));
        pago.setSueldo(pago.getSueldoBruto());
        trabajador.getPagos().add(pago);
    }

    public static String[] getGradoAcademico() {
        return gradoAcademico;
    }

    public static String[] getCargoExtra() {
        return cargoExtra;
    }

    public static double[] getEscala() {
        return escala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Colegio getColegio() {
        return colegio;
    }

    public void setColegio(Colegio colegio) {
        this.colegio = colegio;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<Reclamo> reclamos) {
        this.reclamos = reclamos;
    }

    public int getId_gradoAcademico() {
        return id_gradoAcademico;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public static String[] getTipo() {
        return tipo;
    }

    public int getId_cargoExtra() {
        return id_cargoExtra;
    }

    public int getId_escala() {
        return id_escala;
    }

    public void setId_gradoAcademico(int id_gradoAcademico) {
        this.id_gradoAcademico = id_gradoAcademico;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setId_cargoExtra(int id_cargoExtra) {
        this.id_cargoExtra = id_cargoExtra;
    }

    public void setId_escala(int id_escala) {
        this.id_escala = id_escala;
    }
    
    
}
