package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DB {
    private static List<Trabajador> trabajadores = new ArrayList<>();
    private static List<Colegio> colegios = new ArrayList<>();
    private static List<Reclamo> reclamos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public DB() {
    }
    
    public static void inicializacion() {
        // Agregar colegios de ejemplo
        colegios.add(new Colegio("Mercedes", 1, 34, 2, 3));
        colegios.add(new Colegio("Santa Lucia", 3, 20, 5, 5));

        // Agregar trabajadores de ejemplo
        Trabajador trabajador1 = new Trabajador("Juan", "Perez", colegios.get(0), "12345678", 40, 1, 1, 1 , 5, 9,  new ArrayList<>(), new ArrayList<>());
        Trabajador trabajador2 = new Trabajador("Maria", "Gonzalez", colegios.get(1), "87654321", 35, 2, 2, 2, 3, 6, new ArrayList<>(), new ArrayList<>());

        // Agregar pagos de ejemplo para los trabajadores
        Pago pago1 = new Pago();
        pago1.setFecha("17-02-2024");
        trabajador1.createPago(pago1, trabajador1, colegios.get(0), 1);

        Pago pago2 = new Pago();
        pago2.setFecha("15-01-2024");
        trabajador2.createPago(pago2, trabajador2, colegios.get(1), 2);

        // Agregar los trabajadores a la lista de trabajadores
        trabajadores.add(trabajador1);
        trabajadores.add(trabajador2);
    }
    
    public static void login(){
        Administrador admin = new Administrador();
        Director director = new Director();
        Empleado empleado = new Empleado();
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
                if(director.autentificar(usuario, contraseña, trabajador)){
                    director.menuPrincipal(trabajador);
                    noEncontrado = false;
                }else if(empleado.autentificar(usuario, contraseña, trabajador)){
                    empleado.menuPrincipal(trabajador);
                    noEncontrado = false;
                }
            }
        }
        if(noEncontrado){
            System.out.println("El usuario o contraseña son incorrectos");
        }
    }
    
    public static List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public static List<Colegio> getColegios() {
        return colegios;
    }

    public static List<Reclamo> getReclamos() {
        return reclamos;
    }
}
