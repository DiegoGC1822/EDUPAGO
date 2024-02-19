package Clases;

public abstract class Usuario {
    private String nombre_usuario;
    private String contraseña;
    
    public Usuario() {
    }
    
    public Usuario(String nombre_usuario, String contraseña) {
        this.nombre_usuario = nombre_usuario;
        this.contraseña = contraseña;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public abstract boolean autentificar(String usu, String contra, Trabajador trabajador);
}
