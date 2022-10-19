import command.Command;
import command.ExitCommand;
import command.HelpCommand;
import command.order_command.CreateOrder;
import command.order_command.DeleteOrder;
import command.order_command.GetOrderById;
import command.order_command.GetOrderByStatus;
import command.pet_command.*;
import command.user_command.*;
import service.OrderService;
import service.PetService;
import service.UserService;
import view.Console;
import view.Controller;
import view.View;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String ORDERS_LINK = "https://petstore.swagger.io/v2/store/order";
    private static final String PETS_LINK = "https://petstore.swagger.io/v2/pet";
    private static final String USERS_LINK = "https://petstore.swagger.io/v2/user";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        OrderService orderService = new OrderService();
        PetService petService = new PetService();
        UserService userService = new UserService();

        List<Command> commandList = new ArrayList<>();
        commandList.add(new HelpCommand(view));
        commandList.add(new ExitCommand(view));

        commandList.add(new CreateOrder(view, orderService, URI.create(ORDERS_LINK)));
        commandList.add(new DeleteOrder(view, orderService, URI.create(ORDERS_LINK)));
        commandList.add(new GetOrderById(view, orderService, URI.create(ORDERS_LINK)));
        commandList.add(new GetOrderByStatus(view, orderService, URI.create(ORDERS_LINK)));

        commandList.add(new CreatePet(view, petService, URI.create(PETS_LINK)));
        commandList.add(new DeletePet(view, petService, URI.create(PETS_LINK)));
        commandList.add(new GetPetById(view, petService, URI.create(PETS_LINK)));
        commandList.add(new GetPetByStatus(view, petService, URI.create(PETS_LINK)));
        commandList.add(new UpdatePet(view, petService, URI.create(PETS_LINK)));
        commandList.add(new UpdatePetWithData(view, petService, URI.create(PETS_LINK)));
        commandList.add(new UploadPetImage(view, petService, URI.create(PETS_LINK)));

        commandList.add(new ArrayOfUsers(view, userService, URI.create(USERS_LINK)));
        commandList.add(new CreateUser(view, userService, URI.create(USERS_LINK)));
        commandList.add(new DeleteUser(view, userService, URI.create(USERS_LINK)));
        commandList.add(new GetUserByName(view, userService, URI.create(USERS_LINK)));
        commandList.add(new ListOfUsers(view, userService, URI.create(USERS_LINK)));
        commandList.add(new UpdateUser(view, userService, URI.create(USERS_LINK)));
        commandList.add(new UserLogin(view, userService, URI.create(USERS_LINK)));
        commandList.add(new UserLogout(view, userService, URI.create(USERS_LINK)));

        Controller controller = new Controller(view, commandList);
        controller.run();
    }
}
