package Controladores;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IAService {

    private static final String API_KEY = "AQUÍ_TU_API_KEY";
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String MODELO = "llama-3.3-70b-versatile";

    public int sugerirPrioridad(String descripcion) {

        try {
            String prompt = "Analiza esta descripción de una tarea y responde SOLO con un número del 1 al 5 indicando la prioridad. Descripción: "
                    + descripcion;

            String cuerpoJson = "{"
                    + "\"model\": \"" + MODELO + "\","
                    + "\"messages\": ["
                    + "  { \"role\": \"system\", \"content\": \"Eres un asistente que solo responde con números del 1 al 5.\" },"
                    + "  { \"role\": \"user\",   \"content\": \"" + escapar(descripcion) + "\" }"
                    + "]"
                    + "}";

            HttpClient cliente = HttpClient.newHttpClient();

            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(cuerpoJson))
                    .build();

            HttpResponse<String> respuesta = cliente.send(
                    peticion,
                    HttpResponse.BodyHandlers.ofString()
            );

            String contenido = extraerContenido(respuesta.body()).trim();

            return Integer.parseInt(contenido);

        } catch (Exception e) {
            System.out.println("Error al consultar la IA: " + e.getMessage());
            return 3; // prioridad por defecto si falla
        }
    }

    private String extraerContenido(String json) {
        String clave = "\"content\":\"";
        int pos = json.indexOf(clave);
        if (pos == -1) return "3";
        pos += clave.length();

        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '"') break;
            sb.append(c);
        }
        return sb.toString();
    }

    private String escapar(String texto) {
        return texto.replace("\"", "\\\"");
    }
}
