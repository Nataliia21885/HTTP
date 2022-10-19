package command.user_command;

import command.Command;
import models.User;
import service.UserService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class ArrayOfUsers implements Command {
    private final View view;
    public static final String ARRAY_OF_USER = "user_array";
    private final UserService userService;
    private final URI uri;

    public ArrayOfUsers(View view, UserService userService, URI uri) {
        this.view = view;
        this.userService = userService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(ARRAY_OF_USER);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        User[] users;

        view.write("How many users would you like to add? Please, enter the amount of users");
        users = new User[Integer.parseInt(view.read())];

        for (int i = 0; i < users.length; i++){

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
            users[i] = user;
        }

        System.out.println(userService.postUserWithArray(uri, users));
    }
}
