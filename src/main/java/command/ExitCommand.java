package command;

import view.View;

public class ExitCommand implements Command{

    public static final String EXIT_COMMAND = "exit";
    public static final String EXIT_MESSAGE = "Bye!";
    private final View view;

    public ExitCommand(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(EXIT_COMMAND);
    }

    @Override
    public void execute() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }
}
