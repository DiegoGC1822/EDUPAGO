package Clases;

public class Director extends Empleado{
    
    public boolean autentificar(String usu, String contra, Trabajador trabajador){
        return usu.equals(trabajador.getNombre()) && contra.equals(trabajador.getDNI()) 
                && trabajador.getRol()[trabajador.getId_rol() - 1].equals("Director");
    }
    
    public void menuPrincipal(Trabajador trabajador){
        int opcion = 0;
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
            System.out.println("7. Salir");
            System.out.println("---------------------");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("---------------------");
            switch(opcion){
                case 1:
                    verPlanilla(trabajador);
                case 2:
                    verPagos(trabajador);
                case 3:
                    hacerReclamo(trabajador);
                case 4:
                    verReclamos(trabajador);
                case 5:
                    hacerInforme();
                case 6:
                    verInformes();
                case 7:
                    System.out.println("Nos vemos en otra ocasion");
                    DB.login();
            }
        }
    }
    
    public void hacerInforme(){
        
    }
    
    public void verInformes(){
        
    }
}
