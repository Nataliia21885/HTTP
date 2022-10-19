package command.order_command;

import command.Command;
import models.Order;
import models.OrderStatus;
import service.OrderService;
import view.View;

import java.io.IOException;
import java.net.URI;
import java.sql.Date;

public class CreateOrder implements Command {
    private final View view;
    public static final String CREATE_NEW_ORDER = "create_order";
    private final OrderService orderService;
    private final URI uri;

    public CreateOrder(View view, OrderService orderService, URI uri) {
        this.view = view;
        this.orderService = orderService;
        this.uri = uri;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CREATE_NEW_ORDER);
    }

    @Override
    public void execute() throws IOException, InterruptedException {

        Order order = new Order();

        view.write("Please, enter order id");
        while (true) {
            try {
                order.setId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        view.write("Please, enter pet id");
        while (true) {
            try {
                order.setPetId(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        view.write("Please, enter quantity");
        while (true) {
            try {
                order.setQuantity(Integer.parseInt(view.read()));
                break;
            } catch (NumberFormatException e) {
                System.out.printf("Please, use only digits!");
            }
        }

        view.write("Please, enter ship date");
        while (true) {
            try {
                order.setShipDate(String.valueOf(Date.valueOf(view.read())));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Please, use format of date: YYYY-MM-DD!");
            }
        }

        view.write("Please, choose one of the order's status : placed, approved, delivered");
        while (true) {
            String status = view.read();
            if (status.equals(OrderStatus.PLACED.getName())) {
                order.setStatus(OrderStatus.PLACED.getName());
                break;
            } else if (status.equals(OrderStatus.APPROVED.getName())) {
                order.setStatus(OrderStatus.APPROVED.getName());
                break;
            } else if (status.equals(OrderStatus.DELIVERED.getName())) {
                order.setStatus(OrderStatus.DELIVERED.getName());
                break;
            } else {
                System.out.println("Please, choose correct status from following list");
            }
        }

        orderService.postOrder(uri, order);

        view.write("Please, enter true, if order has already completed, or false, if it's wrong");
        while (true) {
            String orderCompleted = view.read();
            if (orderCompleted.equals("true")) {
                order.setComplete(true);
                break;
            } else if (orderCompleted.equals("false")) {
                order.setComplete(false);
                break;
            } else {
                System.out.println("Please, enter only true or false!");
            }
        }

        System.out.println(orderService.postOrder(uri, order));
    }
}
