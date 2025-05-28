package src;

import java.util.*;

public class MenuConversor {

    static final String[] MONEDAS = {
            "USD", "EUR", "GBP", "JPY", "ARS",
            "CLP", "BRL", "MXN", "CAD", "CNY"
    };

    public static void main(String[] ignoredArgs) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenuPrincipal();

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarMonedas();

                    System.out.print("▸ Elige número de moneda de origen: ");
                    int idxOrigen = scanner.nextInt() - 1;

                    System.out.print("▸ Elige número de moneda de destino: ");
                    int idxDestino = scanner.nextInt() - 1;

                    if (esIndiceValido(idxOrigen) || esIndiceValido(idxDestino)) {
                        System.out.println("⚠️  Selección inválida de moneda.");
                        break;
                    }

                    String origen = MONEDAS[idxOrigen];
                    String destino = MONEDAS[idxDestino];

                    System.out.printf("🔁 Convertir de %s a %s%n", origen, destino);

                    System.out.print("▸ Ingresa la cantidad: ");
                    double cantidad = scanner.nextDouble();

                    ConversorMonedaAPI.convertir(origen, destino, cantidad);

                    System.out.print("\n¿Deseás hacer otra conversión? (s11/n): ");
                    String respuesta = scanner.next();
                    if (respuesta.equalsIgnoreCase("n")) {
                        continuar = false;
                        System.out.println("👋 Gracias por usar el conversor. ¡Hasta luego!");
                    }
                    break;

                case 2:
                    System.out.println("👋 Gracias por usar el conversor. ¡Hasta luego!");
                    continuar = false;
                    break;

                default:
                    System.out.println("❌ Opción no válida. Intenta nuevamente.");
            }
        }

        scanner.close();
    }

    private static boolean esIndiceValido(int idx) {
        return idx < 0 || idx >= MONEDAS.length;
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      🌍 CONVERSOR DE MONEDAS       ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ 1 ▸ Convertir moneda               ║");
        System.out.println("║ 2 ▸ Salir                          ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("Selecciona una opción: ");
    }

    private static void mostrarMonedas() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║       💱 MONEDAS DISPONIBLES       ║");
        System.out.println("╠════════════════════════════════════╣");
        for (int i = 0; i < MONEDAS.length; i++) {
            System.out.printf("║ %2d ▸ %-30s ║%n", i + 1, MONEDAS[i]);
        }
        System.out.println("╚════════════════════════════════════╝");
    }
}
