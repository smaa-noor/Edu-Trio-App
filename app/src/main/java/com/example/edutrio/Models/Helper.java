package com.example.edutrio.Models;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    Context mContext;

    public Helper(){}

    public Helper(Context context){
        this.mContext = context;
    }

    public void addToList(ArrayList<String> list, String value){
        if (!list.contains(value)){
            list.add(value);
        }
    }

    public void createAdapter(List<paperModel> papersList, String type, Spinner spinner){
        ArrayList<String> itemList = new ArrayList<>();
        switch (type){
            case "Class":
                papersList.forEach(paper -> {
                    addToList(itemList, paper.getDiscipline());
                });
                break;
            case "Subject":
                papersList.forEach(paper -> {
                    addToList(itemList, paper.getSubject());
                });
                break;
            case "Year":
                papersList.forEach(paper -> {
                    addToList(itemList, String.valueOf(paper.getYear()));
                });
                break;
            default:
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, itemList);
        spinner.setAdapter(adapter);
    }
}
