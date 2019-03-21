package me.dicky.crudandroid.model;

import java.util.List;

//mengirim data dari android ke api database
public class Value {
    String value;
    String message;
    List<Mahasiswa> result;

    public String getValue(){
        return  value;
    }

    public String getMessage(){
        return  message;
    }

    public List<Mahasiswa> getResult(){
        return  result;
    }
}
