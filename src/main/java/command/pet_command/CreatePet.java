package command.pet_command;

import command.Command;
import models.*;
import service.PetService;
import view.View;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class CreatePet implements Command {
    private final View view;
    public static final String CREATE_NEW_PET = "create_pet";
    private final PetService petService;
    private final URI uri;

    public CreatePet(View view, PetService petService, URI uri) {
        this.view = view;
        this.petService = petService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_NEW_PET);
    }

    @Override
    public void execute() throws IOException, InterruptedException {

        Pet pet = new Pet();
        Category category = new Category();
        List<String> photoUrls = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        Integer tagId;

        view.write("Please, enter pet id");
        while (true) {
            try {
                pet.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        view.write("Please, enter category id");
        while (true) {
            try {
                category.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        view.write("Please, enter category name");
        category.setName(view.read());
        pet.setCategory(category);

        view.write("Please, enter pet name");
        pet.setName(view.read());

        view.write("Please, enter photo url");
        photoUrls.add(view.read());
        while (true) {
            view.write("Please, enter add, if you want to add more photo; else - write break");
            String temp = view.read();
            if (temp.equals("add")) {
                view.write("Please, enter photo url");
                photoUrls.add(view.read());
            } else if (temp.equals("break")) {
                break;
            } else {
                System.out.println("Please, enter only add or break!");
            }
        }
        pet.setPhotoUrls(photoUrls);

        view.write("Please, enter tag id");
        while (true) {
            try {
                tagId = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        view.write("Please, enter tag name");
        tags.add(new Tag(tagId, view.read()));
        while (true) {
            view.write("Please, enter add, if you want to add more tags; else - write break");
            String temp = view.read();
            if (temp.equals("add")) {
                while (true) {
                    view.write("Please, enter tag id");
                    try {
                        tagId = Integer.parseInt(view.read());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.printf("Please, use only digits!");
                    }
                }
                view.write("Please, enter tag name");
                tags.add(new Tag(tagId, view.read()));
            } else if (temp.equals("break")) {
                break;
            } else {
                System.out.println("Please, enter only add or break!");
            }
        }
        pet.setTags(tags);

        view.write("Please, choose one of the pet's status : available, pending, sold");
        while (true) {
            String status = view.read();
            if (status.equals(PetStatus.AVAILABLE.getName())) {
                pet.setStatus(PetStatus.AVAILABLE.getName());
                break;
            } else if (status.equals(PetStatus.PENDING.getName())) {
                pet.setStatus(PetStatus.PENDING.getName());
                break;
            } else if (status.equals(PetStatus.SOLD.getName())) {
                pet.setStatus(PetStatus.SOLD.getName());
                break;
            } else {
                System.out.println("Please, choose correct status from following list");
            }
        }

        System.out.println(petService.postPet(uri, pet));
    }
}
