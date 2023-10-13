package controller;

import com.jfoenix.controls.JFXTreeView;
import entity.Device;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.FileOperator;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeviceTreeViewController extends Application {

    private DeviceManagementController deviceManagementController;
    public void setParentController(DeviceManagementController controller) {
        deviceManagementController = controller;
    }

    @Override
    public void start(Stage stage) {
        List<Device> deviceList = FileOperator.loadData("Devices.json", Device.class);
        TreeItem<String> rootNode =
                new TreeItem<String>("设备管理");
        rootNode.setExpanded(true);
        for(Device de:deviceList){
            //遍历设备，给每一个设备创建一个TreeItem
            TreeItem<String> devLeaf = new TreeItem<>(de.getStatus());
            TreeItem<String> stateLeaf = new TreeItem<>(de.getRentStatus());
            TreeItem<String> idLeaf = new TreeItem<>(de.getId());
            boolean found = false;
            //遍历根节点下的单位结点
            for(TreeItem<String> devNode:rootNode.getChildren()){
                //查看该设备所属的类型结点是否存在
                if(devNode.getValue().contentEquals(de.getName())){
                    //存在则加入叶子结点-设备的状态
                    //devNode.getChildren().add(devLeaf);
                    found = true;
                    //遍历一级叶子结点下的所有结点
                    boolean found1 = false;
                    for(TreeItem<String> stateNode:devNode.getChildren()){
                        //查看该设备所属的租用状态结点是否存在
                        if(stateNode.getValue().contentEquals(de.getStatus())){
                            //如果存在则加入状态
                            stateNode.getChildren().add(stateLeaf);
                            found1 = true;
                            boolean found2 = false;
                            for(TreeItem<String> rentNode:stateNode.getChildren()){
                                if(rentNode.getValue().contentEquals(de.getRentStatus())){
                                    rentNode.getChildren().add(idLeaf);
                                    found2 = true;
                                    boolean found3 = false;
                                    for(TreeItem<String> idNode:rentNode.getChildren()){
                                        if(rentNode.getValue().contentEquals(de.getId())){
                                            idNode.getChildren().add(idLeaf);
                                        }
                                    }
                                }
                            }
                            if(!found2) {
                                TreeItem<String> rentNode = new TreeItem<>(
                                        de.getId(),
                                        new ImageView()
                                );
                                stateNode.getChildren().add(rentNode);
                                rentNode.getChildren().add(idLeaf);
                            }
                        }
                    }
                    if(!found1){
                        TreeItem<String> stateNode = new TreeItem<String>(
                            de.getRentStatus(),
                            new ImageView()
                            );
                        devNode.getChildren().add(stateNode);
                        stateNode.getChildren().add(stateLeaf);
                    }
                }
            }
            if(!found){
                TreeItem<String> devNode = new TreeItem<String>(
                        de.getName(),
                        new ImageView()
                );
                rootNode.getChildren().add(devNode);
                devNode.getChildren().add(devLeaf);
            }
        }
        stage.setTitle("Tree View Sample");
        VBox box = new VBox();
        final Scene scene = new Scene(box, 400, 300);
        scene.setFill(Color.LIGHTGRAY);

        TreeView<String> treeView = new TreeView<>(rootNode);

        box.getChildren().add(treeView);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
