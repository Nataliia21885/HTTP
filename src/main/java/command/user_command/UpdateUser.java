package command.user_command;

import command.Command;
import models.User;
import service.UserService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class UpdateUser implements Command {
    private final View view;
    public static final String UPDATE_USER = "update_user";
    private final UserService userService;
    private final URI uri;

    public UpdateUser(View view, UserService userService, URI uri) {
        this.view = view;
        this.userService = userService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_USER);
    }

    @Override
    public void execute() throws IOException, InterruptedException {

        User user = new User();

        view.write("Please, enter user id");
        while (true) {
            try {
                user.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        view.write("Please, enter user name");
        user.setUsername(view.read());

        view.write("Please, enter user first name");
        user.setFirstName(view.read());

        view.write("Please, enter user last name");
        user.setLastName(view.read());

        view.write("Please, enter user email");
        user.setEmail(view.read());

        view.write("Please, enter user password");
        user.setPassword(view.read());

        view.write("Please, enter user phone number");
        user.setPhone(view.read());

        view.write("Please, enter user status");
        while (true) {
            try {
                user.setUserStatus(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        System.out.println(userService.putUser(uri, user));
    }
}
