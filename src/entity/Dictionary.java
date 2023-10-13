package entity;

import util.FileOperator;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private String ID;
    private String Code;
    private String Type;
    private String Name;
    private int Number;

    public Dictionary(String ID, String Code, String Type, String Name, int Number){
        this.ID = ID;
        this.Code = Code;
        this.Type = Type;
        this.Name = Name;
        this.Number = Number;
    }

    public String getCode() {
        return Code;
    }

    public String getID() {
        return ID;
    }

    public String getType() {
        return Type;
    }

    public int getNumber() {
        return Number;
    }

    public String getName() {
        return Name;
    }



}
