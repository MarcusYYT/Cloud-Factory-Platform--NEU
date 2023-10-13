package controller;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-21 23:34
 */
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ArrangeOrderListController implements Initializable {

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> nameCol;

    @FXML
    private TableColumn<Order, String> startTimeCol;

    @FXML
    private TableColumn<Order, String> endTimeCol;

    @FXML
    private TableColumn<Order, Integer> priceCol;



    private ObservableList<Order> orderObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderObservableList.clear();
        List<Order> orders = OrderManager.getInstance().getOrderList();
        orderObservableList.addAll(orders);
        table.setItems(orderObservableList);
        nameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<Order, String>("dueDate"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<Order, String>("deliveryDate"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("price"));
    }
}
