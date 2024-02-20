package Clases;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Usuario{
    
    @Override
    public boolean autentificar(String usu, String contra, Trabajador trabajador){
        return usu.equals("diego") && contra.equals("123");
    }
    
    public Administrador() {
    }

    public Administrador(String nombre_usuario, String contraseña) {
        super(nombre_usuario, contraseña);
    }
    
    Scanner scanner = new Scanner(System.in);
    
    public void menuPrincipal(){
        int opcion = 0;
        while(opcion != 4){
            System.out.println("=====================");
            System.out.println("BIENVENIDO A EDUPAGO");
            System.out.println("=====================");
            System.out.println("1. Gestionar trabajadores y sus pagos");
            System.out.println("2. Gestionar reclamos");
            System.out.println("3. Gestionar informes");
            System.out.println("4. Salir");
            System.out.println("---------------------");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("---------------------");
            switch(opcion){
                case 1:
                    gestionarTrabajadores();break;
                case 2:
                    gestionarReclamos();break;
                case 3:
                    gestionarInformes();break;
                case 4:
                    System.out.println("Nos vemos en otra ocasion");
                    DB.login();break;
                default:
                    System.out.println("Numero invalido");break;
            }
        }
    }
    
    public void agregarTrabajadores() {
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
    
    public void verDatosCompletos(){
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
            }
        }
        
        if(noEncontrado){
            System.out.println("No se encontro al trabajador");
            gestionarTrabajadores();
        }
        System.out.println("---------------------------------");
        System.out.println("Volver a la gestion de trabajadores? (Y)");
        String volver = scanner.nextLine();
    }
    
    public void actualizarTrabajador(){
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
            gestionarTrabajadores();
        }
    }
    
    public void eliminarTrabajador(){
        System.out.println("-------------");
        System.out.println("DNI: ");
        String DNI = scanner.nextLine();
        System.out.println("-------------");
        boolean noEncontrado = true;
        for(Trabajador trabajador: DB.getTrabajadores()){
            if(trabajador.getDNI().equals(DNI)){
                DB.getTrabajadores().remove(trabajador);
                noEncontrado = false;
            }
        }
        if(noEncontrado){
            System.out.println("No se encontro al trabajador");
            gestionarTrabajadores();
        }
    }
    
    public void verDatosTrabajadores() {
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-15s | %-17s | %-15s | %-10s | %-10s |\n", "Nombre", "Apellido", "DNI", "Horas trabajadas", "Colegio", "Rol","Tipo");
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        for (Trabajador trabajador : DB.getTrabajadores()) {
            System.out.printf("| %-10s | %-10s | %-15s | %-17s | %-15s | %-10s | %-10s |\n",
                    trabajador.getNombre(),
                    trabajador.getApellido(),
                    trabajador.getDNI(),
                    trabajador.getHorasTrabajadas(),
                    trabajador.getColegio().getNombre(),
                    trabajador.getRol()[trabajador.getId_rol() - 1],
                    trabajador.getTipo()[trabajador.getId_tipo() - 1]);
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------");
    }
    
    public void agregarPago(Trabajador trabajador){
        System.out.println("Fecha: ");
        String fecha = scanner.nextLine();
        Pago pago = new Pago();
        pago.setFecha(fecha);
        trabajador.createPago(pago, trabajador, trabajador.getColegio(),trabajador.getColegio().getId_localizacion());
    }
    
    public void sumarBonificaciones(Trabajador trabajador){
        System.out.println("Fecha del pago:");
        System.out.println("--------------------");
        String fecha = scanner.nextLine();
        for(Pago pago : trabajador.getPagos()){
            if(pago.getFecha().equals(fecha)){
               int seguir = 0;
                while(seguir != 2){
                    System.out.println("===================");
                    System.out.println("     Bonificacion:");
                    System.out.println("===================");
                    System.out.println("1. Desempeño");
                    System.out.println("2. Perfecta asistencia");
                    System.out.println("3. Actividad extracurricular");
                    System.out.println("--------------------");
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); 
                    pago.getBonificaciones().add(opcion);
                    System.out.println("Seguir? (1(si)/2(no))");
                    System.out.println("--------------------");
                    seguir = scanner.nextInt();
                    scanner.nextLine();
                }
                pago.setSueldo(pago.getSueldo() + pago.BonificacionTotal());
            }
        }
    }
    
    public void restarDescuentos(Trabajador trabajador){
        System.out.println("Fecha del pago:");
        System.out.println("--------------------");
        String fecha = scanner.nextLine();
        for(Pago pago : trabajador.getPagos()){
            if(pago.getFecha().equals(fecha)){
               int seguir = 0;
                while(seguir != 2){
                    System.out.println("===================");
                    System.out.println("     Descuento:");
                    System.out.println("===================");
                    System.out.println("1. Falta");
                    System.out.println("2. Tardanza");
                    System.out.println("3. Seguro medico");
                    System.out.println("--------------------");
                    int opcion = scanner.nextInt();
                    scanner.nextLine(); 
                    if(opcion == 1){
                        System.out.println("Numero de dias en falta: ");
                        int dias = scanner.nextInt();
                        scanner.nextLine();
                        pago.setDiasFalta(dias);
                    }else if(opcion == 2){
                        System.out.println("Numero de dias en tardanza: ");
                        int dias = scanner.nextInt();
                        pago.setDiasTardanza(dias);
                    }
                    pago.getDescuentos().add(opcion);
                    System.out.println("Seguir? (1(si)/2(no))");
                    System.out.println("--------------------");
                    seguir = scanner.nextInt();
                    scanner.nextLine();
                }
                pago.setSueldo(pago.getSueldo() - pago.DescuentoTotal());
            }
        }
    }
    
    public void verBonificacionesYDescuentos(Trabajador trabajador){
        System.out.println("Fecha del pago:");
        System.out.println("--------------------");
        String fecha = scanner.nextLine();
        for(Pago pago : trabajador.getPagos()){
           if(pago.getFecha().equals(fecha)){
               System.out.println("==================");
               System.out.println("  Bonificaciones:");
               System.out.println("==================");
               if(pago.getBonificaciones().isEmpty()){
                   System.out.println("No posee bonificaciones");
               }else{
                    for(int i : pago.getBonificaciones()){
                        System.out.println(pago.getTipoBon()[i - 1]); 
                    }
               }
               System.out.println("==================");
               System.out.println("    Descuentos:");
               System.out.println("==================");
               if(pago.getDescuentos().isEmpty()){
                   System.out.println("No posee descuentos"); 
               }else{
                   for(int i : pago.getDescuentos()){
                        if(i == 1){
                            System.out.println(pago.getTipoDes()[i - 1] + "(" + pago.getDiasFalta() + ")");
                        }else if(i == 2){
                            System.out.println(pago.getTipoDes()[i - 1] + "(" + pago.getDiasTardanza() + ")");
                        }else{
                            System.out.println(pago.getTipoDes()[i - 1]);
                        }
                    }
               }
           } 
        }
    }
    
    public void eliminarPago(Trabajador trabajador){
        System.out.println("Fecha del pago:");
        System.out.println("--------------------");
        String fecha = scanner.nextLine();
        for(Pago pago : trabajador.getPagos()){
            if(pago.getFecha().equals(fecha)){
                trabajador.getPagos().remove(pago);
            }
        }
    }

    public void verHistorialPagos(Trabajador trabajador) {
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
    }

    public void gestionarTrabajadores(){
        int opcion = 0;
        do{
            verDatosTrabajadores();
            System.out.println("========================");
            System.out.println("          MENU");
            System.out.println("========================");
            System.out.println("1. Agregar trabajador");
            System.out.println("2. Ver datos completos");
            System.out.println("3. Actualizar trabajador");
            System.out.println("4. Eliminar trabajador");
            System.out.println("5. Ver pagos");
            System.out.println("6. Salir");
            System.out.println("-------------------------");
            System.out.println("Elija una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("-------------------------");
            switch(opcion){
                case 1: 
                    agregarTrabajadores();break;
                case 2:
                    verDatosCompletos();break;
                case 3:
                    actualizarTrabajador();break;
                case 4:
                    eliminarTrabajador();break;
                case 5:
                    gestionarPagos();break;
                case 6: 
                    System.out.println("Nos vemos en otra ocasión");
                    break;
                default:
                    System.out.println("Digite un numero del 1 al 5");
            }
        }while(opcion != 6);
    }
    
    public void gestionarPagos(){
        System.out.println("DNI: ");
        String DNI = scanner.nextLine();
        Trabajador tElegido = new Trabajador();
        boolean noEncontrado = true;
        for(Trabajador trabajador: DB.getTrabajadores()){
            if(trabajador.getDNI().equals(DNI)){
                tElegido = trabajador;
                noEncontrado = false;
                break;
            }
        }
        if(noEncontrado){
            System.out.println("No se ha encontrado al trabajador");
        }else if(tElegido.getPagos().isEmpty()){
            int opcion = 0;
            while(opcion != 2){
                System.out.println("No se ha registrado ningun pago");
                System.out.println("========================");
                System.out.println("          MENU");
                System.out.println("========================");
                System.out.println("1. Agregar pago");
                System.out.println("2. Regresar");
                opcion = scanner.nextInt();
                scanner.nextLine();
                switch(opcion){
                    case 1:
                        agregarPago(tElegido);break;
                    case 2:
                        gestionarTrabajadores();break;
                    default:
                        System.out.println("numero invalido");
                }
            }
        }else{
           int opcion = 0;
            while(opcion != 6){
                verHistorialPagos(tElegido);
                System.out.println("========================");
                System.out.println("          MENU");
                System.out.println("========================");
                System.out.println("1. Agregar pago");
                System.out.println("2. Sumar bonificaciones a un pago");
                System.out.println("3. restar descuentos a un pago");
                System.out.println("4. Ver bonificaciones y descuentos");
                System.out.println("5. Eliminar pago");
                System.out.println("6. Regresar");
                System.out.println("-------------------------");
                System.out.println("Elija una opcion: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
                System.out.println("-------------------------");
                switch(opcion){
                    case 1:
                        agregarPago(tElegido);break; 
                    case 2:
                        sumarBonificaciones(tElegido);break;
                    case 3:
                        restarDescuentos(tElegido);break;
                    case 4:
                        verBonificacionesYDescuentos(tElegido);break;
                    case 5:
                        eliminarPago(tElegido);break;
                    case 6:
                        gestionarTrabajadores();break;
                }
            } 
        }
    }
    
    public void gestionarReclamos(){
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-15s | %-15s |\n", "ID", "Fecha", "Tipo", "Estado");
        System.out.println("---------------------------------------------------------------");
        for (Trabajador trabajador: DB.getTrabajadores()) {
            for(Reclamo reclamo : trabajador.getReclamos()){
                System.out.printf("| %-10s | %-10s | %-15s | %-15s |\n",
                reclamo.getId(),
                reclamo.getFecha(),
                reclamo.getTipo()[reclamo.getId_tipo() - 1],
                reclamo.getEstado()[reclamo.getId_estado() - 1]);
                System.out.println("---------------------------------------------------------------"); 
            }
        }
        int op = 0;
        while(op < 1 || op > 3){
            System.out.println("-------------------------------");
            System.out.println("1. Ver detalle de un reclamo");
            System.out.println("2. Actualizar estado");
            System.out.println("3. Regresar al menu principal");
            System.out.println("-------------------------------");
            op = scanner.nextInt();
            scanner.nextLine();
            System.out.println("-------------------------------");
            int id;
            boolean noEncontrado;
            switch(op){
                case 1:
                    System.out.println("Digite el id del reclamo:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    noEncontrado = true;
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
                                gestionarReclamos();   
                            }
                        }
                    }
                    if(noEncontrado){
                        System.out.println("No se ha encontrado un reclamo con ese ID");
                        gestionarReclamos();
                    }
                    break;
                case 2:
                    noEncontrado = true;
                    System.out.println("------------");
                    System.out.println("ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
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
                                reclamo.setId_estado(estado - 1);
                            }
                        }
                    }
                    if(noEncontrado){
                        System.out.println("No se ha encontrado un reclamo con ese ID");
                        gestionarReclamos();
                    }
                    break;
                case 3:
                    menuPrincipal();break;
                default:
                    System.out.println("Numero invalido");break;
            }
        }
    }
    
    public void gestionarInformes(){
        
    }
}
