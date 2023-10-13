package controller;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 1:09
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReceiveOrderController implements Initializable {

    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> idCol;

    @FXML
    private TableColumn<Order, String> nameCol;

    @FXML
    private TableColumn<Order, String> amountCol;

    @FXML
    private TableColumn<Order, String> deliveryDateCol;

    @FXML
    private TableColumn<Order, String> dueDateCol;

    @FXML
    private TableColumn<Order, String> phoneCol;




    @FXML
    private JFXButton tenderButton;

    @FXML
    private JFXTextField priceTextField;

    @FXML
    void tenderHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Order selectedOrder = table.getSelectionModel().getSelectedItem();
            String priceStr = priceTextField.getText();
            int price;
            try {
                price = Integer.parseInt(priceStr);
                if (price == 0) throw new NumberFormatException();
            } catch (NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
                alert.setHeaderText("投标价格输入有误");
                alert.show();
                return;
            }
            selectedOrder.setPrice(price);
            selectedOrder.setOrderState("已投标");
            OrderManager.getInstance().addOrder(selectedOrder);
            priceTextField.clear();
            Alert alert = new Alert(Alert.AlertType.WARNING, "成功投标");
            alert.setHeaderText("该订单已投标");
            alert.show();
            initialize(null, null);
        }
    }

    private ObservableList<Order> orderObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderObservableList.clear();
        List<Order> orders = OrderManager.getInstance().getOrderList();
        for (Order o : orders) {
            if (!o.getOrderState().equals("已投标") && !o.getOrderState().equals("已中标"))
            orderObservableList.add(o);
        }
        table.setItems(orderObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Order, String>("num"));
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("payDate"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("dueDate"));
    }
}
