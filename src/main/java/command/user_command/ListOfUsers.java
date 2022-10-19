package command.user_command;

import command.Command;
import models.User;
import service.UserService;
import view.View;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ListOfUsers implements Command {
    private final View view;
    public static final String LIST_OF_USER = "list_of_users";
    private final UserService userService;
    private final URI uri;

    public ListOfUsers(View view, UserService userService, URI uri) {
        this.view = view;
        this.userService = userService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LIST_OF_USER);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        List<User> users = new ArrayList<>();

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

        users.add(user);

        while (true) {
            view.write("Do you want to add more users? Please, enter yes or no");
            String addUser = view.read();
            if(addUser.equals("yes")) {

                User additionalUser = new User();

                view.write("Please, enter user id");
                while (true) {
                    try {
                        additionalUser.setId(Integer.parseInt(view.read()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.printf("Please, use only digits!");
                    }
                }

                view.write("Please, enter user name");
                additionalUser.setUsername(view.read());

                view.write("Please, enter user first name");
                additionalUser.setFirstName(view.read());

                view.write("Please, enter user last name");
                additionalUser.setLastName(view.read());

                view.write("Please, enter user email");
                additionalUser.setEmail(view.read());

                view.write("Please, enter user password");
                additionalUser.setPassword(view.read());

                view.write("Please, enter user phone number");
                additionalUser.setPhone(view.read());

                view.write("Please, enter user status");
                while (true) {
                    try {
                        additionalUser.setUserStatus(Integer.parseInt(view.read()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.printf("Please, use only digits!");
                    }
                }

                users.add(additionalUser);

            } else if(addUser.equals("no")) {
                break;
            } else {
                System.out.println("Please, choose answer: yes or no");
            }
        }

        System.out.println(userService.postUserWithList(uri, users));
    }
}
