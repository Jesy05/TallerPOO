package modelo;

public class Computadora {
    private int idComputadora;
    private String especificaciones;
    private boolean disponible; // true = Disponible, false = En uso

    // Constructor
    public Computadora(int idComputadora, String especificaciones) {
        this.idComputadora = idComputadora;
        this.especificaciones = especificaciones;
        this.disponible = true; // al crearla está disponible
    }

    // Métodos
    public void encenderEquipo() {
        System.out.println("PC " + idComputadora + " encendida y lista.");
    }

    public void apagarEquipo() {
        System.out.println("PC " + idComputadora + " apagándose...");
    }

    public void actualizarEstado(boolean nuevoEstado) {
        this.disponible = nuevoEstado;
    }

    // Getters
    public int getIdComputadora() { return idComputadora; }
    public String getEspecificaciones() { return especificaciones; }
    public boolean isDisponible() { return disponible; }
}