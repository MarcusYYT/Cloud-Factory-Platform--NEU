package controller;

import entity.Device;
import entity.Order;
import util.FileOperator;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;
    private static OrderManager singletonInstance;

    private OrderManager() {
        orders = FileOperator.loadData("Orders.json", Order.class);
    }

    public static OrderManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new OrderManager();
        }
        return singletonInstance;
    }


    public void addOrder(Order order) {
        orders.add(order);
        System.out.println(orders.size());
        FileOperator.writeData(order, "Orders.json");
    }


    public boolean delOrder(Order order) {
        if (orders.indexOf(order) == -1) return false;
        orders.remove(order);
        FileOperator.writeData(orders, "Orders.json");
        return true;
    }


    public List<Order> getOrderList() {
        return orders;
    }
}
