package controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.AbstractUser;
import entity.FactoryAdmin;
import entity.Retailer;
import entity.SuperAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.LinkQueue;

/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 6:26
 */
public class UserEditController {
    @FXML
    private JFXRadioButton retailerButton;

    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private JFXRadioButton factoryAdminButton;

    @FXML
    private JFXRadioButton superAdminButton;

    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXTextField accountField;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField contactField;

    @FXML
    private JFXTextField factoryNameField;

    @FXML
    private JFXTextArea factoryDescription;

    @FXML
    private JFXTextField passwordField;

    private AbstractUser user;
    private UserManagementController userManagementController;
    private LinkQueue linkQueue = new LinkQueue();

    public void setUser(AbstractUser user) {
        this.user = user;
        accountField.setText(user.getAccount());
        passwordField.setText(user.getPassword());
        nameField.setText(user.getName());
        contactField.setText(user.getContact());
        if (user.getClass() == FactoryAdmin.class) {
            retailerButton.setDisable(true);
            superAdminButton.setDisable(true);
            factoryAdminButton.setSelected(true);
            factoryNameField.setText(((FactoryAdmin) user).getFactoryName());
            factoryDescription.setText(((FactoryAdmin) user).getFactoryDescription());
        } else if (user.getClass() == Retailer.class) {
            factoryNameField.setVisible(false);
            factoryDescription.setVisible(false);
            retailerButton.setSelected(true);
            superAdminButton.setDisable(true);
            factoryAdminButton.setDisable(true);
        } else if (user.getClass() == SuperAdmin.class) {
            factoryNameField.setVisible(false);
            factoryDescription.setVisible(false);
            retailerButton.setDisable(true);
            superAdminButton.setSelected(true);
            factoryAdminButton.setDisable(true);
        }
    }

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void factoryAdminButtonHandled(ActionEvent event) {
        factoryNameField.setVisible(true);
        factoryDescription.setVisible(true);
    }

    @FXML
    void retailerButtonHandled(ActionEvent event) {
        factoryNameField.setVisible(false);
        factoryDescription.setVisible(false);
    }
    @FXML
    void superAdminButtonHandled(ActionEvent event) {
        factoryNameField.setVisible(false);
        factoryDescription.setVisible(false);
    }

    @FXML
    void saveHandled(ActionEvent event) {
        String account = accountField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String contact = contactField.getText();
        String factoryName = factoryNameField.getText();
        String factoryDescriptionText = factoryDescription.getText();
        if (account.equals("") && password.equals("")) {
            return;
        } else if (account.equals("") || password.equals("") || name.equals("") || contact.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "核对一下再试试吧");
            alert.setHeaderText("信息输入不能有空值");
            alert.show();
            return;
        }
        UserManager.getInstance().remove(user);
        if (user.getClass() == FactoryAdmin.class) {
            FactoryAdmin FA = new FactoryAdmin(account, password, contact, name, factoryName, factoryDescriptionText);
            linkQueue.insert(FA);
            UserManager.getInstance().addFactoryAdmin(FA);
        } else if (user.getClass() == Retailer.class) {
            Retailer retailer = new Retailer(account, password, contact, name);
            linkQueue.insert(retailer);
            UserManager.getInstance().addRetailer(retailer);
        } else if (user.getClass() == SuperAdmin.class) {
            SuperAdmin superAdmin = new SuperAdmin(account, password, contact, name);
            linkQueue.insert(superAdmin);
            UserManager.getInstance().addSuperAdmin(superAdmin);
        }

        Alert info = new Alert(Alert.AlertType.INFORMATION, "用户信息已成功保存");
        info.showAndWait();
        //发送消息给被修改的用户
        Alert info1 = new Alert(Alert.AlertType.INFORMATION,"已将修改情况通知给用户");
        info1.showAndWait();
        linkQueue.peek();
        userManagementController.initialize(null, null);
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }

    public void setParentController(UserManagementController controller) {
        userManagementController = controller;
    }
}
