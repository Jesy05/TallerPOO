package main;

import javax.swing.JOptionPane;

// Agrega estas tres líneas:
import modelo.Cliente;
import modelo.Computadora;
import modelo.SesionJuego;

public class Main {
    public static void main(String[] args) {
        // datos de prueba (Mock Data)
        Cliente cliente1 = new Cliente(1, "Juan Perez", "ShadowHunter");
        Computadora pc1 = new Computadora(101, "RTX 4080, Core i9, 32GB RAM");
        SesionJuego sesionActual = null;

        double tarifaLocal = 2.50; // $2.50 la hora

        //opciones para el cliente
        String[] opciones = {
                "1. Ver Estado de PC",
                "2. Recargar Saldo",
                "3. Iniciar Sesión de Juego",
                "4. Finalizar Sesión",
                "5. Salir"
        };

        boolean salir = false;

        JOptionPane.showMessageDialog(null, "¡Bienvenido al Sistema de Gestión de E-Spot!");

        while (!salir) {
            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Cliente actual: " + cliente1.getAliasGamer() + " | Saldo: $" + cliente1.verificarSaldo() + "\nSeleccione una acción:",
                    "Menú Principal - PC Bang",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            //si presiona cancelar o la X de la ventana
            if (seleccion == null) {
                salir = true;
                continue;
            }

            //evaluaria la opción elegida extrayendo el primer número
            char opcionElegida = seleccion.charAt(0);

            switch (opcionElegida) {
                case '1': // ver Estado de PC
                    String estadoStr = pc1.isDisponible() ? "DISPONIBLE" : "EN USO";
                    JOptionPane.showMessageDialog(null,
                            "PC #" + pc1.getIdComputadora() + "\n" +
                                    "Specs: " + pc1.getEspecificaciones() + "\n" +
                                    "Estado: " + estadoStr
                    );
                    break;

                case '2': // para recargar saldo
                    try { //validacion por si ponen una entrada rara
                        String inputMonto = JOptionPane.showInputDialog("Ingrese el monto a recargar ($):");
                        if (inputMonto != null && !inputMonto.trim().isEmpty()) {
                            double monto = Double.parseDouble(inputMonto);
                            cliente1.recargarSaldo(monto);
                            JOptionPane.showMessageDialog(null, "Recarga exitosa. Nuevo saldo: $" + cliente1.verificarSaldo());
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case '3': // Iniciar Sesión
                    if (!pc1.isDisponible()) {
                        JOptionPane.showMessageDialog(null, "La PC ya está en uso.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else if (cliente1.verificarSaldo() < tarifaLocal) {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente. Mínimo requerido: $" + tarifaLocal, "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        sesionActual = new SesionJuego(5001, tarifaLocal);
                        sesionActual.iniciarSesion(cliente1, pc1);
                        JOptionPane.showMessageDialog(null, "¡Sesión iniciada!\nEl tiempo está corriendo para " + cliente1.getAliasGamer() + ".");
                    }
                    break;

                case '4': // Finalizar Sesión
                    if (sesionActual == null || pc1.isDisponible()) {
                        JOptionPane.showMessageDialog(null, "No hay ninguna sesión activa en este momento.", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        sesionActual.finalizarSesion();
                        JOptionPane.showMessageDialog(null,
                                "--- TICKET DE JUEGO ---\n" +
                                        "Ticket #" + sesionActual.getIdSesion() + "\n" +
                                        "Costo total: $" + String.format("%.2f", sesionActual.getCostoTotal()) + "\n" +
                                        "Saldo restante: $" + String.format("%.2f", cliente1.verificarSaldo()) + "\n" +
                                        "¡Gracias por jugar!"
                        );
                        sesionActual = null; // Reiniciar la sesión
                    }
                    break;

                case '5': // Salir
                    salir = true;
                    JOptionPane.showMessageDialog(null, "Cerrando sistema...");
                    break;
            }
        }
    }
}