package command.order_command;

import command.Command;
import service.OrderService;
import view.View;

import java.io.IOException;
import java.net.URI;

public class GetOrderByStatus implements Command {

    private final View view;
    public static final String GET_ORDER_BY_STATUS = "get_order_by_status";
    private final OrderService orderService;
    private final URI uri;

    public GetOrderByStatus(View view, OrderService orderService, URI uri) {
        this.view = view;
        this.orderService = orderService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_ORDER_BY_STATUS);
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        orderService.getOrderByStatus();
    }
}
