package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Product;
import entity.ProductType;
import entity.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.IdGenerator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 20:50
 */
public class ProductEditController implements Initializable {
    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXTextField specField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXComboBox<Type> typeComboBox;

    @FXML
    private JFXTextField idField;

    private Product inProduct;
    private ProductInfoController productInfoController;

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void saveHandled(ActionEvent event) {
        String id = idField.getText();
        String name = nameField.getText();
        String spec = specField.getText();
        String description = descriptionField.getText();

        if (name.equals("") || spec.equals("") || description.equals("") ) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
            alert.setHeaderText("信息输入不能有空值");
            alert.show();
            return;
        } else if (typeComboBox.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
            alert.setHeaderText("产品类别未选择");
            alert.show();
            return;
        }
        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        if (inProduct != null) {
            ProductManager.getInstance().delProduct(inProduct);
        }
        ProductManager.getInstance().addProduct(new Product(id, name, type, spec, description));
        productInfoController.initialize(null,null);
        Alert info = new Alert(Alert.AlertType.INFORMATION,"新的产品信息保存成功");
        info.showAndWait();
        productInfoController.initialize(null,null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    public void setParentController(ProductInfoController controller) {
        productInfoController = controller;
    }

    public void setProduct(Product product) {
        inProduct = product;
        idField.setText(product.getId());
        nameField.setText(product.getName());
        specField.setText(product.getSpec());
        descriptionField.setText(product.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Type> typeObservableList = FXCollections.observableArrayList();
        idField.setText("PRO" + IdGenerator.getCode());
        List<Type> types = ProductType.getInstance().getTypes();
        for (Type t : types) {
            typeObservableList.add(t);
        }
        typeComboBox.setItems(typeObservableList);
    }
}
