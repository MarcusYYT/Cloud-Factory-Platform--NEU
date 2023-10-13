package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.DeviceType;
import entity.Dictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.ViewManager;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;

public class DataDictionaryController implements Initializable {
    private DictionaryManage dictionaryManage = new DictionaryManage();

    @FXML
    private AnchorPane rootLayOut;

    @FXML
    private TableView<Dictionary> table;

    @FXML
    private JFXButton DeviceDetail;

    @FXML
    private TableColumn<Dictionary,String> IDcol;

    @FXML
    private TableColumn<Dictionary,String> codeCol;

    @FXML
    private TableColumn<Dictionary,String> typeCol;

    @FXML
    private TableColumn<Dictionary,String> nameCol;

    @FXML
    private TableColumn<Dictionary,String> numCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Dictionary> list = dictionaryManage.Initialized();
        ObservableList<Dictionary> dicObservableList = FXCollections.observableList(list);
        table.setItems(dicObservableList);

        IDcol.setCellValueFactory(new PropertyValueFactory<Dictionary,String>("ID"));
        codeCol.setCellValueFactory(new PropertyValueFactory<Dictionary,String>("Code"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Dictionary,String>("Type"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Dictionary,String>("Name"));
        numCol.setCellValueFactory(new PropertyValueFactory<Dictionary,String >("Number"));
    }

    public void OpenDevice(javafx.event.ActionEvent actionEvent) {
        UserDictionaryController userDictionaryController = (UserDictionaryController)ViewManager.newWindow("UserDictionary.fxml");
        userDictionaryController.setParentController(this);
    }
}
