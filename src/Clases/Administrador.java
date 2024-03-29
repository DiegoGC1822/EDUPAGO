package Clases;

import java.util.Scanner;

public class Administrador{
    
    private String usuario = "diego";
    private String contraseña = "123";
    
    public boolean autentificar(String usu, String contra, Trabajador trabajador){
        return usu.equals(usuario) && contra.equals(contraseña);
    }
    
    public Administrador() {
    }

    public Administrador(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    Scanner scanner = new Scanner(System.in);
    
    public void menuPrincipal(){
        int opcion = 0;
        while(opcion != 5){
            System.out.println("=====================");
            System.out.println("BIENVENIDO A EDUPAGO");
            System.out.println("=====================");
            System.out.println("1. Gestionar trabajadores y sus pagos");
            System.out.println("2. Gestionar reclamos");
            System.out.println("3. Gestionar colegios");
            System.out.println("4. Gestionar informes");
            System.out.println("5. Gestionar capacitaciones");
            System.out.println("6. Gestionar noticias");
            System.out.println("7. Salir");
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
                    gestionarColegios();break;
                case 4:
                    gestionarInformes();break;
                case 5:
                    gestionarCapacitaciones();break;
                case 6:
                    gestionarNoticias();break;
                case 7:
                    System.out.println("Nos vemos en otra ocasion");
                    DB.login();break;
                default:
                    System.out.println("Numero invalido");break;
            }
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
    
    public void verColegios(){
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-18s | %-15s | %-15s | %-20s |\n", "Nombre", "Localizacion", "Trabajadores","Vacantes","Tipo");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for(Colegio colegio : DB.getColegios()){
            System.out.printf("| %-20s | %-18s | %-15d | %-15d | %-20s |\n",
                colegio.getNombre(),
                colegio.getLocalizacionNombre()[colegio.getId_localizacion() - 1],
                colegio.getNumTrabajadores(),
                colegio.getVacantes(),
                colegio.getTipo()[colegio.getId_tipo() - 1]);
            System.out.println("--------------------------------------------------------------------------------------------------------");
        }
    }
    
    public void verTrabajadoresColegio(){
        System.out.println("Nombre:");
        System.out.println("---------------");
        String nombre = scanner.nextLine();
        System.out.println("---------------");
        boolean noEncontrado = true;
        int contador = 0;
        for(Colegio colegio : DB.getColegios()){
            if(colegio.getNombre().equals(nombre)){
                noEncontrado = false;
                for(Trabajador trabajador : DB.getTrabajadores()){
                    if(trabajador.getColegio().getNombre().equals(colegio.getNombre())){
                        contador++;
                    }
                    if(contador == 1){
                        break;
                    }
                }
                if(contador == 0){
                    System.out.println("No hay trabajadores registrados en este colegio");
                }else{
                    System.out.println("-------------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %-10s | %-10s | %-15s | %-17s | %-15s | %-10s | %-10s |\n", "Nombre", "Apellido", "DNI", "Horas trabajadas", "Colegio", "Rol","Tipo");
                    System.out.println("-------------------------------------------------------------------------------------------------------------");

                    for (Trabajador trabajador : DB.getTrabajadores()) {
                        if(trabajador.getColegio().getNombre().equals(colegio.getNombre())){
                            System.out.printf("| %-10s | %-10s | %-15s | %-17s | %-15s | %-10s | %-10s |\n",
                            trabajador.getNombre(),
                            trabajador.getApellido(),
                            trabajador.getDNI(),
                            trabajador.getHorasTrabajadas(),
                            trabajador.getColegio().getNombre(),
                            trabajador.getRol()[trabajador.getId_rol() - 1],
                            trabajador.getTipo()[trabajador.getId_tipo() - 1]);
                        }
                    }

                    System.out.println("-------------------------------------------------------------------------------------------------------------");

                }
            }
        }
        if(noEncontrado){
            System.out.println("No se encontro un colegio con ese nombre");
        }
        
        System.out.println("¿Volver al anterior menu?(Y)");
        String volver = scanner.nextLine();
        gestionarColegios();
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
                    Trabajador.create();break;
                case 2:
                    Trabajador.read();break;
                case 3:
                    Trabajador.update();break;
                case 4:
                    Trabajador.delete();break;
                case 5:
                    gestionarPagos();break;
                case 6: 
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Digite un numero del 1 al 5");break;
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
                        Pago.create(tElegido);
                        menuPago(tElegido);break;
                    case 2:
                        gestionarTrabajadores();break;
                    default:
                        System.out.println("numero invalido");
                }
            }
        }else{
            menuPago(tElegido);
        }
    }
    
    public void menuPago(Trabajador tElegido){
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
                        Pago.create(tElegido);break; 
                    case 2:
                        Pago.sumarBonificaciones(tElegido);break;
                    case 3:
                        Pago.restarDescuentos(tElegido);break;
                    case 4:
                        Pago.verBonificacionesYDescuentos(tElegido);break;
                    case 5:
                        Pago.delete(tElegido);break;
                    case 6:
                        gestionarTrabajadores();break;
                }
            } 
    }
    
    public void verTablaReclamos(){
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
    }
    
    public void gestionarReclamos(){
        int op = 0;
        while(op != 3){
            verTablaReclamos();
            System.out.println("-------------------------------");
            System.out.println("1. Ver detalle de un reclamo");
            System.out.println("2. Actualizar estado");
            System.out.println("3. Regresar al menu principal");
            System.out.println("-------------------------------");
            op = scanner.nextInt();
            scanner.nextLine();
            System.out.println("-------------------------------");
            switch(op){
                case 1:
                    Reclamo.mostrarDetalle();
                    break;
                case 2:
                    Reclamo.actualizarEstado();
                    break;
                case 3:
                    menuPrincipal();break;
                default:
                    System.out.println("Numero invalido");break;
            }
        }
    }
    
    public void gestionarColegios(){
        int opcion = 0;
        while(opcion != 4){
            verColegios();
            System.out.println("----------------------------------");
            System.out.println("1. Agregar colegio");
            System.out.println("2. Quitar colegio");
            System.out.println("3. Ver trabajadores de un colegio");
            System.out.println("4. Regresar al menu principal");
            System.out.println("----------------------------------");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch(opcion){
                case 1:
                    Colegio.create();break;
                case 2:
                    Colegio.delete();break;
                case 3:
                    verTrabajadoresColegio();break;
                case 4:
                    menuPrincipal();break;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
    
    public void verTablaInformes(){
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s | %-25s | %-15s |\n", "ID", "Titulo","Fecha", "Tipo", "Estado");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        for(Trabajador trabajador : DB.getTrabajadores()){
            for(Informe informe : trabajador.getInformes()){
                System.out.printf("| %-10s | %-20s | %-15s | %-25s | %-15s |\n",
                informe.getId(),
                informe.getTitulo(),
                informe.getFecha(),
                informe.getTipo()[informe.getId_tipo() - 1],
                informe.getEstado()[informe.getId_estado() - 1]);
                System.out.println("-----------------------------------------------------------------------------------------------------");
            }
        }
    }
    
    public void gestionarInformes(){
        int op = 0;
        while(op != 3){
            verTablaInformes();
            System.out.println("--------------------------");
            System.out.println("1. Ver detalle de informe");
            System.out.println("2. Actualizar estado");
            System.out.println("3. Regresar al menu principal");
            System.out.println("-------------------------");
            op = scanner.nextInt();
            scanner.nextLine();
            String volver;
            switch(op){
                case 1: 
                    Informe.verDetalle();
                    System.out.println("---------------------");
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();
                    break;
                case 2:
                    Informe.actualizarEstado();
                    System.out.println("=========================================");
                    System.out.println("El estado se ha actualizado correctamante");
                    System.out.println("==========================================");
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();
                    break;
                case 3:
                    menuPrincipal();
                default:
                    System.out.println("Numero invalido");break;
            }
        } 
    }
    
    public void gestionarCapacitaciones(){
        int op = 0;
        String volver;
        while(op != 5){
            Capacitacion.read();
            System.out.println("--------------------------------");
            System.out.println("1. Crear capacitacion");
            System.out.println("2. Ver detalle");
            System.out.println("3. Eliminar capacitacion");
            System.out.println("4. Ver trabajadores incritos en la capacitacion");
            System.out.println("5. Regresar al menu principal");
            System.out.println("--------------------------------");
            op = scanner.nextInt();
            scanner.nextLine();
            switch(op){
                case 1:
                    Capacitacion.create();
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();
                    break;
                case 2:
                    Capacitacion.verDetalles();
                    System.out.println("---------------------");
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();
                    break;
                case 3:
                    Capacitacion.delete();
                    System.out.println("---------------------");
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();
                    break;
                case 4:
                    Inscripcion.mostrarTrabajadoresInscritos();
                    System.out.println("----------------------------------------------");
                    System.out.println("Volver al menu? (Y)");
                    volver = scanner.nextLine();
                    break;
                case 5:
                    menuPrincipal();
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
    
    public void verNoticias(){
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("| %10s | %-50s | %-20s |\n","ID" ,"Titular","Fecha de publicacion");
        System.out.println("------------------------------------------------------------------------------------------");
        for(Noticia noticia : DB.getNoticias()){
            String titular = noticia.getTipo()[noticia.getId_tipo() - 1] + " " + noticia.getTitulo();
            System.out.printf("| %10s | %-50s | %-20s |\n",
                noticia.getId(),
                titular,
                noticia.getFecha());
            System.out.println("------------------------------------------------------------------------------------------");
        }
    }
    
    public void gestionarNoticias(){
        int op = 0;
        String volver;
        while(op != 5){
            verNoticias();
            System.out.println("--------------------------------");
            System.out.println("1. Crear una noticia");
            System.out.println("2. Actualizar una noticia");
            System.out.println("3. Ver detalle de una noticia");
            System.out.println("4. Eliminar noticia");
            System.out.println("5. Volver al menu principal");
            System.out.println("--------------------------------");
            op = scanner.nextInt();
            scanner.nextLine();
            switch(op){
                case 1:
                    Noticia.create();
                    System.out.println("Regresar? (Y)");
                    volver = scanner.nextLine();break;
                case 2:
                    Noticia.update();
                    System.out.println("Regresar? (Y)");
                    volver = scanner.nextLine();break;
                case 3:
                    Noticia.verDetalle();
                    System.out.println("Regresar? (Y)");
                    volver = scanner.nextLine();break;
                case 4:
                    Noticia.delete();
                    System.out.println("Regresar? (Y)");
                    volver = scanner.nextLine();break;
                case 5:
                    menuPrincipal();break;
                default:
                    System.out.println("Numero invalido");
            }
        }
    }
}
