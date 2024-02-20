package Clases;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.security.SecureRandom;


public class Empleado extends Usuario{
    Scanner scanner = new Scanner(System.in);
    
    @Override
    public boolean autentificar(String usu, String contra, Trabajador trabajador){
        return usu.equals(trabajador.getNombre()) && contra.equals(trabajador.getDNI());
    }
    
    public void menuPrincipal(Trabajador trabajador){
        int opcion = 0;
        while(opcion != 5){
            System.out.println("=====================");
            System.out.println("BIENVENIDO A EDUPAGO");
            System.out.println("=====================");
            System.out.println("1. Ver planilla");
            System.out.println("2. Ver pagos");
            System.out.println("3. Hacer reclamo");
            System.out.println("4. Ver reclamos");
            System.out.println("5. Salir");
            System.out.println("---------------------");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("---------------------");
            switch(opcion){
                case 1:
                    verPlanilla(trabajador);break;
                case 2:
                    verPagos(trabajador);break;
                case 3:
                    hacerReclamo(trabajador);break;
                case 4:
                    verReclamos(trabajador);break;
                case 5:
                    System.out.println("Nos vemos en otra ocasion");
                    DB.login();break;
                default:
                    System.out.println("Numero invalido");break;
            }
        }
    }
    
    public void verPlanilla(Trabajador trabajador){
        if(trabajador.getRol()[trabajador.getId_rol() - 1].equals("Auxiliar") || 
            trabajador.getTipo()[trabajador.getId_tipo() - 1].equals("Contratado")){
            System.out.println("=========================");
            System.out.println("PLANILLA DEL TRABAJADOR");
            System.out.println("=========================");
            System.out.println("Nombre: " + trabajador.getNombre());
            System.out.println("Apellido: " + trabajador.getApellido());
            System.out.println("DNI: " + trabajador.getDNI());
            System.out.println("Horas trabajadas: " + trabajador.getHorasTrabajadas());
            System.out.println("Grado Academico: " + trabajador.getGradoAcademico()[trabajador.getId_gradoAcademico() - 1]);
            System.out.println("Colegio: " + trabajador.getColegio().getNombre());
            System.out.println("Rol: " + trabajador.getRol()[trabajador.getId_rol() - 1]);
            System.out.println("Tipo: " + trabajador.getTipo()[trabajador.getId_tipo() - 1]);
        }else{
            System.out.println("=========================");
            System.out.println("PLANILLA DEL TRABAJADOR");
            System.out.println("=========================");
            System.out.println("Nombre: " + trabajador.getNombre());
            System.out.println("Apellido: " + trabajador.getApellido());
            System.out.println("DNI: " + trabajador.getDNI());
            System.out.println("Horas trabajadas: " + trabajador.getHorasTrabajadas());
            System.out.println("Grado Academico: " + trabajador.getGradoAcademico()[trabajador.getId_gradoAcademico() - 1]);
            System.out.println("Colegio: " + trabajador.getColegio().getNombre());
            System.out.println("Rol: " + trabajador.getRol()[trabajador.getId_rol() - 1]);
            System.out.println("Tipo: " + trabajador.getTipo()[trabajador.getId_tipo() - 1]);
            System.out.println("Cargo Extra: " + trabajador.getCargoExtra()[trabajador.getId_cargoExtra() - 1]);
            System.out.println("Escala: " + trabajador.getEscala()[trabajador.getId_escala() - 1]);
        }
        System.out.println("---------------------------------");
        System.out.println("Volver al menu principal? (Y)");
        String volver = scanner.nextLine();
    }
    
