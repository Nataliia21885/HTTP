package service;

import com.google.gson.Gson;
import models.Order;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OrderService {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public Order postOrder(URI uri, Order order) throws IOException, InterruptedException {
        String requestGson = gson.toJson(order);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestGson))
                .uri(uri)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Order.class);
    }

    public Order getOrderById(URI uri, Integer id) throws IOException, InterruptedException {
        uri = URI.create(uri + "/" + id);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Order.class);
    }

    public void getOrderByStatus() throws IOException, InterruptedException {
        URI uri = URI.create("https://petstore.swagger.io/v2/store/inventory");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);
    }

    public void deleteOrder(URI uri, Integer id) throws IOException, InterruptedException {
        uri = URI.create(uri + "/" + id);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }
}
