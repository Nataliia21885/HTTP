package command.pet_command;

import command.Command;
import service.PetService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class GetPetById implements Command {
    private final View view;
    public static final String GET_PET_BY_ID = "get_pet_by_id";
    private final PetService petService;
    private final URI uri;

    public GetPetById(View view, PetService petService, URI uri) {
        this.view = view;
        this.petService = petService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_PET_BY_ID);
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
        System.out.println(petService.getPetById(uri, id));
    }
}
