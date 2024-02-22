package Clases;

import java.util.Scanner;


public class Colegio {
    private String nombre;
    private int id_localizacion;
    private static final String[] localizacionNombre = {"Rural 1","Rural 2","Rural 3","Frontera","VRAEM","Area metropolitana"};
    private static final double[] localizacion = {500,100,70,100,300,0};
    private int numTrabajadores;
    private int vacantes;
    private int id_tipo;
    private static final String[] tipo = {"Multigrado","Bilingue","Bilingue acreditado"};

    public Colegio() {
    }

    public Colegio(String nombre, int id_localizacion, int numTrabajadores, int vacantes, int id_tipo) {
        this.nombre = nombre;
        this.id_localizacion = id_localizacion;
        this.numTrabajadores = numTrabajadores;
        this.vacantes = vacantes;
        this.id_tipo = id_tipo;
    }
    
    public static void create(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        int id_localizacion = 0;
        while(id_localizacion < 1 || id_localizacion > 6){
            System.out.println("==================");
            System.out.println("   Localizacion: ");
            System.out.println("==================");
            System.out.println("1. Rural 1");
            System.out.println("2. Rural 2");
            System.out.println("3. Rural 3");
            System.out.println("4. Frontera");
            System.out.println("5. VRAEM");
            System.out.println("6. Area metropolitana");
            id_localizacion = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("==================");
        System.out.println("     Vacantes: ");
        System.out.println("==================");
        int vacantes = scanner.nextInt();
        scanner.nextLine();
        int id_tipo = 0;
        while(id_tipo < 1 || id_tipo > 4){
            System.out.println("===============");
            System.out.println("     Tipo: ");
            System.out.println("===============");
            System.out.println("1. Multigrado");
            System.out.println("2. Bilingue");
            System.out.println("3. Bilingue acreditado");
            id_tipo = scanner.nextInt();
            scanner.nextLine();
        }
        Colegio colegio = new Colegio(nombre,id_localizacion,0,vacantes,id_tipo);
        DB.getColegios().add(colegio);
    }
    
    public static void delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre:");
        System.out.println("---------------");
        String nombre = scanner.nextLine();
        System.out.println("---------------");
        boolean noEncontrado = true;
        Colegio colegioElegido = new Colegio();
        for(Colegio colegio : DB.getColegios()){
            if(colegio.getNombre().equals(nombre)){
                noEncontrado = false;
                colegioElegido = colegio;
                for(Trabajador trabajador : DB.getTrabajadores()){
                    if(trabajador.getColegio().getNombre().equals(colegio.getNombre())){
                        trabajador.getColegio().setNombre("Sin colegio");
                    }
                }
            }
        }
        if(noEncontrado){
            System.out.println("No se encontro un colegio con ese nombre");
        }else{
            DB.getColegios().remove(colegioElegido);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String[] getLocalizacionNombre() {
        return localizacionNombre;
    }

    public static double[] getLocalizacion() {
        return localizacion;
    }

    public static String[] getTipo() {
        return tipo;
    }

    public int getNumTrabajadores() {
        return numTrabajadores;
    }

    public void setNumTrabajadores(int numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public int getId_localizacion() {
        return id_localizacion;
    }

    public void setId_localizacion(int id_localizacion) {
        this.id_localizacion = id_localizacion;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    
    
}
