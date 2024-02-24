package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DB {
    private static List<Trabajador> trabajadores = new ArrayList<>();
    private static List<Colegio> colegios = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public DB() {
    }
    
    public static void inicializacion() {
        // Agregar colegios de ejemplo
        colegios.add(new Colegio("Mercedes", 1, 1, 14, 2));
        colegios.add(new Colegio("Santa Lucia", 3, 1, 20, 3));

        // Agregar trabajadores de ejemplo
        Trabajador trabajador1 = new Trabajador("Juan", "Perez", colegios.get(0), "12345678", 40, 1, 1, 2 , 1, 5,  new ArrayList<>(), new ArrayList<>());
        Trabajador trabajador2 = new Trabajador("Maria", "Gonzalez", colegios.get(1), "87654321", 35, 2, 2, 2, 3, 6, new ArrayList<>(), new ArrayList<>());

        // Agregar pagos de ejemplo para los trabajadores
        Pago pago1 = new Pago();
        pago1.setFecha("17-02-2024");
        pago1.setSueldoBruto(pago1.calcularSueldo(trabajador1, pago1));
        pago1.setSueldo(pago1.getSueldoBruto());
        trabajador1.getPagos().add(pago1);

        Pago pago2 = new Pago();
        pago2.setFecha("15-01-2024");
        pago2.setSueldoBruto(pago2.calcularSueldo(trabajador2, pago2));
        pago2.setSueldo(pago2.getSueldoBruto());
        trabajador2.getPagos().add(pago2);
        
        Reclamo reclamo1 = new Reclamo(3,"No me pagaron el mes pasado",1,"16-02-2024");
        reclamo1.setId("1a1");
        trabajador1.getReclamos().add(reclamo1);
        
        Reclamo reclamo2 = new Reclamo(3,"Trabajo 40 horas",2,"5-01-2024");
        reclamo2.setId("3B1");
        trabajador2.getReclamos().add(reclamo2);
        

        // Agregar los trabajadores a la lista de trabajadores
        trabajadores.add(trabajador1);
        trabajadores.add(trabajador2);
    }
    
    public static void login(){
        Administrador admin = new Administrador();
        boolean noEncontrado = true;
        System.out.println("==============");
        System.out.println("    EDUPAGO");
        System.out.println("==============");
        System.out.println("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();
        if(admin.autentificar(usuario, contraseña, null)){
            admin.menuPrincipal();
            noEncontrado = false;
        }else{
            for(Trabajador trabajador : trabajadores){
                if(trabajador.autentificar(usuario, contraseña, trabajador)){
                    trabajador.menuPrincipal(trabajador);
                    noEncontrado = false;
                }
            }
        }
        if(noEncontrado){
            System.out.println("El usuario o contraseña son incorrectos");
            login();
        }
    }
    
    public static List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public static List<Colegio> getColegios() {
        return colegios;
    }
}
