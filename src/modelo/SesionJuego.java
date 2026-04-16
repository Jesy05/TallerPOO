package modelo;

import java.time.LocalDateTime;
import java.time.Duration;

public class SesionJuego {
    private int idSesion;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private double tarifaPorHora;
    private double costoTotal;

    private Cliente clienteActivo;
    private Computadora pcActiva;

    // Constructor
    public SesionJuego(int idSesion, double tarifaPorHora) {
        this.idSesion = idSesion;
        this.tarifaPorHora = tarifaPorHora;
    }

    // Métodos
    public void iniciarSesion(Cliente cliente, Computadora pc) {
        this.clienteActivo = cliente;
        this.pcActiva = pc;
        this.horaInicio = LocalDateTime.now(); // Captura la hora actual

        pc.actualizarEstado(false); // La PC ya no está disponible
        pc.encenderEquipo();
    }

    public void finalizarSesion() {
        this.horaFin = LocalDateTime.now(); // Captura la hora de finalización
        this.pcActiva.actualizarEstado(true); // Libera la PC
        this.pcActiva.apagarEquipo();
        this.costoTotal = calcularCostoTotal();

        // descuenta el saldo del cliente
        double nuevoSaldo = clienteActivo.verificarSaldo() - this.costoTotal;
        clienteActivo.setSaldoDisponible(nuevoSaldo);
    }

    public double calcularCostoTotal() {
        // Calcula la diferencia en segundos para no esperar un monton
        long segundosJugados = Duration.between(horaInicio, horaFin).getSeconds(); //con duration de java.time para contar el tiempo del cliete

        // si jugó menos de 10 segundos (para pruebas), le cobramos una hora mínima para la simulación
        if(segundosJugados < 10) {
            return tarifaPorHora;
        }

        // fórmula basada en horas (segundos a horas)
        double horasJugadas = segundosJugados / 3600.0;
        return horasJugadas * tarifaPorHora;
    }

    // Getters
    public double getCostoTotal() { return costoTotal; }
    public int getIdSesion() { return idSesion; }
}