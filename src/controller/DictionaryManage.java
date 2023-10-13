package controller;

import entity.Device;
import entity.Dictionary;
import entity.Product;
import entity.Retailer;
import util.FileOperator;

import java.util.ArrayList;
import java.util.List;

public class DictionaryManage {
    List<Dictionary> dictionaryList = new ArrayList<Dictionary>();
    private int Number;

    public DictionaryManage() {
    }
    public int setUserNumber(){
        List<Retailer> list = UserManager.getInstance().getRetailers();
        Number = list.size();
        return Number;
    }

    public int setDeviceNumber(){
        List<Device> list = DeviceManager.getInstance().getDevices();
        Number = list.size();
        return Number;
    }
    public int setProductNumber(){
        List<Product> list = ProductManager.getInstance().getProducts();
        Number = list.size();
        return Number;
    }
    public int setOrderNumber(){
        List<Product> list = ProductManager.getInstance().getProducts();
        Number = list.size();
        return Number;
    }
    public List<Dictionary> Initialized(){
        dictionaryList.add(new Dictionary("1","12exb928eh1en","UserInformation","用戶信息",setUserNumber()));
        dictionaryList.add(new Dictionary("2","0asdnewew2wq3","DeviceInformation","设备信息",setDeviceNumber()));
        dictionaryList.add(new Dictionary("3","09idwq23rrw42","ProductInformation","产品信息",setProductNumber()));
        dictionaryList.add(new Dictionary("4","09dfg2erf8vnr","OrderInformation","订单信息",setUserNumber()));

        return dictionaryList;
    }
}
