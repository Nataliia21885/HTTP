package command.order_command;

import command.Command;
import service.OrderService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class DeleteOrder implements Command {
    private final View view;
    public static final String DELETE_ORDER = "delete_order";
    private final OrderService orderService;
    private final URI uri;

    public DeleteOrder(View view, OrderService orderService, URI uri) {
        this.view = view;
        this.orderService = orderService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_ORDER);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        Integer id;
        view.write("Please, enter order id");
        while (true) {
            try {
                id = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }
        orderService.deleteOrder(uri, id);
    }
}
