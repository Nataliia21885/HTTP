package command.user_command;

import command.Command;
import service.UserService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class DeleteUser implements Command {
    private final View view;
    public static final String DELETE_USER = "delete_user";
    private final UserService userService;
    private final URI uri;

    public DeleteUser(View view, UserService userService, URI uri) {
        this.view = view;
        this.userService = userService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_USER);
    }

    @Override
    public void execute() throws IOException, InterruptedException {

        String userName;
        view.write("Please, enter user name");
        userName = view.read();
        userService.deleteUser(uri, userName);
    }
}
