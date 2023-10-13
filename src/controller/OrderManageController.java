package controller;

import com.jfoenix.controls.JFXButton;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.Sort;
import view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class OrderManageController implements Initializable {
    @FXML
    private TableView<Order> table;

    @FXML
    private TableColumn<Order, String> idCol;

    @FXML
    private TableColumn<Order, String> nameCol;

    @FXML
    private TableColumn<Order, String> proCol;

    @FXML
    private TableColumn<Order, String> numCol;

    @FXML
    private TableColumn<Order, Integer> priceCol;

    @FXML
    private TableColumn<Order, String> payDateCol;

    @FXML
    private TableColumn<Order, String> ddlCol;

    @FXML
    private TableColumn<Order, String> receiverCol;


    @FXML
    private TableColumn<Order, String> stateCol;


    @FXML
    private JFXButton newOrderBT;

    @FXML
    private JFXButton editOrderBT;

    @FXML
    private JFXButton delOrderBT;

    @FXML
    private JFXButton tenderWinButton;


    @FXML
    void delOrder(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Order selectedDevice = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION, "确定删除" + selectedDevice.getName() + "吗？");
            delWarning.setHeaderText("删除确认");
            delWarning.setTitle("稍等下。。");
            delWarning.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedDevice);
                    OrderManager.getInstance().delOrder(selectedDevice);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要删除");
            nullWarning.show();
        }
    }

    @FXML
    void modifyOrder(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Order selectedOrder = table.getSelectionModel().getSelectedItem();
            OrderEditController controller = (OrderEditController) ViewManager.newWindow("OrderEdit.fxml");
            controller.setOrder(selectedOrder);
            controller.setParentController(this);
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要编辑");
            nullWarning.show();
        }
    }

    @FXML
    void newOrder(ActionEvent event) {
        OrderEditController controller = (OrderEditController) ViewManager.newWindow("OrderEdit.fxml");
        controller.setParentController(this);
    }

    @FXML
    void tenderWinHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Order selectedOrder = table.getSelectionModel().getSelectedItem();
            if (selectedOrder.getOrderState().equals("已投标")) {
                String id = selectedOrder.getId();
//                for (Order o : OrderManager.getInstance().getOrderList()) {
//                    if (o.getId().equals(selectedOrder.getId())) OrderManager.getInstance().delOrder(o);
//                }
                OrderManager.getInstance().getOrderList().removeIf(e->e.getId().equals(selectedOrder.getId()));
                selectedOrder.setOrderState("已中标");
                OrderManager.getInstance().addOrder(selectedOrder);
                Alert alert = new Alert(Alert.AlertType.WARNING, "该订单已中标。该订单其它投标项已作废。");
                alert.setHeaderText("成功中标");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "该订单还未投标或已中标。");
                alert.setHeaderText("中标失败");
                alert.show();
            }
        }
        initialize(null, null);
    }

    private ObservableList<Order> orderObservableList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderObservableList.clear();
        Sort.selectSort(OrderManager.getInstance().getOrderList());
        List<Order> orders = OrderManager.getInstance().getOrderList();
        for (Order o : orders) {
            orderObservableList.add(o);
        }


        table.setItems(orderObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
        proCol.setCellValueFactory(new PropertyValueFactory<Order, String>("product"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("price"));
        numCol.setCellValueFactory(new PropertyValueFactory<Order, String>("num"));
        payDateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("payDate"));
        ddlCol.setCellValueFactory(new PropertyValueFactory<Order, String>("dueDate"));
        receiverCol.setCellValueFactory(new PropertyValueFactory<Order, String>("receiver"));
        stateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderState"));
    }
}
