package modelo;

public class Cliente {
    private int idCliente;
    private String nombreCompleto;
    private String aliasGamer;
    private double saldoDisponible;

    // Constructor
    public Cliente(int idCliente, String nombreCompleto, String aliasGamer) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.aliasGamer = aliasGamer;
        this.saldoDisponible = 0.0; // para que inicie con saldo cero
    }

    // Métodos
    public void registrarCliente() {
        // esto se guardaría en BD, para efectos del taller confirmamos por consola
        System.out.println("Cliente " + aliasGamer + " registrado con éxito.");
    }

    public void recargarSaldo(double monto) {
        if (monto > 0) {
            this.saldoDisponible += monto;
        }
    }

    public double verificarSaldo() {
        return this.saldoDisponible;
    }

    // Getters
    public String getAliasGamer() { return aliasGamer; }
    public void setSaldoDisponible(double saldoDisponible) { this.saldoDisponible = saldoDisponible; }
}