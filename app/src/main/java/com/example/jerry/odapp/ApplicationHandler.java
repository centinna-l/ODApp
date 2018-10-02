package com.example.jerry.odapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ApplicationHandler {
    public String regNo;
    public String hashData;
    public ApplicationHandler(){

    }
    public ApplicationHandler(String regNo,String hashData){
        this.regNo=regNo;
        this.hashData=hashData;
    }
}
