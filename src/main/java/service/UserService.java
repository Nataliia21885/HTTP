package service;

import com.google.gson.Gson;
import models.ApiResponse;
import models.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserService {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public ApiResponse postUser(URI uri, User user) throws IOException, InterruptedException {
        String requestGson = gson.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestGson))
                .uri(uri)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public ApiResponse postUserWithArray(URI uri, User[] users) throws IOException, InterruptedException {
        String requestGson = gson.toJson(users);
        uri = URI.create(uri + "/createWithArray");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestGson))
                .uri(uri)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public ApiResponse postUserWithList(URI uri, List<User> users) throws IOException, InterruptedException {
        String requestGson = gson.toJson(users);
        uri = URI.create(uri + "/createWithList");
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestGson))
                .uri(uri)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public User getUserByUserName(URI uri, String userName) throws IOException, InterruptedException {
        uri = URI.create(uri + "/" + userName);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), User.class);
    }

    public ApiResponse putUser(URI uri, User user) throws IOException, InterruptedException {
        String requestGson = gson.toJson(user);
        uri = URI.create(uri + "/" + user.getUsername());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(requestGson))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public void deleteUser(URI uri, String userName) throws IOException, InterruptedException {
        uri = URI.create(uri + "/" + userName);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }

    public ApiResponse getUserLogin(URI uri, String userName, String password) throws IOException, InterruptedException {
        uri = URI.create(uri + "/login?username=" + userName + "&password=" + password);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public ApiResponse getUserLogout(URI uri) throws IOException, InterruptedException {
        uri = URI.create(uri + "/logout");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }
}
