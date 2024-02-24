package Clases;

import java.util.Scanner;


public class Informe {
    private String id;
    private String titulo;
    private int id_tipo;
    private static final String[] tipo = {"Solicitud de recursos","Solicitud de capacitacion","Otro"};
    private int id_estado = 3;
    private static final String[] estado = {"Procede","No procede","No visto"};
    private String detalle;
    private String fecha;

    public Informe() {
    }

    public Informe(String id, String titulo, int id_tipo, String detalle, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.id_tipo = id_tipo;
        this.detalle = detalle;
        this.fecha = fecha;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }
    
    public static void verDetalle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        for(Trabajador trabajador : DB.getTrabajadores()){
            if(trabajador.getId_cargoExtra() == 1){
               for(Informe informe : trabajador.getInformes()){
                    if(informe.getId().equals(id)){
                        noEncontrado = false;
                        System.out.println("==============");
                        System.out.println("   Detalle:");
                        System.out.println("==============");
                        System.out.println(informe.getDetalle());
                        System.out.println("--------------------------------");
                        System.out.println("Informe proveniente del colegio:");
                        System.out.println("--------------------------------");
                        System.out.println(trabajador.getColegio().getNombre());
                    }
                }
            }
        }
        if(noEncontrado){
            System.out.println("No se encontro el informe");
        }
    }
    
    public static void actualizarEstado(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        for(Trabajador trabajador : DB.getTrabajadores()){
            if(trabajador.getId_cargoExtra() == 1){
               for(Informe informe : trabajador.getInformes()){
                    if(informe.getId().equals(id)){
                        noEncontrado = false;
                        int opcion = 0;
                        while(opcion < 1 || opcion > 3){
                            System.out.println("--------------");
                            System.out.println("1. Procede");
                            System.out.println("2. No procede");
                            System.out.println("---------------");
                            System.out.println("3. Cancelar");
                            System.out.println("---------------"); 
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                        }
                        informe.setId_estado(opcion);
                    }
                } 
            }
        }
        if(noEncontrado){
            System.out.println("ID no encontrado");
        }
    }
    
    public static void delete(Trabajador trabajador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        Informe informeEncontrado = new Informe();
        boolean noEncontrado = true;
        for(Informe informe : trabajador.getInformes()){
            if(informe.getId().equals(id)){
                informeEncontrado = informe;
                noEncontrado = false;
            }
        }
        if(noEncontrado){
            System.out.println("No se encontro el informe");
        }else{
            trabajador.getInformes().remove(informeEncontrado);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public static String[] getTipo() {
        return tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    public static String[] getEstado() {
        return estado;
    }
    
}
