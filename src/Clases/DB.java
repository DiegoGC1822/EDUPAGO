package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DB {
    private static List<Trabajador> trabajadores = new ArrayList<>();
    private static List<Colegio> colegios = new ArrayList<>();
    private static List<Noticia> noticias = new ArrayList<>();
    private static List<Capacitacion> capacitaciones = new ArrayList<>();
    private static List<Inscripcion> inscripciones = new ArrayList<>();
    private static List<Visualizacion> visualizaciones = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public DB() {
    }
    
    public static void inicializacion() {
        // Agregar colegios de ejemplo
        colegios.add(new Colegio("Mercedes", 1, 1, 14, 2));
        colegios.add(new Colegio("Santa Lucia", 3, 1, 20, 3));

        // Agregar trabajadores de ejemplo
        Trabajador trabajador1 = new Trabajador("Juan", "Perez", colegios.get(0), "12345678", 40, 1, 1, 2 , 1, 5,  new ArrayList<>(), new ArrayList<>());
        Trabajador trabajador2 = new Trabajador("Maria", "Gonzalez", colegios.get(1), "87654321", 35, 2, 2, 2, 3, 6, new ArrayList<>(), new ArrayList<>());

        // Agregar pagos de ejemplo para los trabajadores
        Pago pago1 = new Pago();
        pago1.setFecha("17-02-2024");
        pago1.setSueldoBruto(pago1.calcularSueldo(trabajador1, pago1));
        pago1.setSueldo(pago1.getSueldoBruto());
        trabajador1.getPagos().add(pago1);

        Pago pago2 = new Pago();
        pago2.setFecha("15-01-2024");
        pago2.setSueldoBruto(pago2.calcularSueldo(trabajador2, pago2));
        pago2.setSueldo(pago2.getSueldoBruto());
        trabajador2.getPagos().add(pago2);
        
        Reclamo reclamo1 = new Reclamo(3,"No me pagaron el mes pasado",1,"16-02-2024");
        reclamo1.setId("1a1");
        trabajador1.getReclamos().add(reclamo1);
        
        Reclamo reclamo2 = new Reclamo(3,"Trabajo 40 horas",2,"5-01-2024");
        reclamo2.setId("3B1");
        trabajador2.getReclamos().add(reclamo2);
        
        String detalle1 = "Esta capacitacion se centrarq en tecnicas de evaluacion formativa \n"
                + "para monitorear el progreso de los estudiantes de manera continua. Se abordaran \n"
                + "diferentes metodos de retroalimentacion y estrategias para integrar la evaluacion \n"
                + "en el proceso de enseñanza-aprendizaje." ;
        
        Capacitacion capacitacion1 = new Capacitacion("Aa2","Estrategias de Evaluacion Formativa en el Aula",
                "Lunes-Miercoles-Viernes",2,2,detalle1,25,"2-03-2024");
        
        String detalle2 = "Esta capacitacion proporcionara a los profesores habilidades practicas \n"
                + "para integrar herramientas digitales en sus practicas pedagogicas. Se exploraran \n"
                + "recursos en linea, aplicaciones educativas y plataformas de aprendizaje virtual.";
        
        Capacitacion capacitacion2 = new Capacitacion("Bb1","Uso Efectivo de Herramientas Digitales en el Aula",
                "Martes-Jueves",3,1,detalle2,30,"30-02-2024");
        
        Inscripcion inscripcion1 = new Inscripcion(capacitacion1,trabajador1);
        Inscripcion inscripcion2 = new Inscripcion(capacitacion2,trabajador2);
        
        String detalle3 = "Se convoca a todos los docentes nombrados de escala 6 en adelante a una reunión de planificacion curricular para\n"
                + "el proximo semestre. En esta reunion se discutiran los objetivos educativos, las estrategias de\n"
                + "enseñanza y los recursos necesarios para garantizar una experiencia de aprendizaje de calidad\n"
                + "para los estudiantes.";
        
        Noticia noticia1 = new Noticia("3ab","Reunion de Planificacion Curricular",2,detalle3,"20-02-2024");
        
        String detalle4 = "Se anuncia el lanzamiento del concurso \"Innovación Pedagogica del Mes\" dirigido a todo el personal docente\n"
                + "de los colegios. Los participantes pueden enviar propuestas de nuevas metodologias, actividades o proyectos que promuevan\n "
                + "la innovacion en el aula. El ganador sera seleccionado por un comite evaluador y recibira un premio especial.\n "
                + "La fecha limite para enviar propuestas es el prooximo viernes.";
        
        Noticia noticia2 = new Noticia("1v2","Innovacion Pedagogica del Mes",3,detalle4,"21-02-2024");
        
        Visualizacion visualizacion1 = new Visualizacion(noticia1,trabajador1);
        Visualizacion visualizacion2 = new Visualizacion(noticia1,trabajador2);
        Visualizacion visualizacion3 = new Visualizacion(noticia2,trabajador1);
        Visualizacion visualizacion4 = new Visualizacion(noticia2,trabajador2);
        
        visualizaciones.add(visualizacion1);
        visualizaciones.add(visualizacion2);
        visualizaciones.add(visualizacion3);
        visualizaciones.add(visualizacion4);
        
        noticias.add(noticia1);
        noticias.add(noticia2);
        
        capacitaciones.add(capacitacion1);
        capacitaciones.add(capacitacion2);
        
        inscripciones.add(inscripcion1);
        inscripciones.add(inscripcion2);

        trabajadores.add(trabajador1);
        trabajadores.add(trabajador2);
    }
    
    public static void login(){
        Administrador admin = new Administrador();
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
                if(trabajador.autentificar(usuario, contraseña, trabajador)){
                    trabajador.menuPrincipal(trabajador);
                    noEncontrado = false;
                }
            }
        }
        if(noEncontrado){
            System.out.println("El usuario o contraseña son incorrectos");
            login();
        }
    }
    
    public static List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public static List<Colegio> getColegios() {
        return colegios;
    }

    public static List<Noticia> getNoticias() {
        return noticias;
    }

    public static List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public static List<Visualizacion> getVisualizaciones() {
        return visualizaciones;
    }

    public static List<Capacitacion> getCapacitaciones() {
        return capacitaciones;
    }
}
