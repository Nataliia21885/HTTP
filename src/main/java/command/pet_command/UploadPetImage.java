package command.pet_command;

import command.Command;
import service.PetService;
import view.View;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

public class UploadPetImage implements Command {
    private final View view;
    public static final String UPLOAD_PET_IMAGE = "upload_pet_image";
    private final PetService petService;
    private final URI uri;

    public UploadPetImage(View view, PetService petService, URI uri) {
        this.view = view;
        this.petService = petService;
        this.uri = uri;
    }
    @Override
    public boolean canExecute(String input) {
        return input.equals(UPLOAD_PET_IMAGE);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        Integer petId;
        Path path;
        System.out.println("Please, enter pet id");
        while (true){
            try{
                petId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e){
                System.out.println("Please, use only digits!");
            }
        }
        System.out.println("Please, enter image path, for example: panda.jpg");
        path = new File("src/main/resources/" + view.read()).toPath();

        System.out.println(petService.postPetUploadImage(uri, petId, path));
    }
}
