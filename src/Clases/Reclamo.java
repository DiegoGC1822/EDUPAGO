package Clases;
import java.util.Scanner;

public class Reclamo {
    private String id;
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
    
    public static void actualizarEstado(){
        Scanner scanner = new Scanner(System.in);
        boolean noEncontrado = true;
        System.out.println("------------");
        System.out.println("ID: ");
        String id = scanner.nextLine();
        System.out.println("------------");
        for (Trabajador trabajador: DB.getTrabajadores()){
            for(Reclamo reclamo : trabajador.getReclamos()){
                if(reclamo.getId().equals(id)){
                    noEncontrado = false;
                    System.out.println("===============");
                    System.out.println("Actual estado");
                    System.out.println("===============");
                    System.out.println(reclamo.getEstado()[reclamo.getId_estado() - 1]);
                    System.out.println("===============");
                    System.out.println("Nuevo estado");
                    System.out.println("===============");
                    System.out.println("1. Resuelto");
                    System.out.println("2. No procede");
                    System.out.println("-------------------");
                    int estado = scanner.nextInt();
                    scanner.nextLine();
                    reclamo.setId_estado(estado);
                }
            }
        }
        if(noEncontrado){
            System.out.println("No se ha encontrado un reclamo con ese ID");
        }
    }
    
    public static void mostrarDetalle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite el id del reclamo:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        for (Trabajador trabajador: DB.getTrabajadores()){
            for(Reclamo reclamo : trabajador.getReclamos()){
                if(reclamo.getId().equals(id)){
                    noEncontrado = false;
                    System.out.println("=====================");
                    System.out.println("Detalle del reclamo");
                    System.out.println("=====================");
                    System.out.println(reclamo.getDetalle());
                    System.out.println("---------------------------------");
                    System.out.println("Regresar? (Y)");
                    String volver = scanner.nextLine();  
                }
            }
        }
        if(noEncontrado){
            System.out.println("No se ha encontrado un reclamo con ese ID");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
