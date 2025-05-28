package src;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorMonedaAPI {

    static final String API_KEY = "EXCHANGE_API_KEY";

    public static void convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaOrigen;
            HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
            con.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }

            reader.close();
            con.disconnect();

            JsonObject json = JsonParser.parseString(respuesta.toString()).getAsJsonObject();

            if (!json.get("result").getAsString().equals("success")) {
                System.out.println("Error en la API.");
                return;
            }

            JsonObject rates = json.getAsJsonObject("conversion_rates");

            if (!rates.has(monedaDestino)) {
                System.out.println("Moneda destino no válida.");
                return;
            }

            double tasa = rates.get(monedaDestino).getAsDouble();
            double resultado = cantidad * tasa;

            System.out.printf("Resultado: %.2f %s = %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino);

        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Ejemplo: convertir 100 USD a EUR
        convertir("USD", "EUR", 100);
    }
}
