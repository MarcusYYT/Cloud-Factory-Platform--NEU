package controller;

import entity.Product;
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
import view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 19:58
 */
public class ProductInfoController implements Initializable {
    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, String> idCol;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TableColumn<Product, String> typeCol;

    @FXML
    private TableColumn<Product, String> specCol;

    @FXML
    private TableColumn<Product, String> descriptionCol;

    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productObservableList.clear();
        List<Product> products = ProductManager.getInstance().getProducts();
        for (Product p : products) {
            productObservableList.add(p);
        }
        table.setItems(productObservableList);
        idCol.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
        specCol.setCellValueFactory(new PropertyValueFactory<Product, String>("spec"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
    }

    @FXML
    void delHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Product selectedProduct = table.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"确定删除" + selectedProduct.getName() + "吗？");
            delWarning.setHeaderText("删除确认");
            delWarning.setTitle("稍等下。。");
            delWarning.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    table.getItems().remove(selectedProduct);
                    ProductManager.getInstance().delProduct(selectedProduct);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullwarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个产品");
            nullwarning.setTitle("提示：未选中任何项哦");
            nullwarning.setHeaderText("没有一个产品被选中要删除");
            nullwarning.show();
        }

    }

    @FXML
    void editHandled(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Product selectedProduct = table.getSelectionModel().getSelectedItem();
            ProductEditController controller = (ProductEditController) ViewManager.newWindow("ProductEdit.fxml");
            controller.setProduct(selectedProduct);
            controller.setParentController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个产品");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个产品被选中要编辑");
            nullWarning.show();
        }
    }

    @FXML
    void newHandled(ActionEvent event) {
        ProductEditController controller = (ProductEditController) ViewManager.newWindow("ProductEdit.fxml");
        controller.setParentController(this);
    }


}
