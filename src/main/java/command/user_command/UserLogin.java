package command.user_command;

import command.Command;
import service.UserService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class UserLogin implements Command {
    private final View view;
    public static final String USER_LOGIN = "user_login";
    private final UserService userService;
    private final URI uri;

    public UserLogin(View view, UserService userService, URI uri) {
        this.view = view;
        this.userService = userService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(USER_LOGIN);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        String userName;
        String password;

        view.write("Please, enter user name");
        userName = view.read();
        view.write("Please, enter user password");
        password = view.read();

        System.out.println(userService.getUserLogin(uri, userName, password));
    }
}
