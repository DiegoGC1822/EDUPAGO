package Clases;

import java.security.SecureRandom;
import java.util.Scanner;


public class Capacitacion {
    private String id;
    private String titulo;
    private String dias;
    private int horasAlDia;
    private int id_modalidad;
    private static final String[] modalidad = {"Virtual","Precencial"};
    private String detalle;
    private int vacantes;
    private String fechaInicio;

    public Capacitacion() {
    }

    public Capacitacion(String id, String titulo, String dias,int horasAlDia, int id_modalidad, String detalle, int vacantes, String fechaInicio) {
        this.id = id;
        this.titulo = titulo;
        this.dias = dias;
        this.horasAlDia = horasAlDia;
        this.id_modalidad = id_modalidad;
        this.detalle = detalle;
        this.vacantes = vacantes;
        this.fechaInicio = fechaInicio;
    }
    
    public static String generarID(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(indice));
        }

        return sb.toString();
    }
    
    public static void create(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Titulo:");
        String titulo = scanner.nextLine();
        System.out.println("Dias:");
        String dias = scanner.nextLine();
        System.out.println("Horas al dia:");
        int horas = scanner.nextInt();
        scanner.nextLine();
        int op = 0;
        while(op < 1 || op > 2){
            System.out.println("1. Virtual");
            System.out.println("2. Precencial");
            op = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("Detalle:");
        String detalle = scanner.nextLine();
        System.out.println("Vacantes:");
        int vacantes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Fecha de inicio:");
        String fechaInicio = scanner.nextLine();
        String id = generarID(3);
        Capacitacion capacitacion = new Capacitacion(id,titulo,dias,horas,op,detalle,vacantes,fechaInicio);
        DB.getCapacitaciones().add(capacitacion);
        System.out.println("=============================================");
        System.out.println("La capacitacion ha sido creada exitosamente");
        System.out.println("=============================================");
    }
    
    public static void read(){
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-50s | %-15s |\n", "ID", "Titulo","Fecha de inicio");
        System.out.println("-------------------------------------------------------------------------------------");
        for(Capacitacion capacitacion : DB.getCapacitaciones()){
            System.out.printf("| %-10s | %-50s | %-15s |\n",
                capacitacion.getId(),
                capacitacion.getTitulo(),
                capacitacion.getFechaInicio());
                System.out.println("-------------------------------------------------------------------------------------");
        }
    }
    
    public static void verDetalles(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        for(Capacitacion capacitacion : DB.getCapacitaciones()){
            if(capacitacion.getId().equals(id)){
                System.out.println("=============================");
                System.out.println("Detalles de la capacitacion: ");
                System.out.println("=============================");
                System.out.println("Dias:");
                System.out.println(capacitacion.getDias());
                System.out.println("Horas al dia:");
                System.out.println(capacitacion.getHorasAlDia());
                System.out.println("Modalidad:");
                System.out.println(capacitacion.getModalidad()[capacitacion.getId_modalidad() - 1]);
                System.out.println("Vacantes:");
                System.out.println(capacitacion.getVacantes());
                System.out.println("Detalle:");
                System.out.println(capacitacion.getDetalle());
                noEncontrado = false;
            }
        }
        if(noEncontrado){
            System.out.println("No se ha encontrado al informe");
        }
    }
    
    public static void delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        Capacitacion capacitacionElegido = new Capacitacion();
        for(Capacitacion capacitacion : DB.getCapacitaciones()){
            if(capacitacion.getId().equals(id)){
                capacitacionElegido = capacitacion;
                noEncontrado = false;
            }
        }
        if(noEncontrado){
            System.out.println("No se ha encontrado la capacitacion");
        }else{
            DB.getCapacitaciones().remove(capacitacionElegido);
            System.out.println("=============================================");
            System.out.println("La capacitacion ha sido borrada exitosamente");
            System.out.println("=============================================");
        }
    }

    public int getId_modalidad() {
        return id_modalidad;
    }

    public void setId_modalidad(int id_modalidad) {
        this.id_modalidad = id_modalidad;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public static String[] getModalidad() {
        return modalidad;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public int getHorasAlDia() {
        return horasAlDia;
    }

    public void setHorasAlDia(int horasAlDia) {
        this.horasAlDia = horasAlDia;
    }

}
