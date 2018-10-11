package com.example.jerry.odapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Pending {
    public String nme;
    public String rsn;
    public String reg;
    public String stn;

    public Pending() {
    }

    public Pending(String nme, String rsn, String reg, String stn) {
        this.nme = nme;
        this.rsn = rsn;
        this.reg = reg;
        this.stn = stn;
    }
    public String getNme() {
        return nme;
    }

    public void setNme(String nme) {
        this.nme = nme;
    }

    public String getRsn() {
        return rsn;
    }

    public void setRsn(String rsn) {
        this.rsn = rsn;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getStn() {
        return stn;
    }

    public void setStn(String stn) {
        this.stn = stn;
    }
}
