package Clases;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Noticia {
   private String id;
   private String titulo;
   private int id_tipo;
   private static final String[] tipo = {"[COMUNICADO]","[CONVOCATORIA]","[CONCURSO]"};
   private String detalle;
   private String fecha;

    public Noticia() {
    }

   
    public Noticia(String id, String titulo, int id_tipo, String detalle, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.id_tipo = id_tipo;
        this.detalle = detalle;
        this.fecha = fecha;
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
        int op = 0;
        while(op < 1 || op > 3){
            System.out.println("---------------");
            System.out.println("     Tipo:");
            System.out.println("---------------");
            System.out.println("1. Comunicado");
            System.out.println("2. Convocatoria");
            System.out.println("3. Concurso");
            System.out.println("4. Capacitacion");
            System.out.println("---------------");
            op = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("Detalle:");
        String detalle = scanner.nextLine();
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada = formato.format(fechaActual);
        String id = generarID(3);
        Noticia noticia = new Noticia(id,titulo,op,detalle,fechaFormateada);
        DB.getNoticias().add(noticia);
        System.out.println("====================================");
        System.out.println("La noticia se ha creado exitosamente");
        System.out.println("====================================");
    }
    
    public static void verDetalle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        for(Noticia noticia : DB.getNoticias()){
            if(noticia.getId().equals(id)){
                noEncontrado = false;
                System.out.println("=====================");
                System.out.println("Detalle de la noticia");
                System.out.println("=====================");
                System.out.println(noticia.getDetalle());  
            }
        }
        if(noEncontrado){
            System.out.println("No se ha encontrado un reclamo con ese ID");
        }
    }
    
    public static void update(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        for(Noticia noticia : DB.getNoticias()){
            if(noticia.getId().equals(id)){
                noEncontrado = false;
                int op = 0;
                while(op < 1 || op > 3){
                    System.out.println("=====================");
                    System.out.println("Atributo a actualizar");
                    System.out.println("====================");
                    System.out.println("1. Titulo");
                    System.out.println("2. Tipo");
                    System.out.println("3. Detalle");
                    op = scanner.nextInt();
                    scanner.nextLine();
                    switch(op){
                        case 1:
                            System.out.println("===============");
                            System.out.println("Titulo actual");
                            System.out.println("===============");
                            System.out.println(noticia.getTitulo());
                            System.out.println("==============");
                            System.out.println("Nuevo titulo");
                            System.out.println("==============");
                            String titulo = scanner.nextLine();
                            noticia.setTitulo(titulo);break;
                        case 2:
                            System.out.println("===============");
                            System.out.println("  Tipo actual");
                            System.out.println("===============");
                            System.out.println(noticia.getTitulo());
                            System.out.println("===============");
                            System.out.println("  Nuevo tipo");
                            System.out.println("===============");
                            int op1 = 0;
                            while(op1 < 1 || op1 > 4){
                                System.out.println("1. Comunicado");
                                System.out.println("2. Convocatoria");
                                System.out.println("3. Concurso");
                                System.out.println("4. Capacitacion");
                                System.out.println("---------------");
                                op1 = scanner.nextInt();
                                scanner.nextLine();
                            }
                            noticia.setId_tipo(op1);break;
                        case 3:
                            System.out.println("===============");
                            System.out.println("Detalle actual");
                            System.out.println("===============");
                            System.out.println(noticia.getTitulo());
                            System.out.println("==============");
                            System.out.println("Nuevo Detalle");
                            System.out.println("==============");
                            String detalle = scanner.nextLine();
                            noticia.setDetalle(detalle);break;
                        default:
                            System.out.println("Numero invalido");
                    }
                }
            }
        }
        if(noEncontrado){
            System.out.println("No se ha encontrado a la noticia");
        }else{
            System.out.println("==========================================");
            System.out.println("La noticia se ha actualizado correctamente");
            System.out.println("==========================================");
        }
    }
    
    public static void delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        String id = scanner.nextLine();
        boolean noEncontrado = true;
        Noticia noticiaElegida = new Noticia();
        for(Noticia noticia : DB.getNoticias()){
            if(noticia.getId().equals(id)){
                noticiaElegida = noticia;
                noEncontrado = false;
            }
        }
        if(noEncontrado){
            System.out.println("No se ha encontrado la noticia");
        }else{
            DB.getNoticias().remove(noticiaElegida);
            System.out.println("=============================================");
            System.out.println("La noticia ha sido borrada exitosamente");
            System.out.println("=============================================");
        }
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

    public int getId_tipo() {
        return id_tipo;
    }

    public static String[] getTipo() {
        return tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
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
