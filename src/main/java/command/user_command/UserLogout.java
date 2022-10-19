package command.user_command;

import command.Command;
import service.UserService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class UserLogout implements Command {
    private final View view;
    public static final String USER_LOGOUT = "user_logout";
    private final UserService userService;
    private final URI uri;

    public UserLogout(View view, UserService userService, URI uri) {
        this.view = view;
        this.userService = userService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(USER_LOGOUT);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        System.out.println(userService.getUserLogout(uri));
    }
}
