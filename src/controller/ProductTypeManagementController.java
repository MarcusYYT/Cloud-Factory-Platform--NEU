package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-23 2:42
 */
public class ProductTypeManagementController implements Initializable {
    @FXML
    private JFXListView<Type> list;

    @FXML
    private Label title;

    @FXML
    private JFXTextField newTypeField;

    @FXML
    private JFXButton configureButton;

    @FXML
    void delTypeHandled(ActionEvent event) {
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Type selectedType = list.getSelectionModel().getSelectedItem();
            Alert delWarning = new Alert(Alert.AlertType.CONFIRMATION,"确定删除" + selectedType + "吗？");
            delWarning.setHeaderText("删除确认");
            delWarning.setTitle("稍等下。。");
            delWarning.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    list.getItems().remove(selectedType);
                    ProductType.getInstance().removeType(selectedType);
                    initialize(null, null);
                }
            });
        } else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一项");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个被选中要删除");
            nullWarning.show();
        }
    }

    @FXML
    void newTypeHandled(ActionEvent event) {
        newTypeField.setVisible(true);
        configureButton.setVisible(true);

    }

    @FXML
    void configureHandled(ActionEvent event) {
        if (newTypeField.getText().equals("")) return;
        if (title.getText().equals("设备类别管理")) {
            ProductType.getInstance().addType(newTypeField.getText());
        } else if (title.getText().equals("产品类别管理")) {
            ProductType.getInstance().addType(newTypeField.getText());
        }
        initialize(null, null);
        newTypeField.setText("");
        newTypeField.setVisible(false);
        configureButton.setVisible(false);
    }

    private ObservableList<Type> productTypeObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productTypeObservableList.clear();
        List<Type> productTypes = ProductType.getInstance().getTypes();
        for (Type t : productTypes) {
            productTypeObservableList.add(t);
        }
        title.setText("设备类别管理");
        list.setItems(productTypeObservableList);
    }
}
