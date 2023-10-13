package entity;

import util.IdGenerator;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-21 9:14
 */
public class Order {
    private final String id;
    private String payDate;
    private String dueDate;
    private String receiver;
    private String contact;
    private String name;
    private String orderState;
    private int price;
    public String product;
    public String num;



    public Order(String id, String name, String product, String num,String payDate, String dueDate, String receiver,String orderState){
        this.id = id;
        this.name = name;
        this.product = product;
        this.num = num;
        this.payDate = payDate;
        this.dueDate = dueDate;
        this.receiver = receiver;
        this.orderState = orderState;
    }

    public String getOrderState() {
        return orderState;
    }
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
    public String getPayDate() {
        return payDate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public String getId() { return id; }
    public String getReceiver() { return receiver;  }
    public String getContact() { return contact; }
    public String getProduct() { return product; }
    public String getNum() { return num; }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
    public String getName() { return name; }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getSortIndex1() {
        if (orderState.equals("已投标")) return 1;
        else if (orderState.equals("已中标")) return 2;
        else if (orderState.equals("未投标")) return 3;
        else return 4;
    }

    public int getSortIndex2() {
        return Integer.parseInt(id.substring(10));
    }

    public int getSortIndex3() {
        return price;
    }
}
