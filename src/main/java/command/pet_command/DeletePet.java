package command.pet_command;

import command.Command;
import service.PetService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class DeletePet implements Command {
    private final View view;
    public static final String DELETE_PET = "delete_pet";
    private final PetService petService;
    private final URI uri;

    public DeletePet(View view, PetService petService, URI uri) {
        this.view = view;
        this.petService = petService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_PET);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        Integer id;
        view.write("Please, enter pet id");
        while (true) {
            try {
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        petService.deletePet(uri, id);
    }
}
