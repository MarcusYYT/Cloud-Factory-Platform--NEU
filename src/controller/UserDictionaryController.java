package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Device;
import entity.DeviceType;
import entity.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.IdGenerator;
import view.ViewManager;

import javax.sound.sampled.Line;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserDictionaryController implements Initializable {
    @FXML
    private JFXTextField CodeTextField;

    @FXML
    private TableView<Device> table;

    @FXML
    private TableColumn<Device,String> IDcol;

    @FXML
    private TableColumn<Device,String> CodeCol;

    @FXML
    private TableColumn<Device, String> ItemCol;

    @FXML
    private TableColumn<Device,String> NameCol;

    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private TableColumn<Device,String> TypeCol;

    @FXML
    private TableColumn<Device, String> ValCol;

    @FXML
    private JFXButton CreateButton;

    @FXML
    private AnchorPane rootLayOut;

    @FXML
    private JFXButton ChangeButton;

    @FXML
    private JFXComboBox<String> KeyWordBox;

    @FXML
    private JFXComboBox<String> InfoComBox;


    private DataDictionaryController dataDictionaryController;
    private DeviceType deviceType;
    private String type;


    @FXML
    public void OpenCreatePage(ActionEvent event){
        DeviceEditController controller = (DeviceEditController) ViewManager.newWindow("DeviceEdit.fxml");
        controller.setSecondController(this);
    }

    @FXML
    void SelectCode(ActionEvent event){

    }
    private ObservableList<Device> deviceObservableList = FXCollections.observableArrayList();
    private ObservableList<String> KeyList = FXCollections.observableArrayList();
    private ObservableList<String> InfoList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KeyList.clear();
        KeyList.add("--请选择关键词--");
        KeyList.add("设备状态");
        KeyList.add("设备类型");
        KeyList.add("使用状态");
        KeyWordBox.setItems(KeyList);
        showInitialPage();
    }

    public void showInitialPage(){
        deviceObservableList.clear();
        List<Device> devices = DeviceManager.getInstance().getDevices();
        for (Device d : devices) {
            deviceObservableList.add(d);
            System.out.println(deviceObservableList.size());
        }
        table.setItems(deviceObservableList);
        IDcol.setCellValueFactory(new PropertyValueFactory<Device,String>("id"));
        CodeCol.setCellValueFactory(new PropertyValueFactory<Device, String>("id"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<Device, String>("type"));
        ValCol.setCellValueFactory(new PropertyValueFactory<Device, String>("rentStatus"));
        NameCol.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
        ItemCol.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));

    }
    public void setParentController(DataDictionaryController controller){
        dataDictionaryController = controller;
    }

    public void CreatePage(javafx.event.ActionEvent actionEvent) {
        DeviceEditController controller = (DeviceEditController) ViewManager.newWindow("DeviceEdit.fxml");
        controller.setSecondController(this);
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
    }

    public void EditPage(javafx.event.ActionEvent actionEvent) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Device selectedDevice = table.getSelectionModel().getSelectedItem();
            DeviceEditController controller = (DeviceEditController) ViewManager.newWindow("DeviceEdit.fxml");
            controller.setDevice(selectedDevice);
            controller.setSecondController(this);
        }else {
            Alert nullWarning = new Alert(Alert.AlertType.WARNING, "请选中表格中一个设备");
            nullWarning.setTitle("提示：未选中任何项哦");
            nullWarning.setHeaderText("没有一个设备被选中要编辑");
            nullWarning.show();
        }
    }

    /**
     * 顺序查找功能
     * @param actionEvent
     */
    @FXML
    public void SelectCode(javafx.event.ActionEvent actionEvent) {
        type = KeyWordBox.getSelectionModel().getSelectedItem().toString();
        List<Type> typeList = DeviceType.getInstance().getTypes();
        CodeTextField.setText("Key"+ IdGenerator.getCode());
        if(type == "设备类型"){
            InfoList.clear();
            for(Type t:typeList){
                InfoList.add(t.toString());
            }
            InfoComBox.setItems(InfoList);
        }
        if(type == "设备状态"){
            InfoList.clear();
            InfoList.add("已被租用");
            InfoList.add("未被租用");
            InfoList.add("工厂设备");
            InfoComBox.setItems(InfoList);
        }
        if(type == "使用状态"){
            InfoList.clear();
            InfoList.add("已关闭");
            InfoList.add("闲置中");
            InfoComBox.setItems(InfoList);
        }
    }

    @FXML
    private void SelectDetail(javafx.event.ActionEvent event){
        String detail = InfoComBox.getSelectionModel().getSelectedItem().toString();

        deviceObservableList.clear();
        List<Device> devices = DeviceManager.getInstance().getDevices();

        for (Device d : devices) {
            if(type == "设备状态"){
                if(d.getRentStatus().equals(detail)){
                    deviceObservableList.add(d);
                }
            }
            if(type == "设备类型"){
                if(d.getType().equals(detail)){
                    deviceObservableList.add(d);
                    System.out.println("符合条件的设备有"+d.getType()+deviceObservableList.size());
                }
            }
            if(type == "使用状态"){
                if(d.getStatus().equals(detail)){
                    deviceObservableList.add(d);
                }
            }
        }
        table.setItems(deviceObservableList);
        IDcol.setCellValueFactory(new PropertyValueFactory<Device,String>("id"));
        CodeCol.setCellValueFactory(new PropertyValueFactory<Device, String>("id"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<Device, String>("type"));
        ValCol.setCellValueFactory(new PropertyValueFactory<Device, String>("RentStatus"));
        NameCol.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
        ItemCol.setCellValueFactory(new PropertyValueFactory<Device, String>("name"));

        KeyWordBox.setItems(KeyList);
    }
}

