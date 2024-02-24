package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pago {
    private double sueldoBruto;
    private static final String[] tipoBon = {"desempeño","perfecta asistencia","actividad extracurricular"};
    private static final int[] cantidadBon = {300,100,150}; 
    private List<Integer> bonificaciones = new ArrayList<>(); 
    private int diasFalta = 0;
    private int diasTardanza = 0;
    private static final String[] tipoDes = {"falta","tardanza","seguro medico"};
    private static final int[] cantidadDes = {40,20,400}; 
    private List<Integer> descuentos = new ArrayList<>();
    private double sueldo;
    private String fecha;
    private static final double[] cargoExtraPago = {800, 600, 400, 0};
    
    public static void create(Trabajador trabajador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fecha: ");
        String fecha = scanner.nextLine();
        Pago pago = new Pago();
        pago.setFecha(fecha);
        pago.setSueldoBruto(pago.calcularSueldo(trabajador, pago));
        pago.setSueldo(pago.getSueldoBruto());
        trabajador.getPagos().add(pago);
    }
    
    public static void sumarBonificaciones(Trabajador trabajador){
        Scanner scanner = new Scanner(System.in);
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
    
    public static void restarDescuentos(Trabajador trabajador){
        Scanner scanner = new Scanner(System.in);
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
    
    public static void verBonificacionesYDescuentos(Trabajador trabajador){
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("---------------------------------");
        System.out.println("Volver a la gestion de trabajadores? (Y)");
        String volver = scanner.nextLine();
    }
    
    public static void delete(Trabajador trabajador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fecha del pago:");
        System.out.println("--------------------");
        String fecha = scanner.nextLine();
        Pago pagoEncontrado = new Pago();
        boolean noEncontrado = true;
        for(Pago pago : trabajador.getPagos()){
            if(pago.getFecha().equals(fecha)){
                pagoEncontrado = pago;
                noEncontrado = false;
            }
        }
        if(noEncontrado){
            System.out.println("No se encontro el pago");
        }else{
            trabajador.getPagos().remove(pagoEncontrado);
        }
    }
    
    public double calcularSueldo(Trabajador trabajador, Pago pago){
        double escala = trabajador.getEscala()[trabajador.getId_escala() - 1];
        double cargoExtra = pago.getCargoExtraPago()[trabajador.getId_cargoExtra() - 1];
        double localizacionColegio = trabajador.getColegio().getLocalizacion()[trabajador.getColegio().getId_localizacion() - 1];
        return trabajador.getHorasTrabajadas() * 103.25 + escala + cargoExtra + localizacionColegio;
    }
    
    public double BonificacionTotal(){
        int total = 0;
        for(int i: bonificaciones){
            total = total + cantidadBon[i - 1];
        }
        return total;
    }
    
    public double DescuentoTotal(){
        int seguro = 0;
        int falta = 0;
        int tarde = 0;
        for(int i: descuentos){
            if(i == 1){
                falta = cantidadDes[i - 1] * diasFalta;
            }else if(i == 2){
                tarde = cantidadDes[i - 1] * diasTardanza;
            }else{
                seguro = cantidadDes[i - 1];
            }
        }
        return falta + tarde + seguro;
    }

    public static String[] getTipoBon() {
        return tipoBon;
    }

    public static int[] getCantidadBon() {
        return cantidadBon;
    }

    public static String[] getTipoDes() {
        return tipoDes;
    }

    public static int[] getCantidadDes() {
        return cantidadDes;
    }

    public void setSueldoBruto(double sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getSueldoBruto() {
        return sueldoBruto;
    }

    public double getSueldo() {
        return sueldo;
    }

    public String getFecha() {
        return fecha;
    }

    public double getCargoExtraPago(int indice) {
        return cargoExtraPago[indice - 1];
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Integer> getBonificaciones() {
        return bonificaciones;
    }

    public void setBonificaciones(List<Integer> bonificaciones) {
        this.bonificaciones = bonificaciones;
    }

    public List<Integer> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<Integer> descuentos) {
        this.descuentos = descuentos;
    }

    public static double[] getCargoExtraPago() {
        return cargoExtraPago;
    }

    public int getDiasFalta() {
        return diasFalta;
    }

    public void setDiasFalta(int diasFalta) {
        this.diasFalta = diasFalta;
    }

    public int getDiasTardanza() {
        return diasTardanza;
    }

    public void setDiasTardanza(int diasTardanza) {
        this.diasTardanza = diasTardanza;
    }
    
}
