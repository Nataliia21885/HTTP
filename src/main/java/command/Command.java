package command;

import java.io.IOException;

public interface Command {

    boolean canExecute(String input);

    void execute() throws IOException, InterruptedException;
}
