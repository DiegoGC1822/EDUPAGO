package Clases;

import java.util.Scanner;

public class Inscripcion {
    private Capacitacion capacitacion;
    private Trabajador trabajador;

    public Inscripcion(Capacitacion capacitacion, Trabajador trabajador) {
        this.capacitacion = capacitacion;
        this.trabajador = trabajador;
    }

    public static void inscribirse(Trabajador trabajador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        boolean YaInscrito = false;
        for(Capacitacion capacitacion : DB.getCapacitaciones()){
            if(capacitacion.getId().equals(id)){
                noEncontrado = false;
                for(Inscripcion inscripcion : DB.getInscripciones()){
                    if(inscripcion.getCapacitacion().getId().equals(id) && 
                        inscripcion.getTrabajador().getDNI().equals(trabajador.getDNI())){
                        YaInscrito = true;
                    }
                }
                if(!YaInscrito){
                    Inscripcion inscripcion = new Inscripcion(capacitacion,trabajador);
                    capacitacion.setVacantes(capacitacion.getVacantes() - 1);
                    DB.getInscripciones().add(inscripcion);
                }
            }
        }
        
        if(noEncontrado){
            System.out.println("No se encontro una capacitacion con ese ID");
        }else if(YaInscrito){
            System.out.println("Ya esta inscrito en esta capacitacion");
        }else{
            System.out.println("===========================================");
            System.out.println("Se inscribio a la capacitacion exitosamente");
            System.out.println("===========================================");
        }
    }
    
    public static void mostrarTrabajadoresInscritos(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        int flag = 0;
        for(Inscripcion inscripcion : DB.getInscripciones()){
            if(inscripcion.getCapacitacion().getId().equals(id)){
                flag++;
                System.out.println("----------------------------------------------");
                System.out.println(flag + ". " + inscripcion.getTrabajador().getNombre() + " " + inscripcion.getTrabajador().getApellido());
            }
        }
        if(flag == 0){
            System.out.println("No hay trabajadores inscritos en la capacitacion");
        }
    }

    public Capacitacion getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(Capacitacion capacitacion) {
        this.capacitacion = capacitacion;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
    
    
}
