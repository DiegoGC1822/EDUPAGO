package Clases;

import java.util.ArrayList;
import java.util.List;

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
    
    public double calcularSueldo(double escala, double cargoExtra, int horasTrabajadas, double localizacionColegio){
         return horasTrabajadas * 103.25 + escala + cargoExtra + localizacionColegio;
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
