package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.ApiResponse;
import models.Pet;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PetService {


    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public ApiResponse postPetUploadImage(URI uri, Integer petId, Path path) throws IOException, InterruptedException {
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", new File(path.toUri()), ContentType.parse(Files.probeContentType(path)),
                        path.getFileName().toString())
                .build();
        Pipe pipe = Pipe.open();
        new Thread(() -> {
            try (OutputStream outputStream = Channels.newOutputStream(pipe.sink())) {
                httpEntity.writeTo(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        uri = URI.create(uri + "/" + petId + "/uploadImage");
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Content-Type", httpEntity.getContentType().getValue())
                .POST(HttpRequest.BodyPublishers.ofInputStream(() -> Channels.newInputStream(pipe.source()))).build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public Pet postPet(URI uri, Pet pet) throws IOException, InterruptedException {
        String requestGson = gson.toJson(pet);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestGson))
                .uri(uri)
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Pet.class);
    }

    public ApiResponse postPetWithData(URI uri, Integer petId, String name, String status) throws IOException, InterruptedException {
        String requestGson = "name=" + name + "&status=" + status;
        uri = URI.create(uri + "/" + petId);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestGson))
                .uri(uri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ApiResponse.class);
    }

    public Pet putPet(URI uri, Pet pet) throws IOException, InterruptedException {
        String requestGson = gson.toJson(pet);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(requestGson))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Pet.class);
    }

    public List<Pet> getPetByStatus(URI uri, String status) throws IOException, InterruptedException {
        uri = URI.create(uri + "findByStatus?status=" + status);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), new TypeToken<List<Pet>>(){}.getType());
    }

    public Pet getPetById(URI uri, Integer petId) throws IOException, InterruptedException {
        uri = URI.create(uri + "/" + petId);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), Pet.class);
    }

    public void deletePet(URI uri, Integer petId) throws IOException, InterruptedException {
        uri = URI.create(uri + "/" +petId);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
    }
}
