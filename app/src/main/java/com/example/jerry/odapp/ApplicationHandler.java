package com.example.jerry.odapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ApplicationHandler {

    public String regNo;
    public String name;
    public String dept,sec,year;
    public String reason,from,to;
    public String classAdvisor;
    public String emailName;
    public String status;

    public ApplicationHandler(){

    }
    public ApplicationHandler(String name,String regNo,String dept,String sec,String year,String reason,String from,String to,String classAdvisor,String emailName,String status){
        this.name=name;
        this.regNo=regNo;
        this.dept=dept;
        this.sec=sec;
        this.year=year;
        this.reason=reason;
        this.from=from;
        this.to=to;
        this.classAdvisor=classAdvisor;
        this.emailName=emailName;
        this.status=status;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getClassAdvisor() {
        return classAdvisor;
    }

    public void setClassAdvisor(String classAdvisor) {
        this.classAdvisor = classAdvisor;
    }


    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
