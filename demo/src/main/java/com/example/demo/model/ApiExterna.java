package com.example.demo.model;
import com.example.demo.exceptions.RecursoNoEncontradoException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

import java.util.List;

public class ApiExterna {
    private static final String API_URL = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.1/m/api/recursos";

    public Collection<Recurso> getRecursos(){
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.1/m/api/recursos"))
                .setHeader("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            String responseBody = httpResponse.body();
            Gson gson = new Gson();
            Recurso[] recursosArray = gson.fromJson(responseBody, Recurso[].class);
            List<Recurso> recursos = Arrays.asList(recursosArray);
            System.out.println("Status code: " + statusCode);
            System.out.println("Response body: " + responseBody);
            return  recursos;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public Optional<Recurso> findByLegajo(long legajoBuscado) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.1/m/api/recursos"))
                .setHeader("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            String responseBody = httpResponse.body();
            Gson gson = new Gson();
            Recurso[] recursosArray = gson.fromJson(responseBody, Recurso[].class);
            for (Recurso persona : recursosArray) {
                if (persona.getLegajo() == legajoBuscado) {
                    return Optional.of(persona);
                }
            }
            System.out.println("Status code: " + statusCode);
            System.out.println("Response body: " + responseBody);
            throw new RecursoNoEncontradoException("El legajo " + legajoBuscado + " no pertenece a la API externa.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}