package com.jmdev.greatmovies.persistence;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class OtherServicesTypeConverter {


    @TypeConverter
    public static ArrayList<Integer>fromString(String data){
        Type listType=new TypeToken<ArrayList <Integer>>(){}.getType();
        return  new Gson().fromJson(data,listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Integer> list){
        Gson gson=new Gson();
        String json=gson.toJson(list);
        return json;
    }
}
