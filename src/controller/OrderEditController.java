package controller;
import com.jfoenix.controls.JFXDatePicker;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.IdGenerator;

public class OrderEditController implements Initializable{

        @FXML
        private FontAwesomeIconView exitButton;

        @FXML
        private JFXTextField numField;

        @FXML
        private JFXTextField nameField;

        @FXML
        private JFXComboBox<Type> typeComboBox;

        @FXML
        private JFXTextField idField;

        @FXML
        private JFXTextField descriptionField;

        @FXML
        private JFXTextField personField;

        @FXML
        private JFXDatePicker date;

        @FXML
        private JFXDatePicker ddl;

        private OrderManageController orderManageController;
        private Object OrderManageController;
        private Order inorder;

        public OrderEditController() {
        }

        @FXML
        void saveHandled(ActionEvent event) {
                String id = idField.getText();
                String name = nameField.getText();
                String num = numField.getText();
                String overDate = date.getValue().toString();
                String deadLineDate = ddl.getValue().toString();
                String receiver = personField.getText();
                String product = typeComboBox.getSelectionModel().getSelectedItem().toString();

                if(id.equals("")||name.equals("")||num.equals("")||overDate.equals("")||deadLineDate.equals("")||receiver.equals("")){
                        Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
                        alert.setHeaderText("信息输入不能有空值");
                        alert.show();
                        return;
                }else if(typeComboBox.getSelectionModel().getSelectedItem() == null){
                        Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
                        alert.setHeaderText("产品类型未选择");
                        alert.show();
                        return;
                }

                Order order = new Order(id,name,product,num,overDate,deadLineDate,receiver,"未投标");
                OrderManager.getInstance().addOrder(order);
                Alert regSuccess = new Alert(Alert.AlertType.INFORMATION, "订单已保存成功");
                regSuccess.setHeaderText("注册成功");
                regSuccess.showAndWait();
                if (orderManageController != null) {
                        orderManageController.initialize(null, null);
                }
                Stage currentStage = (Stage) exitButton.getScene().getWindow();
                currentStage.close();

        }

        public void setParentController(OrderManageController controller) {
                orderManageController = controller;
        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
                idField.setText("ORD" + IdGenerator.getCode());
                List<Type> types = ProductType.getInstance().getTypes();
                for (Type t : types) {
                        typeObservableList.add(t);
                }
                typeComboBox.setItems(typeObservableList);
        }

        public void close(javafx.scene.input.MouseEvent mouseEvent) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

        public void setOrder(Order order) {
                inorder = order;
                idField.setText(order.getId());
                nameField.setText(order.getName());
                typeComboBox.setPromptText(order.getProduct());
                numField.setText(order.getNum());
                personField.setText(order.getReceiver());
        }

}
