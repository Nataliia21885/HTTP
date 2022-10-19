package command.user_command;

import command.Command;
import service.UserService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class GetUserByName implements Command {
    private final View view;
    public static final String GET_USER_BY_NAME = "get_user_by_name";
    private final UserService userService;
    private final URI uri;

    public GetUserByName(View view, UserService userService, URI uri) {
        this.view = view;
        this.userService = userService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_USER_BY_NAME);
    }

    @Override
    public void execute() throws IOException, InterruptedException {

        String userName;
        view.write("Please, enter user name");
        userName = view.read();
        System.out.println(userService.getUserByUserName(uri, userName));
    }
}
