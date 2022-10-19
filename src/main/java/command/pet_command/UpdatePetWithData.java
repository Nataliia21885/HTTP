package command.pet_command;

import command.Command;
import models.PetStatus;
import service.PetService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class UpdatePetWithData implements Command {
    private final View view;
    public static final String UPDATE_PET_WITH_DATA = "update_pet_with_data";
    private final PetService petService;
    private final URI uri;

    public UpdatePetWithData(View view, PetService petService, URI uri) {
        this.view = view;
        this.petService = petService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_PET_WITH_DATA);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        Integer petId;
        String name;
        String status;

        view.write("Please, enter pet id");
        while (true) {
            try {
                petId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        view.write("Please, enter pet name");
        name = view.read();

        view.write("Please, choose one of the pet's status : available, pending, sold");
        while (true) {
            String stat = view.read();
            if(stat.equals(PetStatus.AVAILABLE.getName())) {
                status = PetStatus.AVAILABLE.getName();
                break;
            } else if(stat.equals(PetStatus.PENDING.getName())) {
                status = PetStatus.PENDING.getName();
                break;
            } else if(stat.equals(PetStatus.SOLD.getName())) {
                status = PetStatus.SOLD.getName();
                break;
            } else {
                System.out.println("Please, choose correct status from following list");
            }
        }

        System.out.println(petService.postPetWithData(uri, petId, name, status));
    }
}
