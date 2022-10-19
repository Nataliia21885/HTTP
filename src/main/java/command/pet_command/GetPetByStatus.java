package command.pet_command;

import command.Command;
import models.PetStatus;
import service.PetService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class GetPetByStatus implements Command {
    private final View view;
    public static final String GET_PET_BY_STATUS = "get_pet_by_status";
    private final PetService petService;
    private final URI uri;

    public GetPetByStatus(View view, PetService petService, URI uri) {
        this.view = view;
        this.petService = petService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_PET_BY_STATUS);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        String statusOfPet;
        view.write("Please, choose one of the pet's status : available, pending, sold");
        while (true) {
            String status = view.read();
            if(status.equals(PetStatus.AVAILABLE.getName())) {
                statusOfPet = PetStatus.AVAILABLE.getName();
                break;
            } else if(status.equals(PetStatus.PENDING.getName())) {
                statusOfPet = PetStatus.PENDING.getName();
                break;
            } else if(status.equals(PetStatus.SOLD.getName())) {
                statusOfPet = PetStatus.SOLD.getName();
                break;
            } else {
                System.out.println("Please, choose correct status from following list");
            }
        }
        System.out.println(petService.getPetByStatus(uri, statusOfPet));
    }
}
