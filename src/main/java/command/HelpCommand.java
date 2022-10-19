package command;

import command.order_command.CreateOrder;
import command.order_command.DeleteOrder;
import command.order_command.GetOrderById;
import command.order_command.GetOrderByStatus;
import command.pet_command.*;
import command.user_command.*;
import view.View;

public class HelpCommand implements Command{

    public static final String HELP_COMMAND = "help";
    private final View view;

    public HelpCommand(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP_COMMAND);
    }

    @Override
    public void execute() {
        view.write(String.format("Please, enter %s to create order", CreateOrder.CREATE_NEW_ORDER));
        view.write(String.format("Please, enter %s to delete order", DeleteOrder.DELETE_ORDER));
        view.write(String.format("Please, enter %s to get order by id", GetOrderById.GET_ORDER_BY_ID));
        view.write(String.format("Please, enter %s to get order by status", GetOrderByStatus.GET_ORDER_BY_STATUS));

        view.write(String.format("Please, enter %s to create pet", CreatePet.CREATE_NEW_PET));
        view.write(String.format("Please, enter %s to delete pet", DeletePet.DELETE_PET));
        view.write(String.format("Please, enter %s to get pet by id", GetPetById.GET_PET_BY_ID));
        view.write(String.format("Please, enter %s to get pet by status", GetPetByStatus.GET_PET_BY_STATUS));
        view.write(String.format("Please, enter %s to update pet", UpdatePet.UPDATE_PET));
        view.write(String.format("Please, enter %s to update pet with data", UpdatePetWithData.UPDATE_PET_WITH_DATA));
        view.write(String.format("Please, enter %s to upload pet image", UploadPetImage.UPLOAD_PET_IMAGE));

        view.write(String.format("Please, enter %s to see array of users", ArrayOfUsers.ARRAY_OF_USER));
        view.write(String.format("Please, enter %s to create user", CreateUser.CREATE_NEW_USER));
        view.write(String.format("Please, enter %s to delete user", DeleteUser.DELETE_USER));
        view.write(String.format("Please, enter %s to get user by name", GetUserByName.GET_USER_BY_NAME));
        view.write(String.format("Please, enter %s to see list of users", ListOfUsers.LIST_OF_USER));
        view.write(String.format("Please, enter %s to update user", UpdateUser.UPDATE_USER));
        view.write(String.format("Please, enter %s to log user into the system", UserLogin.USER_LOGIN));
        view.write(String.format("Please, enter %s to log out current logged in user session", UserLogout.USER_LOGOUT));

        view.write(String.format("Please, enter %s to exit", ExitCommand.EXIT_COMMAND));
    }
}
