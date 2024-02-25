package Clases;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Trabajador{
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
    private List<Pago> pagos = new ArrayList<>();
    private List<Reclamo> reclamos = new ArrayList<>();
    private List<Informe> informes = new ArrayList<>();

    
    Scanner scanner = new Scanner(System.in);
    
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
    
    public boolean autentificar(String usu, String contra, Trabajador trabajador){
        return usu.equals(trabajador.getNombre()) && contra.equals(trabajador.getDNI());
    }
    
    public static void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.println("DNI: ");
        String DNI = scanner.nextLine();
        System.out.println("Horas trabajadas: ");
        int ht = scanner.nextInt();
        scanner.nextLine();
        System.out.println("====================");
        System.out.println("Colegio de trabajo:");
        System.out.println("====================");
        int id_colegio = 1;
        for(Colegio colegio : DB.getColegios()){
            System.out.println(id_colegio + ". " + colegio.getNombre());
            id_colegio++;
        }
        System.out.println("Colegio elegido: ");
        id_colegio = scanner.nextInt();
        scanner.nextLine();
        int actualTrabajadores = DB.getColegios().get(id_colegio - 1).getNumTrabajadores();
        int vacantes = DB.getColegios().get(id_colegio - 1).getVacantes();
        DB.getColegios().get(id_colegio - 1).setNumTrabajadores(actualTrabajadores + 1);
        DB.getColegios().get(id_colegio - 1).setVacantes(vacantes - 1);
        Colegio colegioElegido = DB.getColegios().get(id_colegio - 1);
        int id_gradoAca = 0;
        while(id_gradoAca < 1 || id_gradoAca > 3){
            System.out.println("====================");
            System.out.println("   Grado academico:");
            System.out.println("====================");
            System.out.println("1.Licenciatura");
            System.out.println("2.Maestria");
            System.out.println("3.Doctorado");
            id_gradoAca = scanner.nextInt();
            scanner.nextLine();
        }
        int id_rol = 0;
        while(id_rol < 1 || id_rol > 2){
            System.out.println("====================");
            System.out.println("        Rol:");
            System.out.println("====================");
            System.out.println("1.Profesor");
            System.out.println("2.Auxiliar");
            id_rol = scanner.nextInt();
            scanner.nextLine();
        }
        int id_tipo = 0;
        while(id_tipo < 1 || id_tipo > 2){
            System.out.println("====================");
            System.out.println("        Tipo:");
            System.out.println("====================");
            System.out.println("1.Contratado");
            System.out.println("2.Nombrado");
            id_tipo = scanner.nextInt();
            scanner.nextLine();
        }
        if(id_rol == 2 || id_tipo == 1){
            Trabajador t = new Trabajador(nombre, apellido, colegioElegido,DNI, ht, 
                    id_gradoAca, id_rol, id_tipo, 4, 9,new ArrayList<>(), new ArrayList<>());
            DB.getTrabajadores().add(t);
        }else{
            int id_cargoExtra = 0;
            while(id_cargoExtra < 1 || id_cargoExtra > 4){
                System.out.println("====================");
                System.out.println("    Cargo extra:");
                System.out.println("====================");
                System.out.println("1. Director");
                System.out.println("2. Subdirector");
                System.out.println("3. Jerarquico");
                System.out.println("4. Ninguno");
                id_cargoExtra = scanner.nextInt();
                scanner.nextLine(); 
            }
            int id_escala = 0;
            while(id_escala < 1 || id_escala > 8){
                System.out.println("====================");
                System.out.println("Escala: ");
                System.out.println("====================");
                id_escala = scanner.nextInt();
                scanner.nextLine();
            }
            Trabajador t = new Trabajador(nombre, apellido, colegioElegido,DNI, ht, 
                    id_gradoAca, id_rol, id_tipo,id_cargoExtra, id_escala,new ArrayList<>(), new ArrayList<>());
            DB.getTrabajadores().add(t);
        }
    }
    
    public static void read(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("DNI:");
        String DNI = scanner.nextLine();
        System.out.println("---------------");
        boolean noEncontrado = true;
        for(Trabajador trabajador: DB.getTrabajadores()){
            if(trabajador.getDNI().equals(DNI)){
                noEncontrado = false;
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
                    System.out.println("Localizacion de Colegio: " + trabajador.getColegio().getLocalizacionNombre()[trabajador.getColegio().getId_localizacion() - 1]);
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
                    System.out.println("Localizacion de Colegio: " + trabajador.getColegio().getLocalizacionNombre()[trabajador.getColegio().getId_localizacion() - 1]);
                    System.out.println("Rol: " + trabajador.getRol()[trabajador.getId_rol() - 1]);
                    System.out.println("Tipo: " + trabajador.getTipo()[trabajador.getId_tipo() - 1]);
                    System.out.println("Cargo Extra: " + trabajador.getCargoExtra()[trabajador.getId_cargoExtra() - 1]);
                    System.out.println("Escala: " + trabajador.getId_escala());
                }
            }
        }
        
        if(noEncontrado){
            System.out.println("No se encontro al trabajador");
        }
        System.out.println("---------------------------------");
        System.out.println("Volver a la gestion de trabajadores? (Y)");
        String volver = scanner.nextLine();
    }
    
    public static void update(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------");
        System.out.println("DNI: ");
        String DNI = scanner.nextLine();
        System.out.println("-------------");
        boolean noEncontrado = true;
        int opcion = 0;
        for(Trabajador trabajador: DB.getTrabajadores()){
            if(trabajador.getDNI().equals(DNI)){
                noEncontrado = false;
                if(trabajador.getRol()[trabajador.getId_rol() - 1].equals("Auxiliar") || 
                        trabajador.getTipo()[trabajador.getId_tipo() - 1].equals("Contratado")){
                    while(opcion < 1 || opcion > 5){
                        System.out.println("====================");
                        System.out.println("Atributo a cambiar: ");
                        System.out.println("====================");
                        System.out.println("1. Horas trabajadas");
                        System.out.println("2. Grado academico");
                        System.out.println("3. Colegio");
                        System.out.println("4. Rol");
                        System.out.println("5. Tipo");
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                    } 
                }else{
                    while(opcion < 1 || opcion > 7){
                       System.out.println("====================");
                        System.out.println("Atributo a cambiar: ");
                        System.out.println("====================");
                        System.out.println("1. Horas trabajadas");
                        System.out.println("2. Grado academico");
                        System.out.println("3. Colegio");
                        System.out.println("4. Rol");
                        System.out.println("5. Tipo"); 
                        System.out.println("6. Cargo Extra");
                        System.out.println("7. Escala");
                        opcion = scanner.nextInt();
                        scanner.nextLine(); 
                    }
                }
                int op = 0;
                switch(opcion){
                        case 1:
                            System.out.println("Nuevo valor: ");
                            int nv = scanner.nextInt();
                            trabajador.setHorasTrabajadas(nv);
                            break;
                        case 2:
                            while(op < 1 || op > 3){
                                System.out.println("====================");
                                System.out.println("   Grado academico:");
                                System.out.println("====================");
                                System.out.println("1.Licenciatura");
                                System.out.println("2.Maestria");
                                System.out.println("3.Doctorado");
                                op = scanner.nextInt();
                                scanner.nextLine();
                            }
                            trabajador.setId_gradoAcademico(op);
                            break;
                        case 3:
                            int actualTrabajadores = trabajador.getColegio().getNumTrabajadores();
                            int vacantes = trabajador.getColegio().getVacantes();
                            trabajador.getColegio().setNumTrabajadores(actualTrabajadores - 1);
                            trabajador.getColegio().setVacantes(vacantes + 1);
                            System.out.println("====================");
                            System.out.println("Colegio de trabajo:");
                            System.out.println("====================");
                            int id_colegio = 1;
                            for(Colegio colegio : DB.getColegios()){
                                System.out.println(id_colegio + ". " + colegio.getNombre());
                                id_colegio++;
                            }
                            System.out.println("====================");
                            System.out.println("  Colegio elegido: ");
                            System.out.println("====================");
                            id_colegio = scanner.nextInt();
                            scanner.nextLine();
                            trabajador.setColegio(DB.getColegios().get(id_colegio - 1));
                            actualTrabajadores = trabajador.getColegio().getNumTrabajadores();
                            vacantes = trabajador.getColegio().getVacantes();
                            trabajador.getColegio().setNumTrabajadores(actualTrabajadores + 1);
                            trabajador.getColegio().setVacantes(vacantes - 1);
                            break;
                        case 4:
                            while(op < 1 || op > 2){
                                System.out.println("====================");
                                System.out.println("        Rol:");
                                System.out.println("====================");
                                System.out.println("1. Profesor");
                                System.out.println("2. Auxiliar");
                                op = scanner.nextInt();
                                scanner.nextLine();
                                trabajador.setId_rol(op);
                            }
                            break;
                        case 5:
                            while(op < 1 || op > 2){
                                System.out.println("====================");
                                System.out.println("       Tipo:");
                                System.out.println("====================");
                                System.out.println("1. Contratado");
                                System.out.println("2. Nombrado");
                                op = scanner.nextInt();
                                scanner.nextLine();
                            }
                            trabajador.setId_tipo(op);
                            if(op == 1){
                                trabajador.setId_cargoExtra(4);
                                trabajador.setId_escala(9);
                            }else if(op == 2){
                                int cargoExtra = 0;
                                while(cargoExtra < 1 || cargoExtra > 4){
                                    System.out.println("====================");
                                    System.out.println("      Cargo extra: ");
                                    System.out.println("====================");
                                    System.out.println("1. Director");
                                    System.out.println("2. Subdirector");
                                    System.out.println("3. Jerarquico");
                                    System.out.println("4. Ninguno");
                                    cargoExtra = scanner.nextInt();
                                    scanner.nextLine();
                                }
                                trabajador.setId_cargoExtra(cargoExtra);

                                int escala = 0;
                                while(escala < 1 || escala > 8){
                                    System.out.println("====================");
                                    System.out.println("    Nueva escala: ");
                                    System.out.println("====================");
                                    escala = scanner.nextInt();
                                    scanner.nextLine();
                                }
                                trabajador.setId_escala(escala);
                            }
                            break;
                        case 6:
                            while(op < 1 || op > 4){
                                System.out.println("====================");
                                System.out.println("      Cargo extra: ");
                                System.out.println("====================");
                                System.out.println("1. Director");
                                System.out.println("2. Subdirector");
                                System.out.println("3. Jerarquico");
                                System.out.println("4. Ninguno");
                                op = scanner.nextInt();
                                scanner.nextLine();
                            }
                            trabajador.setId_cargoExtra(op);
                            break;
                        case 7:
                            while(op < 1 || op > 8){
                                System.out.println("====================");
                                System.out.println("    Nueva escala: ");
                                System.out.println("====================");
                                op = scanner.nextInt();
                                scanner.nextLine();
                            }
                            trabajador.setId_escala(op);
                            break;
                    }
            }
        }
        
        if(noEncontrado){
            System.out.println("No se encontro al trabajador");
        }
    }
    
    public static void delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------");
        System.out.println("DNI: ");
        String DNI = scanner.nextLine();
        System.out.println("-------------");
        boolean noEncontrado = true;
        Trabajador trabajadorElegido = new Trabajador();
        for(Trabajador trabajador: DB.getTrabajadores()){
            if(trabajador.getDNI().equals(DNI)){
                trabajadorElegido = trabajador;
                noEncontrado = false;
            }
        }
        if(noEncontrado){
            System.out.println("No se encontro al trabajador");
        }else{
            DB.getTrabajadores().remove(trabajadorElegido);
        }
    }
    
    public void menuPrincipal(Trabajador trabajador){
        int opcion = 0;
        if(trabajador.getId_cargoExtra() != 1){
           while(opcion != 5){
                System.out.println("=====================");
                System.out.println("BIENVENIDO A EDUPAGO");
                System.out.println("=====================");
                System.out.println("1. Ver planilla");
                System.out.println("2. Ver pagos");
                System.out.println("3. Hacer reclamo");
                System.out.println("4. Ver reclamos");
                System.out.println("5. Ver capacitaciones");
                System.out.println("6. Salir");
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
                        verCapacitaciones(trabajador);break;
                    case 6:
                        System.out.println("Nos vemos en otra ocasion");
                        DB.login();break;
                    default:
                        System.out.println("Numero invalido");
                }
           } 
        }else{
            while(opcion != 7){
                System.out.println("=====================");
                System.out.println("BIENVENIDO A EDUPAGO");
                System.out.println("=====================");
                System.out.println("1. Ver planilla");
                System.out.println("2. Ver pagos");
                System.out.println("3. Hacer reclamo");
                System.out.println("4. Ver reclamos");
                System.out.println("5. Hacer informe");
                System.out.println("6. Ver informes");
                System.out.println("7. Ver capacitaciones");
                System.out.println("8. Salir");
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
                        hacerInforme(trabajador);break;
                    case 6:
                        verInformes(trabajador);break;
                    case 7:
                        verCapacitaciones(trabajador);break;
                    case 8:
                        System.out.println("Nos vemos en otra ocasion");
                        DB.login();break;
                    default:
                        System.out.println("Numero invalido");
                }
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
    
    public String generarID(int longitud) {
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
                        String id = scanner.nextLine();
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
    
    public void hacerInforme(Trabajador trabajador){
        System.out.println("Titulo: ");
        Scanner scanner = new Scanner(System.in);
        String titulo = scanner.nextLine();
        System.out.println("Detalle: ");
        String detalle = scanner.nextLine();
        int op = 0;
        while(op < 1 || op > 3){
            System.out.println("==============");
            System.out.println("     Tipo:");
            System.out.println("==============");
            System.out.println("1. Solicitud de recursos");
            System.out.println("2. Solicitud de capacitacion");
            System.out.println("3. Otro");
            op = scanner.nextInt();
            scanner.nextLine();
        }
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = formato.format(fechaActual);
        String id = generarID(3);
        Informe informe = new Informe(id,titulo,op,detalle,fechaFormateada);
        trabajador.getInformes().add(informe);
        System.out.println("====================================");
        System.out.println("Se mando el informe exitosamente");
        System.out.println("====================================");
        System.out.println("---------------------------");
        System.out.println("Volver al menu principal?");
        String volver = scanner.nextLine();
    }
    
    public void verInformes(Trabajador trabajador){
       System.out.println("Historial de informes de " + trabajador.getNombre() + " " + trabajador.getApellido() + ":");
        if(trabajador.getInformes().isEmpty()){
            System.out.println("No tiene informes registrados");
            System.out.println("---------------------------------");
            System.out.println("Volver al menu principal? (Y)");
            String volver = scanner.nextLine();
            menuPrincipal(trabajador);
        }else{
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-15s | %-25s | %-15s |\n", "ID", "Titulo","Fecha", "Tipo", "Estado");
            System.out.println("-----------------------------------------------------------------------------------------------------"); 
            for(Informe informe : trabajador.getInformes()){
                System.out.printf("| %-10s | %-20s | %-15s | %-25s | %-15s |\n",
                informe.getId(),
                informe.getTitulo(),
                informe.getFecha(),
                informe.getTipo()[informe.getId_tipo() - 1],
                informe.getEstado()[informe.getId_estado() - 1]);
                System.out.println("-----------------------------------------------------------------------------------------------------"); 
            }
            int op = 0;
            String volver;
            while(op < 1 || op > 3){
                System.out.println("-------------------------------");
                System.out.println("1. Ver detalle de un informe");
                System.out.println("2. Eliminar un informe");
                System.out.println("3. Regresar al menu principal");
                System.out.println("-------------------------------");
                op = scanner.nextInt();
                scanner.nextLine();
                switch(op){
                    case 1:
                        Informe.verDetalle();
                        System.out.println("---------------------------");
                        System.out.println("Volver al menu? (Y)");
                        volver = scanner.nextLine();
                        verInformes(trabajador);break;
                    case 2:
                        Informe.delete(trabajador);
                        System.out.println("====================================");
                        System.out.println("Se elimino el informe exitosamente");
                        System.out.println("====================================");
                        System.out.println("---------------------------");
                        System.out.println("Volver al menu? (Y)");
                        volver = scanner.nextLine();
                        verInformes(trabajador);
                        break;
                    case 3:
                        menuPrincipal(trabajador);break;
                }
            }
        }   
    }
    
    public void verCapacitaciones(Trabajador trabajador){
        int op = 0;
        String volver;
        while(op != 4){
            Capacitacion.read();
            System.out.println("-----------------------------");
            System.out.println("1. Ver detalles");
            System.out.println("2. Inscribirse");
            System.out.println("3. Ver inscripciones");
            System.out.println("4. Regresar al menu principal");
            System.out.println("-----------------------------");
            op = scanner.nextInt();
            scanner.nextLine();
            switch(op){
                case 1:
                    Capacitacion.verDetalles();
                    System.out.println("---------------------");
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();break;
                case 2:
                    Inscripcion.inscribirse(trabajador);
                    System.out.println("---------------------");
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();break;
                case 3:
                    verInscripciones(trabajador);
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();break;
                case 4:
                    menuPrincipal(trabajador);break;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
    
    public void verInscripciones(Trabajador trabajador){
        int flag = 0;
        for(Inscripcion inscripcion : DB.getInscripciones()){
            if(inscripcion.getTrabajador().getDNI().equals(trabajador.getDNI())){
                flag++;
                System.out.println("-------------------------------------------------------------------");
                System.out.println(flag + ". " + inscripcion.getCapacitacion().getTitulo());
            }
        }
        if(flag == 0){
            System.out.println("No se ha inscrito a ninguna capacitacion");
        }
    }

    public List<Informe> getInformes() {
        return informes;
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
