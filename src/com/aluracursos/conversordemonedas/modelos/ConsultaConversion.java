package com.aluracursos.conversordemonedas.modelos;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaConversion {

    public Tasa busquedaConversionTasa(String tasaOrigen){
        String apiKey = "3c48a53b44a55725b029005b";
        String direccion = "https://v6.exchangerate-api.com/v6/"+ apiKey + "/latest/" + tasaOrigen;
        String json = "";
        Tasa tasa = null;
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();

            TasasDeConversion tasasDeConversion = new Gson().fromJson(json, TasasDeConversion.class);
            tasa = tasasDeConversion.conversion_rates();

        }catch (IllegalArgumentException e) {
            System.out.println("Error en la URI, Verifique la direccion");
        }
        catch (Exception e){
            System.out.println("Ocurrio un error inesperado!!");
        }

        return tasa;
    }

}
