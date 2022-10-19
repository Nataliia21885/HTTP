package view;

import command.Command;
import command.HelpCommand;

import java.io.IOException;
import java.util.List;

public class Controller { private final View view;
    private final List<Command> commandList;

    public Controller(View view, List<Command> commandList) {
        this.view = view;
        this.commandList = commandList;
    }

    public void run() {
        view.write(String.format("Hello, please enter %s to see all command", HelpCommand.HELP_COMMAND));
        try {
            execute();
        } catch (RuntimeException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execute() throws IOException, InterruptedException {
        while (true) {
            String input = view.read();
            boolean isInputCorrect = false;
            for (Command command : commandList) {
                if (command.canExecute(input)) {
                    command.execute();
                    isInputCorrect = true;
                }
            }
            if (!isInputCorrect) {
                view.write(String.format("Command not found. Please enter %s to see all commands", HelpCommand.HELP_COMMAND));
            }
        }
    }
}