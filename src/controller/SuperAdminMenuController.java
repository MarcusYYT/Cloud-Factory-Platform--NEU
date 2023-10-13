package controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Device;
import entity.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.ViewManager;


public class SuperAdminMenuController {

    @FXML
    private BorderPane rootLayout;

    @FXML
    private JFXButton deviceManageButton;

    @FXML
    private JFXButton OrderInfoButton;

    @FXML
    private JFXButton userManagementButton;

    @FXML
    private JFXButton factoryInfoButton;

    @FXML
    private JFXButton productTypeButton;

    @FXML
    private JFXButton productInfoButton;

    @FXML
    private JFXButton showDataDictionary;

    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void showDeviceManagement(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("DeviceManagement.fxml"));
    }

    @FXML
    void showDeviceTypeManagement(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("DeviceTypeManagement.fxml"));
    }

    @FXML
    void showFactoryInfo(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("FactoryInfo.fxml"));
    }

    @FXML
    void showOrderInfo(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("OrderManagement.fxml"));
    }

    @FXML
    void showProductInfo(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("ProductInfo.fxml"));
    }

    @FXML
    void showProductType(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("ProductTypeManagement.fxml"));
    }

    @FXML
    void showUserManagement(ActionEvent event) {

        rootLayout.setCenter(ViewManager.getPane("UserManagement.fxml"));
    }
    @FXML
    void DataDictinaryManangement(ActionEvent event) {
        rootLayout.setCenter(ViewManager.getPane("DataDictionary.fxml"));
    }

}