    public void verPagos(Trabajador trabajador){
        System.out.println("Historial de pagos para " + trabajador.getNombre() + " " + trabajador.getApellido() + ":");
        System.out.println("--------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-15s |\n", "Fecha", "Sueldo bruto", "Sueldo");
        System.out.println("--------------------------------------------------");
        for (Pago pago : trabajador.getPagos()) {
            System.out.printf("| %-10s | %-15s | %-15s |\n",
                pago.getFecha(),
                pago.getSueldoBruto(),
                pago.getSueldo());
            System.out.println("--------------------------------------------------");
        }
        System.out.println("---------------------------------");
        System.out.println("Volver al menu principal? (Y)");
        String volver = scanner.nextLine();
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
    
    public void hacerReclamo(Trabajador trabajador){
        System.out.println("==================");
        System.out.println(" Tipo de reclamo ");
        System.out.println("==================");
        System.out.println("1. Falta de pago");
        System.out.println("2. Dato incorrecto");
        System.out.println("3. Otro");
        System.out.println("------------------");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        System.out.println("------------------");
        System.out.println("Escriba el detalle del reclamo");
        String detalle = scanner.nextLine();
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = formato.format(fechaActual);
        Reclamo reclamo = new Reclamo(3,detalle,opcion,fechaFormateada);
        trabajador.getReclamos().add(reclamo);
        String id = generarID(3);
        reclamo.setId(id);
        System.out.println("EL RECLAMO SE HA ENVIADO SATISFACTORIAMENTE");
        System.out.println("-------------------------------");
        int op = 0;
        while(op < 1 || op > 2){
            System.out.println("1. Enviar otro reclamo");
            System.out.println("2. Regresar al menu principal");
            System.out.println("-------------------------------");
            System.out.println("------------------");
            op = scanner.nextInt();
            scanner.nextLine();
            System.out.println("------------------");
            switch(op){
                case 1:
                    hacerReclamo(trabajador);break;
                case 2:
                    menuPrincipal(trabajador);break;
                case 3:
                    System.out.println("Numero invalido");
            } 
        }
    }
    
    public void verReclamos(Trabajador trabajador){
        System.out.println("Historial de reclamos para " + trabajador.getNombre() + " " + trabajador.getApellido() + ":");
        if(trabajador.getReclamos().isEmpty()){
            System.out.println("No tiene reclamos registrados");
            System.out.println("---------------------------------");
            System.out.println("Volver al menu principal? (Y)");
            String volver = scanner.nextLine();
        }else{
            System.out.println("---------------------------------------------------------------");
            System.out.printf("| %-10s | %-10s | %-15s | %-15s |\n", "ID", "Fecha", "Tipo", "Estado");
            System.out.println("---------------------------------------------------------------");
            for (Reclamo reclamo : trabajador.getReclamos()) {
                System.out.printf("| %-10s | %-10s | %-15s | %-15s |\n",
                    reclamo.getId(),
                    reclamo.getFecha(),
                    reclamo.getTipo()[reclamo.getId_tipo() - 1],
                    reclamo.getEstado()[reclamo.getId_estado() - 1]);
                System.out.println("---------------------------------------------------------------");
            }
            int op = 0;
            while(op < 1 || op> 2){
                System.out.println("-------------------------------");
                System.out.println("1. Ver detalle de un reclamo");
                System.out.println("2. Regresar al menu principal");
                System.out.println("-------------------------------");
                op = scanner.nextInt();
                scanner.nextLine();
                System.out.println("-------------------------------");
                switch(op){
                    case 1:
                        System.out.println("Digite el id del reclamo:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        boolean noEncontrado = true;
                        for(Reclamo reclamo : trabajador.getReclamos()){
                            if(reclamo.getId().equals(id)){
                                noEncontrado = true;
                                System.out.println("=====================");
                                System.out.println("Detalle del reclamo");
                                System.out.println("=====================");
                                System.out.println(reclamo.getDetalle());
                                System.out.println("---------------------------------");
                                System.out.println("Regresar? (Y)");
                                String volver = scanner.nextLine();
                                verReclamos(trabajador);
                            }
                        }
                        if(noEncontrado){
                            System.out.println("No se ha encontrado un reclamo con ese ID");
                        }
                        break;
                    case 2:
                        menuPrincipal(trabajador);break;
                    default:
                        System.out.println("Numero invalido");break;
                }
            }
        }
    }
}
